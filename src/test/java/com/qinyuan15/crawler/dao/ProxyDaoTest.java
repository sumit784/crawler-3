package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by qinyuan on 14-12-29.
 */
public class ProxyDaoTest {
    private ProxyDao dao = new ProxyDao();

    @Test
    public void testGetTop() throws Exception {
        assertThat(dao.getTop(10)).hasSize(10);
    }

    @Test
    public void testUpdateSpped() throws Exception {
        Proxy proxy = dao.getTop(1).get(0);
        int id = proxy.getId();
        int speed = new Random().nextInt(100000);

        dao.updateSpeed(id, speed);

        proxy = dao.getInstance(id);
        assertThat(proxy.getSpeed()).isEqualTo(speed);
    }
}
