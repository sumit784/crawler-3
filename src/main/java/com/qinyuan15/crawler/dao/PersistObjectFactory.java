package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Factory to get/create Persist Object
 * Created by qinyuan on 14-12-27.
 */
public interface PersistObjectFactory<T extends PersistObject> {
    List<T> getInstances();

    T getInstance(int id);
}
