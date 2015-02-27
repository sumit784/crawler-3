package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao object of branch
 * Created by qinyuan on 15-2-24.
 */
public class CategoryDao {

    public List<Category> getInstances() {
        return HibernateUtil.getList(Category.class);
    }

    public Category getFirstInstance() {
        @SuppressWarnings("unchecked")
        List<Category> instances = HibernateUtil.getList("FROM Category ORDER BY id");
        return instances.size() == 0 ? null : instances.get(0);
    }

    public List<Category> getRootInstances() {
        return HibernateUtil.getList(Category.class, "parentId IS NULL OR parentId<=0");
    }

    public List<Category> getSubInstances(int parentId) {
        return HibernateUtil.getList(Category.class, "parentId=" + parentId);
    }
}
