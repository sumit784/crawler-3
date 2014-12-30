package com.qinyuan15.crawler.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by qinyuan on 14-12-27.
 */
public class ProxyDao extends CommonDao<Proxy> {

    @Override
    public void add(List<? extends Proxy> list) {
        Session session = HibernateUtil.openSession();
        String query = "FROM Proxy WHERE host=? and port=?";
        for (Proxy proxy : list) {
            if (session.createQuery(query).
                    setString(0, proxy.getHost()).
                    setInteger(1, proxy.getPort()).
                    list().size() == 0) {
                session.save(proxy);
            }
        }
        HibernateUtil.commitAndClose(session);
    }
}
