package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Factory with pagination function
 * Created by qinyuan on 15-4-5.
 */
public interface PaginationFactory<T extends PersistObject> {
    long getCount();

    List<T> getInstances(int firstResult, int maxResults);
}
