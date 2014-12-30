package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Proxy;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
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
        String html = FileUtils.readFileToString(new File("src/test/resources/pachong.html"));
        parser.setHTML(html);
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
