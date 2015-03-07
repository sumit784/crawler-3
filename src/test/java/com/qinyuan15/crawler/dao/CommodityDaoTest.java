package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CommodityDao
 * Created by qinyuan on 15-1-14.
 */
public class CommodityDaoTest {
    @Test
    public void testFactory() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().getInstances();
        assertThat(commodities).isNotEmpty();
    }

    @Test
    public void testInLowPrice() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().setInLowPrice(true).getInstances();
        System.out.println(commodities.size());
    }

    @Test
    public void testSetUserId() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().setUserId(1).getInstances();
        System.out.println(commodities.size());
    }

    @Test
    public void testGetInstance() throws Exception {
        CommodityDao dao = new CommodityDao();
        Commodity commodity = dao.getInstance(10);
        if (commodity != null) {
            System.out.println(commodity.getShowId());
            System.out.println(commodity.getSerialNumber());
        }
    }

    @Test
    public void testGetInstancesByShowId() throws Exception {
        List<Commodity> commodities = new CommodityDao().getInstancesByShowId("40780735321");
        System.out.println(commodities.size());
    }
}
