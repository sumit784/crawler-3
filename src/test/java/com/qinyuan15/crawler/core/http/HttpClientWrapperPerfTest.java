package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.http.lib.TestProxyPool;
import com.qinyuan15.crawler.core.http.proxy.ProxyPool;
import com.qinyuan15.crawler.dao.Proxy;
import org.junit.Test;

/**
 * Test Performance of HttpClientWrapper
 * Created by qinyuan on 14-12-24.
 */
public class HttpClientWrapperPerfTest {

    @Test
    public void test() throws Exception {
        ProxyPool pool = new TestProxyPool();
        HttpClientWrapper client = new HttpClientWrapper();
        //String url = "www.baidu.com";
        String url = "http://s.etao.com/detail/40780735321.html?tbpm=20141215";

        //int runTimes = 1000 * 10000;
        int runTimes = 2;
        Proxy proxy = pool.next();
        client.setProxy(proxy);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runTimes; i++) {
            if (i > 0 && i % 10 == 0) {
                long interval = System.currentTimeMillis() - startTime;
                System.out.println(i + " " + i * 1000.0 / interval);
            }
            /*
            HttpProxy proxy = pool.next();
            System.out.println("visit by proxy " + proxy.getHost() + ":" + proxy.getPort());
            client.setProxy(proxy);
            */
            try {
                /*
                HttpResponse response = client.get(url);
                System.out.println("status: " + response.getStatus() + ", title: " +
                        new HtmlParser(response.getContent()).getTitle());
                        */
                client.download(url, "/tmp/crawler/etao.html");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //Thread.sleep(50);
        }
    }
}
