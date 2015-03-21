package com.qinyuan15.crawler.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;


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
        try {
            session.getTransaction().commit();
        } catch (Throwable e) {
            LOGGER.error("fail to commit: {}", e);
            throw new RuntimeException(e);
        } finally {
            session.close();    /// ensure session is closed
        }
    }

    public static Integer save(Object object) {
        Session session = getSession();
        try {
            return (Integer) session.save(object);
        } catch (Throwable e) {
            LOGGER.error("fail to save: {}", e);
            throw new RuntimeException(e);
        } finally {
            commit(session);    // ensure session is closed
        }
    }

    public static void saveOrUpdate(Object object) {
        Session session = getSession();
        try {
            session.saveOrUpdate(object);
        } catch (Throwable e) {
            LOGGER.error("fail to saveOrUpdate: {}", e);
            throw new RuntimeException(e);
        } finally {
            commit(session);    // ensure session is closed
        }
    }

    public static void update(Object object) {
        Session session = getSession();
        try {
            session.update(object);
        } catch (Throwable e) {
            LOGGER.error("fail to update: {}", e);
            throw new RuntimeException(e);
        } finally {
            commit(session);    // ensure session is closed
        }
    }

    public static void delete(Class<?> clazz, String whereClause) {
        Session session = HibernateUtil.getSession();
        String hql = "DELETE FROM " + clazz.getSimpleName() + " " +
                adjustWhereClause(whereClause);
        session.createQuery(hql).executeUpdate();
        HibernateUtil.commit(session);
    }

    private static String adjustWhereClause(String whereClause) {
        if (!StringUtils.hasText(whereClause)) {
            return "";
        }
        String lowerCaseString = whereClause.trim().toLowerCase();

        if (!(lowerCaseString.startsWith("where") || lowerCaseString.startsWith("order by"))) {
            return " WHERE " + whereClause;
        } else {
            return whereClause;
        }
    }

    public static void delete(Class clazz, Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Object object = session.get(clazz, id);
            if (object != null) {
                session.delete(object);
            }
        } catch (Throwable e) {
            LOGGER.error("fail to delete: {}", e);
            throw new RuntimeException(e);
        } finally {
            commit(session);    // ensure session is closed
        }
    }

    public static <T> T get(Class<T> clazz, Integer id) {
        if (id == null) {
            return null;
        }

        Session session = HibernateUtil.getSession();
        @SuppressWarnings("unchecked")
        T object = (T) session.get(clazz, id);
        session.close();
        return object;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getList(Class<T> clazz) {
        return getList(clazz.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getList(Class<T> clazz, String whereClause) {
        String hql = clazz.getSimpleName() + " " + adjustWhereClause(whereClause);
        return getList(hql);
    }

    public static List getList(String hql) {
        return getList(hql, -1, -1);
    }

    public static Object getFirstItem(String hql) {
        List list = getList(hql, 0, 1);
        return list.size() > 0 ? list.get(0) : null;
    }

    public static List getList(String hql, int firstResult, int maxResults) {
        Session session = HibernateUtil.getSession();
        try {
            hql = hql.trim();
            if (!hql.toLowerCase().startsWith("from") &&
                    !hql.toLowerCase().startsWith("select")) {
                hql = "FROM " + hql;
            }
            Query query = session.createQuery(hql);
            if (firstResult >= 0 && maxResults > 0) {
                query.setFirstResult(firstResult).setMaxResults(maxResults);
            }
            return query.list();
        } catch (Exception e) {
            LOGGER.error("fail to get list: {}", e);
            throw new RuntimeException(e);
        } finally {
            commit(session);
            //session.close();    // ensure session is closed
        }
    }

    public static long getCount(Class<?> clazz) {
        return getCount(clazz, "");
    }

    public static long getCount(String className) {
        return getCount(className, "");
    }

    public static long getCount(Class<?> clazz, String whereCondition) {
        return getCount(clazz.getSimpleName(), whereCondition);
    }

    public static long getCount(String className, String whereCondition) {
        List list = getList("SELECT COUNT(*) FROM " + className + " " +
                adjustWhereClause(whereCondition));
        return (Long) list.get(0);
    }
}
