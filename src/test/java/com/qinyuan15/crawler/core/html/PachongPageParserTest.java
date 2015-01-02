package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Proxy;
import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by qinyuan on 14-12-29.
 */
public class PachongPageParserTest {
    private PachongPageParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new PachongPageParser();
        parser.setHTML(TestFileUtils.read("pachong.html"));
    }

    @Test
    public void testGetProxies() throws Exception {
        List<Proxy> proxies = parser.getProxies();
        assertThat(proxies).hasSize(50);
    }

    @Test
    public void testGetSubLinks() throws Exception {

    }
}
