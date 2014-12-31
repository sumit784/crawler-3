package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.Proxy;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

/**
 * Test ProxyPool
 * Created by qinyuan on 14-12-31.
 */
public class ProxyPoolTest {

    private final static int POOL_SIZE = 10;
    private ProxyPool pool;

    @Before
    public void setUp() throws Exception {
        pool = new ProxyPool(POOL_SIZE);
    }

    @Test
    public void testUpdateSpeed() throws Exception {
        Proxy proxy = pool.next();
        pool.updateSpeed(proxy, new Random().nextInt());
    }

    @Test
    public void testNext() throws Exception {
        Proxy proxy = pool.next();
        assertThat(proxy).isNotNull();
        assertThat(proxy.getSpeed()).isNotNull();
    }
}
