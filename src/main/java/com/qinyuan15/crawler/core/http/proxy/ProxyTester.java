package com.qinyuan15.crawler.core.http.proxy;

import com.qinyuan15.crawler.core.http.HttpClientWrapper;
import com.qinyuan15.crawler.dao.HibernateUtils;
import com.qinyuan15.crawler.dao.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class to test Proxy
 * Created by qinyuan on 15-1-2.
 */
public class ProxyTester {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyTester.class);

    public final static String DEFAULT_TEST_PAGE = "www.baidu.com";

    private String testPage = DEFAULT_TEST_PAGE;

    public void setTestPage(String testPage) {
        this.testPage = testPage;
    }

    public void run() {
        LOGGER.info("proxy test start!");
        @SuppressWarnings("unchecked")
        List<Proxy> proxies = HibernateUtils.getList("FROM Proxy ORDER BY speed asc, id desc");
        for (Proxy proxy : proxies) {
            HttpClientWrapper client = new HttpClientWrapper();
            client.setProxy(proxy);
            try {
                LOGGER.info("start testing {} with page {}.", proxy, this.testPage);
                client.getContent(this.testPage);
                LOGGER.info("connect to {} with proxy {} in {} milliseconds",
                        this.testPage, proxy, client.getLastConnectTime());
                proxy.setSpeed(client.getLastConnectTime());
            } catch (Exception e) {
                proxy.setSpeed(Integer.MAX_VALUE);
                LOGGER.info("fail to connect {} with proxy {}: {}",
                        this.testPage, proxy, e.getMessage());
            }
            HibernateUtils.update(proxy);
        }
        LOGGER.info("proxy test complete!");
    }
}
