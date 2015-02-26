package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao object of branch
 * Created by qinyuan on 15-2-24.
 */
public class BranchDao {

    public List<Branch> getInstances() {
        return HibernateUtil.getList(Branch.class);
    }

    public List<Branch> getRootInstances() {
        return HibernateUtil.getList(Branch.class, "parentId IS NULL OR parentId<=0");
    }

    public List<Branch> getSubInstances(int parentId) {
        return HibernateUtil.getList(Branch.class, "parentId=" + parentId);
    }
}
