package com.qinyuan15.crawler.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by qinyuan on 14-12-25.
 */
public class Main {

    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Proxy proxy = new Proxy();
        proxy.setHost("192.168.8.1");
        proxy.setPort(80);
        session.save(proxy);

        tx.commit();
        session.close();
    }
}
