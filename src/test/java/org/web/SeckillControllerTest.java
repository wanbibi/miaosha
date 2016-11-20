package org.web;

import org.dao.SeckillDAO;
import org.dao.SuccessKilledDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.SeckillService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 16.11.18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-dao.xml", "/spring/spring-service.xml"})
public class SeckillControllerTest {

    @Resource
    SeckillDAO seckillDAO;

    @Resource
    SuccessKilledDAO successKilledDAO;
    @Resource
    SeckillService seckillService;

    @Test
    public void list() throws Exception {

    }

    @Test
    public void detail() throws Exception {

    }

    @Test
    public void exposer() throws Exception {

    }

    @Test
    public void execute() throws Exception {

    }

}