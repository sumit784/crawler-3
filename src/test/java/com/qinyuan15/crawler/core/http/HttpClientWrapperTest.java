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
    String url = "http://www.baidu.com";

    @Before
    public void setUp() throws Exception {
        client = new HttpClientWrapper();
        //proxyPool = new TestProxyPool();
        proxyPool = new DatabaseProxyPool(10, 50 * 1000);
        client.setProxy(proxyPool.next());
    }

    @Test
    public void testGetContent() throws Exception {
        while (true) {
            try {
                String result = client.getContent(url);
                assertThat(result).contains("百度一下");
                break;
            } catch (Exception e) {
                e.printStackTrace();
                client.setProxy(proxyPool.next());
            }
        }
    }

    @Test
    public void testDownload() throws Exception {
        String filePath = "/tmp/crawler/baidu.html";
        client.download(url, filePath);
    }
}
