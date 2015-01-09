package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.HibernateUtil;
import com.qinyuan15.crawler.dao.Proxy;
import org.hibernate.Session;
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
        Session session = HibernateUtil.getSession();
        @SuppressWarnings("unchecked")
        List<Proxy> proxies = session.createQuery(
                "FROM Proxy ORDER BY speed asc, id desc").list();
        session.close();
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
            session = HibernateUtil.getSession();
            session.update(proxy);
            HibernateUtil.commit(session);
        }
    }
}
