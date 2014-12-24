package com.qinyuan15.crawler.core;

import org.junit.Test;

/**
 * Test Performance of HttpClientWrapper
 * Created by qinyuan on 14-12-24.
 */
public class HttpClientWrapperPerfTest {

    @Test
    public void test() throws Exception {
        HttpProxyPool pool = new ConfigFileProxyPool();
        HttpClientWrapper client = new HttpClientWrapper();
        //String url = "www.baidu.com";
        String url = "http://s.etao.com/detail/40780735321.html?tbpm=20141215";

        int runTimes = 1;
        for (int i = 0; i < runTimes; i++) {
            HttpProxy proxy = pool.next();
            System.out.println("visit by proxy " + proxy.getHost() + ":" + proxy.getPort());
            client.setProxy(proxy);
            try {
                HttpResponse response = client.get(url);
                System.out.println("status: " + response.getStatus() + ", title: " +
                        new HtmlParser(response.getContent()).getTitle());
                client.download(url, "/tmp/crawler/etao.html");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Thread.sleep(2000);
        }
    }
}
