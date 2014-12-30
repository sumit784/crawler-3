package com.qinyuan15.crawler.dao;

import org.hibernate.Session;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/**
 * Implements some methods of Dao
 * Created by qinyuan on 14-12-27.
 */
abstract public class AbstractDao<T extends PersistObject> implements Dao {

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

    @Override
    public T getInstance(int id) {
        @SuppressWarnings("unchecked")
        Iterator<T> iterator = getInstances().iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element.getId() == id) {
                return element;
            }
        }
        return null;
    }

    @Override
    public void add(PersistObject persistObject) {
        Session session = HibernateUtil.openSession();
        session.save(persistObject);
        HibernateUtil.commitAndClose(session);
    }

    @Override
    public void add(List list) {
        Session session = HibernateUtil.openSession();
        for (Object obj : list) {
            try {
                session.save(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HibernateUtil.commitAndClose(session);
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.openSession();
        Object obj = session.get(classOfT, id);
        if (obj != null) {
            session.delete(obj);
        }
        HibernateUtil.commitAndClose(session);
    }

    @Override
    public void edit(PersistObject persistObject) {
        Session session = HibernateUtil.openSession();
        session.update(persistObject);
        HibernateUtil.commitAndClose(session);
    }
}
