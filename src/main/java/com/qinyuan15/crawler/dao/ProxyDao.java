package com.qinyuan15.crawler.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Data Access Object of Proxy
 * Created by qinyuan on 15-1-1.
 */
public class ProxyDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyDao.class);

    public void save(List<Proxy> proxies) {
        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery("FROM Proxy WHERE host=:host and port=:port");
            for (Proxy proxy : proxies) {
                if (query.setString("host", proxy.getHost()).setInteger("port", proxy.getPort())
                        .list().size() == 0) {
                    session.save(proxy);
                    LOGGER.info("save proxy {}.", proxy);
                } else {
                    LOGGER.info("{} already exists, no need to save.", proxy);
                }
            }
        } catch (Exception e) {
            LOGGER.error("fail to save: {}", e);
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.commit(session);
        }
    }
}
