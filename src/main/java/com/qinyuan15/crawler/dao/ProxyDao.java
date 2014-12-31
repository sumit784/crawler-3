package com.qinyuan15.crawler.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by qinyuan on 14-12-27.
 */
public class ProxyDao extends AbstractDao<Proxy> {

    @Override
    public void add(List<? extends Proxy> list) {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM Proxy WHERE host=? and port=?");
        for (Proxy proxy : list) {
            if (query.setString(0, proxy.getHost()).
                    setInteger(1, proxy.getPort()).
                    list().size() == 0) {
                session.save(proxy);
            }
        }
        HibernateUtil.commitAndClose();
    }

    public List<Proxy> getTop(int count) {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM Proxy ORDER BY speed").
                setFirstResult(1).setMaxResults(count);
        @SuppressWarnings("unchecked")
        List<Proxy> list = query.list();
        return list;
    }

    public void updateSpeed(int id, int speed) {
        Proxy proxy = this.getInstance(id);
        if (proxy != null) {
            proxy.setSpeed(speed);
        }
        this.edit(proxy);
    }
}
