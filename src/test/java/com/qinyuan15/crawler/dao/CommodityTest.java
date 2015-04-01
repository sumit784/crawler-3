package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test Commodity
 * Created by qinyuan on 15-4-1.
 */
public class CommodityTest {
    @Test
    public void test() {
        Commodity commodity = new Commodity();
        commodity.setName("test");
        commodity.setUrl("test");
        commodity.setActive(true);
        commodity.setSerialNumber("test");
        commodity.setShowId("test");
        //commodity.setUserId(3);
        commodity.setInLowPrice(true);
        HibernateUtils.save(commodity);
    }
}
