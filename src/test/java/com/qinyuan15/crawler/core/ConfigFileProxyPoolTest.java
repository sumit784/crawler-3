package com.qinyuan15.crawler.core;

import com.qinyuan15.crawler.core.http.ProxyPool;
import com.qinyuan15.crawler.dao.Proxy;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Case of ConfigFileProxyPool
 * Created by qinyuan on 14-12-24.
 */
public class ConfigFileProxyPoolTest {
    private ProxyPool pool;

    @Before
    public void setUp() throws Exception {
        pool = new ConfigFileProxyPool();
    }

    @Test
    public void testGetAll() throws Exception {
        assertThat(pool.getAll()).isNotEmpty();
    }

    @Test
    public void testNext() throws Exception {
        Proxy first = pool.next();
        assertThat(first).isNotNull();
        for (int i = 0; i < pool.size() - 1; i++) {
            Proxy proxy = pool.next();
            assertThat(proxy).isNotSameAs(first);
        }
        assertThat(pool.next()).isSameAs(first);
    }

    @Test
    public void testSize() throws Exception {
        assertThat(pool.size()).isGreaterThan(0);
    }
}
