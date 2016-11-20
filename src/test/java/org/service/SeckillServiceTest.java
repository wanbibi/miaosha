package org.service;

import org.dao.SeckillDAO;
import org.dao.SuccessKilledDAO;
import org.dto.Exposer;
import org.dto.SeckillExecution;
import org.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 16.11.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-dao.xml", "/spring/spring-service.xml"})
public class SeckillServiceTest {

    @Resource
    SeckillDAO seckillDAO;

    @Resource
    SuccessKilledDAO successKilledDAO;
    @Resource
    SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        for (Seckill seckill : seckillList) {
            System.out.println(seckill);
        }
    }

    @Test
    public void getById() throws Exception {
        Seckill s = seckillService.getById(1000L);
        System.out.println(s);
    }

    @Test
    public void expoertSeckillUrl() throws Exception {
        Exposer exposer = seckillService.expoertSeckillUrl(1002L);
        System.out.println(exposer.toString());
    }

    @Test
    public void executeSeckill() throws Exception {
        SeckillExecution seckillExecution = seckillService.executeSeckill(1002L, 14312341234L, "2647380c3e60c40e8d6353f7841b5186");
        System.out.println(seckillExecution.toString());
    }

}