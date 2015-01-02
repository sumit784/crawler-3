package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.http.lib.TestProxyPool;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Case of HttpClientWrapper
 * Created by qinyuan on 14-12-24.
 */
public class HttpClientWrapperTest {
    private HttpClientWrapper client;
    private ProxyPool proxyPool;
    private String url = "http://www.baidu.com";
    //private String url = "http://localhost/test/test.php";

    @Before
    public void setUp() throws Exception {
        client = new HttpClientWrapper();
        proxyPool = new TestProxyPool();
        //client.setProxy(proxyPool.next());
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
