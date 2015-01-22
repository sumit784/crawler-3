package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test PriceRecordDao
 * Created by qinyuan on 15-1-13.
 */
public class PriceRecordDaoTest {
    @Test
    public void testFactory() throws Exception {
        List<PriceRecord> priceRecords = PriceRecordDao.factory().getInstances();
        assertThat(priceRecords).isNotEmpty();

        PriceRecord firstRecord = priceRecords.get(0);
        Integer commodityId = firstRecord.getCommodityId();
        Date recordTime = firstRecord.getRecordTime();

        priceRecords = PriceRecordDao.factory().setCommodityId(commodityId).getInstances();
        assertThat(priceRecords).isNotEmpty();

        priceRecords = PriceRecordDao.factory().setRecordTime(recordTime).getInstances();
        assertThat(priceRecords).isNotEmpty();

        priceRecords = PriceRecordDao.factory().setRecordTime(recordTime)
                .setCommodityId(commodityId).getInstances();
        assertThat(priceRecords).isNotEmpty();
    }
}