package org.service;

import org.dto.Exposer;
import org.dto.SeckillExecution;
import org.entity.Seckill;
import org.exception.RepeatKillException;
import org.exception.SeckillCloseException;
import org.exception.SeckillException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 16.11.16.
 */
@Component
public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    Exposer expoertSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException;

}
