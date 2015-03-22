package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test CommodityPicture
 * Created by qinyuan on 15-1-15.
 */
public class CommodityPictureTest {
    @Test
    public void test() {
        CommodityPicture commodityPicture = new CommodityPicture();
        commodityPicture.setId(1);
        commodityPicture.setCommodityId(1);
        commodityPicture.setUrl("www.baidu.com");

        HibernateUtils.save(commodityPicture);
        HibernateUtils.delete(CommodityPicture.class, 1);
    }
}
