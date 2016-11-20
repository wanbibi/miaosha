package org.dao;

import org.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 16.11.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"/spring/spring-dao.xml"})
    public class SuccessKilledDAOTest {

        @Resource
        private SuccessKilledDAO successKilledDAO;

        @Test
    public void insertSuccessKilled() throws Exception {
        int i = successKilledDAO.insertSuccessKilled(1000L, 12342314L);
        System.out.println(i);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        List<SuccessKilled> successKilleds = successKilledDAO.queryByIdWithSeckill(1000L);
        for (SuccessKilled s : successKilleds) {
            System.out.println(s);

        }

    }

}