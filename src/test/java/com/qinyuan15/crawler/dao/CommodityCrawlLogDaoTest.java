package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test CommodityCrawlLogDao
 * Created by qinyuan on 15-3-29.
 */
public class CommodityCrawlLogDaoTest {
    private CommodityCrawlLogDao dao = new CommodityCrawlLogDao();

    @Test
    public void testGetInstances() throws Exception {
        System.out.println(dao.getInstances(true).size());
    }

    @Test
    public void testLogSuccess() {
        //dao.logSuccess(1, "HelloWorld");
    }

    @Test
    public void testLogFail() {
        //dao.logFail(2, "HelloWorld2");
    }
}
