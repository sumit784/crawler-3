package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.category.CategoryUtils;

import java.util.List;

/**
 * Dao object of branch
 * Created by qinyuan on 15-2-24.
 */
public class CategoryDao {

    public List<Category> getInstances() {
        return HibernateUtil.getList(Category.class);
    }

    public Category getInstance(Integer id) {
        return HibernateUtil.get(Category.class, id);
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

    public List<Category> getSubInstancesAndSelf(int parentId) {
        return HibernateUtil.getList(Category.class, "id = " + parentId + " OR parentId=" + parentId);
    }

    public String getSubInstancesAndSelfIdsString(int parentId) {
        List<Category> categories = getSubInstancesAndSelf(parentId);
        return CategoryUtils.getIdsString(categories);
    }

    public boolean isUsed(int id) {
        return HibernateUtil.getCount(Commodity.class, "categoryId=" + id) > 0;
    }

    public void delete(int id) {
        if (!isUsed(id)) {
            new HotSearchWordDao().clear(id);
            HibernateUtil.delete(Category.class, id);
        }
    }
}
