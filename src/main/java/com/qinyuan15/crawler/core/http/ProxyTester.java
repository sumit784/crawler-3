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
        HttpClientWrapper client = new HttpClientWrapper();
        Session session = HibernateUtil.getSession();
        int batchSize = 2, i = 0;
        @SuppressWarnings("unchecked")
        List<Proxy> proxies = session.createQuery("FROM Proxy ORDER BY speed asc, id desc").list();
        for (Proxy proxy : proxies) {
            client.setProxy(proxy);
            try {
                client.getContent(this.testPage);
                LOGGER.info("connect to {} with proxy in {} millisesonds", this.testPage, proxy, client.getLastConnectTime());
                proxy.setSpeed(client.getLastConnectTime());
            } catch (Exception e) {
                proxy.setSpeed(Integer.MAX_VALUE);
                LOGGER.info("fail to connect {} with proxy {}: {}", this.testPage, proxy, e.getMessage());
            }
            session.update(proxy);
            if ((++i) % batchSize == 0) {
                session.flush();
                session.clear();
            }
        }
        HibernateUtil.commit(session);
    }
}
