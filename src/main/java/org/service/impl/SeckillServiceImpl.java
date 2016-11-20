package org.service.impl;

import org.dao.SeckillDAO;
import org.dao.SuccessKilledDAO;
import org.dto.Exposer;
import org.dto.SeckillExecution;
import org.entity.Seckill;
import org.entity.SuccessKilled;
import org.enums.StateEnums;
import org.exception.RepeatKillException;
import org.exception.SeckillCloseException;
import org.exception.SeckillException;
import org.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.reflect.annotation.ExceptionProxy;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 16.11.17.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckillDAO seckillDAO;

    @Autowired
    private SuccessKilledDAO successKilledDAO;


    private final String salt = "uas%$*&%^E&T*&()*()_()_I}HF^R$%(^^)+*(SW$^F%RIGIUDCH)@I)+($I*HF947y";


    @Override
    public List<Seckill> getSeckillList() {
        return seckillDAO.queryAll(0, 5);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDAO.queryById(seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public Exposer expoertSeckillUrl(long seckillId) {
        Seckill seckill = seckillDAO.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    @Override
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("failed");
        }

        try {
            int reduceCount = seckillDAO.reduceNumber(seckillId, new Date());
            if (reduceCount <= 0) {
                throw new SeckillCloseException("seckill is closed");
            } else {
                int insertCount = successKilledDAO.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillException("repeat scekill");
                } else {
                    List<SuccessKilled> successKilleds = successKilledDAO.queryByIdWithSeckill(seckillId);
                    return new SeckillExecution(seckillId, StateEnums.SUCCESS, successKilleds.get(0));
                }
            }

        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());

        }
        return null;
    }
}
