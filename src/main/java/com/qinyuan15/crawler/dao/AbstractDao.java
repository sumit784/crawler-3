package com.qinyuan15.crawler.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Implements some methods of Dao
 * Created by qinyuan on 14-12-27.
 */
abstract public class AbstractDao<T extends PersistObject> {
    /**
     * 记录范型表示符'T'所指代的具体类型
     */
    private final Class classOfT = getClassOfT();

    /**
     * 获取运行过程中范型表示符'T'所指代的类型
     *
     * @return 范型表示符'T'在运行时的类型
     */
    private Class getClassOfT() {
        final Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type firstParam = params[0];
        if (firstParam instanceof GenericArrayType) {
            final String elementClassName = firstParam.toString().replace("[]", "");
            try {
                return Class.forName("[L" + elementClassName + ";");
            } catch (java.lang.ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            return (Class) firstParam;
        }
    }

    public T getInstance(int id) {
        Session session = HibernateUtil.openSession();
        @SuppressWarnings("unchecked")
        T t = (T) session.get(classOfT, id);
        return t;
    }

    public void add(PersistObject persistObject) {
        Session session = HibernateUtil.openSession();
        session.save(persistObject);
        HibernateUtil.commitAndClose();
    }

    public List<? extends T> getInstances() {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM " + classOfT.getSimpleName());
        @SuppressWarnings("unchecked")
        List<T> list = query.list();
        return list;
    }

    public void add(List<? extends T> list) {
        Session session = HibernateUtil.openSession();
        for (Object obj : list) {
            session.save(obj);
        }
        HibernateUtil.commitAndClose();
    }

    public void delete(int id) {
        Session session = HibernateUtil.openSession();
        Object obj = session.get(classOfT, id);
        if (obj != null) {
            session.delete(obj);
        }
        HibernateUtil.commitAndClose();
    }

    public void edit(PersistObject persistObject) {
        Session session = HibernateUtil.openSession();
        session.update(persistObject);
        HibernateUtil.commitAndClose();
    }
}
