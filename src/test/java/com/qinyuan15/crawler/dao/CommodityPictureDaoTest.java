package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test CommodityPictureDao
 * Created by qinyuan on 15-1-15.
 */
public class CommodityPictureDaoTest {
    private CommodityPictureDao dao = new CommodityPictureDao();

    @Test
    public void testGetInstances() throws Exception {
        System.out.println(dao.getInstances(1));
    }
}
