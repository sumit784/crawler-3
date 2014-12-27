package com.qinyuan15.crawler.dao;

import org.hibernate.Session;

/**
 * Created by qinyuan on 14-12-25.
 */
public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Proxy proxy = new Proxy();
        proxy.setHost("192.168.8.1");
        proxy.setPort(80);
        session.save(proxy);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }
}
