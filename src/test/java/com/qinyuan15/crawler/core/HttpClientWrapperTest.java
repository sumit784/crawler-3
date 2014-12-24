package com.qinyuan15.crawler.core;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test Case of HttpClientWrapper
 * Created by qinyuan on 14-12-24.
 */
public class HttpClientWrapperTest {
    private HttpClientWrapper client;
    String url = "http://www.baidu.com";

    @Before
    public void setUp() throws Exception {
        client = new HttpClientWrapper();
        client.setProxy(new ConfigFileProxyPool().next());
    }

    @Test
    public void testGetContent() throws Exception {
        String result = client.getContent(url);
        assertThat(result).contains("百度一下");
    }

    @Test
    public void testDownload() throws Exception {
        String filePath = "/tmp/crawler/baidu.html";
        client.download(url, filePath);
    }
}
