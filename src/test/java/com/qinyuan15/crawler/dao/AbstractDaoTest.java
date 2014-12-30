package com.qinyuan15.crawler.dao;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinyuan on 14-12-29.
 */
public class AbstractDaoTest {

    private CommonDao<Proxy> dao;

    @Before
    public void setUp() throws Exception {
        dao = new CommonDao<Proxy>();
        testDelete();
    }

    @Test
    public void testGetInstance() throws Exception {
        // TODO
    }

    @Test
    public void testAdd() throws Exception {
        Proxy proxy = new Proxy();
        proxy.setId(1);
        proxy.setHost("192.168.1.1");
        proxy.setPort(8080);
        dao.add(proxy);
    }

    @Test
    public void testAdd1() throws Exception {
        List<Proxy> proxies = new ArrayList<Proxy>();

        Proxy proxy = new Proxy();
        proxy.setId(2);
        proxy.setHost("192.168.8.1");
        proxy.setPort(8080);
        proxies.add(proxy);

        proxy = new Proxy();
        proxy.setId(3);
        proxy.setHost("192.168.8.2");
        proxy.setPort(80);
        proxies.add(proxy);

        proxy = new Proxy();
        proxy.setId(4);
        proxy.setHost("192.168.8.3");
        proxy.setPort(80);
        proxies.add(proxy);

        dao.add(proxies);
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(1);
        dao.delete(2);
        dao.delete(3);
        dao.delete(4);
    }

    @Test
    public void testEdit() throws Exception {

    }
}
