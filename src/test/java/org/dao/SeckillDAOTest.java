package org.dao;

import org.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 16.11.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-dao.xml"})
public class SeckillDAOTest {
    @Resource
    private SeckillDAO seckillDAO;
    @Test
    public void reduceNumber() throws Exception {
        int i = seckillDAO.reduceNumber(1000L, new Date());
        System.out.println(i);
    }

    @Test
    public void queryById() throws Exception {
        Seckill seckill = seckillDAO.queryById(1000L);
        System.out.println(seckill.toString());
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDAO.queryAll(0,10);
        for (Seckill s:seckills){
            System.out.println(s.toString());
        }
    }

}