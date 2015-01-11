package com.qinyuan15.crawler.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by qinyuan on 15-1-1.
 */
public class ProxyDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyDao.class);

    public void save(List<Proxy> proxies) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Proxy WHERE host=? and port=?");
        for (Proxy proxy : proxies) {
            if (query.setString(0, proxy.getHost()).setInteger(1, proxy.getPort()).list().size() == 0) {
                session.save(proxy);
                LOGGER.info("save proxy {}.", proxy);
            } else {
                LOGGER.info("{} already exists, no need to save.", proxy);
            }
        }
        HibernateUtil.commit(session);
    }
}