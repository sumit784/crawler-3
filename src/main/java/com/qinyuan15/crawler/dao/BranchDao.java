package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao object of branch
 * Created by qinyuan on 15-2-24.
 */
public class BranchDao {

    @SuppressWarnings("unchecked")
    public List<String> getRootInstances() {
        String hql = "Branch WHERE parentId IS NULL OR parentId<=0";
        return HibernateUtil.getList(hql);
    }

    @SuppressWarnings("unchecked")
    public List<String> getSubInstances(int parentId) {
        String hql = "Branch WHERE parentId=" + parentId;
        return HibernateUtil.getList(hql);
    }
}
