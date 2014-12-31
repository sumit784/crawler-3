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
    public static String CONFIG_FILE = "hibernate.cfg.xml";

    private final static Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);
    private final static SessionFactory sessionFactory = buildSessionFactory();
    private static Session session;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure(CONFIG_FILE);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory creation failed.{}", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static void commitAndClose() {
        session.getTransaction().commit();
        //getSessionFactory().close();
    }
}
