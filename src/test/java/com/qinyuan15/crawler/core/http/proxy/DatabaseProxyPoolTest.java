package com.qinyuan15.crawler.core.http.proxy;

import com.qinyuan15.crawler.core.http.proxy.DatabaseProxyPool;
import com.qinyuan15.crawler.dao.Proxy;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

/**
 * Test DatabaseProxyPool
 * Created by qinyuan on 14-12-31.
 */
public class DatabaseProxyPoolTest {

    private DatabaseProxyPool pool;

    @Before
    public void setUp() throws Exception {
        pool = new DatabaseProxyPool();
    }

    @Test
    public void testUpdateSpeed() throws Exception {
        Proxy proxy = pool.next();
        proxy.setSpeed(new Random().nextInt());
        pool.updateSpeed(proxy);
    }

    @Test
    public void testNext() throws Exception {
        Proxy proxy = pool.next();
        assertThat(proxy).isNotNull();
        assertThat(proxy.getSpeed()).isNotNull();
    }
}