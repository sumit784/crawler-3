package com.qinyuan15.crawler.core;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test Case of ConfigFileProxyPool
 * Created by qinyuan on 14-12-24.
 */
public class ConfigFileProxyPoolTest {
    private HttpProxyPool pool;

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
        HttpProxy first = pool.next();
        assertThat(first).isNotNull();
        for (int i = 0; i < pool.size() - 1; i++) {
            HttpProxy proxy = pool.next();
            assertThat(proxy).isNotSameAs(first);
        }
        assertThat(pool.next()).isSameAs(first);
    }

    @Test
    public void testSize() throws Exception {
        assertThat(pool.size()).isGreaterThan(0);
    }
}
