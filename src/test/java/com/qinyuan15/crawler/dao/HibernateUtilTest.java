package com.qinyuan15.crawler.dao;

import org.hibernate.Session;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test HibernateUtil
 * Created by qinyuan on 14-12-31.
 */
public class HibernateUtilTest {

    @Test
    public void test() {
        int testTimes = 200;
        for (int i = 0; i < testTimes; i++) {
            Session session = HibernateUtils.getSession();
            assertThat(session).isNotNull();

            Proxy proxy = new Proxy();
            proxy.setHost("192.168.8.1");
            proxy.setPort(80);

            Integer id = (Integer) session.save(proxy);
            proxy.setId(id);
            session.delete(proxy);

            HibernateUtils.commit(session);
        }
    }

    @Test
    public void testGetCount() {
        long count = HibernateUtils.getCount("Commodity");
        assertThat(HibernateUtils.getCount("Commodity", "id>0")).isEqualTo(count);
    }
}
