package com.qinyuan15.crawler.dao;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CommodityPriceDao
 * Created by qinyuan on 15-1-22.
 */
public class CommodityPriceDaoTest {
    @Test
    public void testRange() throws Exception {
        CommodityPriceDao.PriceRange priceRange;

        for (int i = 0; i < 10; i++) {
            priceRange = CommodityPriceDao.range(i);
            Double max = priceRange.getMax();
            Double min = priceRange.getMin();
            if (max == null) {
                assertThat(min).isNull();
            } else {
                assertThat(max).isGreaterThanOrEqualTo(min);
            }
        }
    }
}
