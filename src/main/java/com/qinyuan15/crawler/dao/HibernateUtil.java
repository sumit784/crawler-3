package com.qinyuan15.crawler.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Util class for Hibernate
 * Created by qinyuan on 14-12-26.
 */
public class HibernateUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);

    public static String CONFIG_FILE = "hibernate.cfg.xml";
    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure(CONFIG_FILE);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            LOGGER.error("fail to connect database {}", e);
            throw new RuntimeException(e);
        }
    }

    public static Session getSession() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static void commit(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    public static void save(Object object) {
        Session session = getSession();
        session.save(object);
        commit(session);
    }

    public static void saveOrUpdate(Object object) {
        Session session = getSession();
        session.saveOrUpdate(object);
        commit(session);
    }
}
