package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
}
