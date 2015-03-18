package com.qinyuan15.crawler.dao;

import org.hibernate.Session;
import org.junit.Test;

import java.sql.Date;
import static org.assertj.core.api.Assertions.*;

/**
 * Test Persistent Object PriceRecord
 * Created by qinyuan on 15-1-4.
 */
public class PriceRecordTest {

    @Test
    public void testSaveDelete() throws Exception {
        /*
        Session session = HibernateUtil.getSession();

        PriceRecord record = new PriceRecord();
        record.setCommodityId(1);
        record.setRecordTime(new Date(System.currentTimeMillis()));
        record.setPrice(10.0);

        Integer id = (Integer) session.save(record);
        HibernateUtil.commit(session);

        session = HibernateUtil.getSession();
        record = (PriceRecord) session.get(PriceRecord.class, id);
        assertThat(record).isExactlyInstanceOf(PriceRecord.class);

        session.delete(record);
        HibernateUtil.commit(session);

        session = HibernateUtil.getSession();
        assertThat(session.get(PriceRecord.class, id)).isNull();
        */
    }
}
