package com.qinyuan15.crawler.dao;

import java.util.Iterator;

/**
 * Implements some methods of PersistObjectFactory
 * Created by qinyuan on 14-12-27.
 */
abstract public class AbstractPersistObjectFactory<T extends PersistObject> implements PersistObjectFactory {
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
}
