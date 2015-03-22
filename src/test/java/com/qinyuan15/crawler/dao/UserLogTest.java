package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test UserLog
 * Created by qinyuan on 15-3-6.
 */
public class UserLogTest {
    @Test
    public void test() throws Exception {
        System.out.println(HibernateUtils.getList(UserLog.class).size());
    }
}
