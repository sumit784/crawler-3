package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test UserLogDao
 * Created by qinyuan on 15-3-6.
 */
public class UserLogDaoTest {
    private UserLogDao dao = new UserLogDao();

    @Test
    public void testGetInstances() throws Exception {
        System.out.println(dao.getInstances().size());
    }

    @Test
    public void testGetInstancesByUserId() throws Exception {
        System.out.println(dao.getInstancesByUserId(1).size());
    }

    @Test
    public void testSave() throws Exception {
        //dao.save(1, "测试");
    }
}
