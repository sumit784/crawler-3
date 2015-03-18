package com.qinyuan15.crawler.dao;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao object of branch
 * Created by qinyuan on 15-2-24.
 */
public class BranchDao {

    public Branch getInstance(Integer id) {
        return HibernateUtil.get(Branch.class, id);
    }

    public boolean isUsed(Integer id) {
        return HibernateUtil.getCount(Commodity.class, "branchId=" + id) > 0 ||
                HibernateUtil.getCount(Branch.class, "parentId=" + id) > 0;
    }

    public void delete(Integer id) {
        if (!isUsed(id)) {
            new ShoppeDao().clear(id);
            HibernateUtil.delete(Branch.class, id);
        }
    }

    public List<Branch> getInstances() {
        return HibernateUtil.getList(Branch.class);
    }

    public List<Branch> getRootInstances() {
        return HibernateUtil.getList(Branch.class, "parentId IS NULL OR parentId<=0");
    }

    public List<Branch> getSubInstances(int parentId) {
        return HibernateUtil.getList(Branch.class, "parentId=" + parentId);
    }

    public List<Branch> getAllSubInstances(int parentId) {
        List<Branch> allBranches = new ArrayList<>();
        List<Branch> subBranches = getSubInstances(parentId);
        if (subBranches.size() > 0) {
            allBranches.addAll(subBranches);
            for (Branch branch : subBranches) {
                allBranches.addAll(getAllSubInstances(branch.getId()));
            }
        }
        return allBranches;
    }

    public List<Branch> getAllSubInstancesAndSelf(int parentId) {
        List<Branch> branches = Lists.newArrayList(getInstance(parentId));
        branches.addAll(getAllSubInstances(parentId));
        return branches;
    }

    public String getAllSubInstancesAndSelfIdsString(int parentId) {
        List<Branch> branches = getAllSubInstancesAndSelf(parentId);
        return PersistObjectUtils.getIdsString(branches);
    }

    public List<Branch> getInstancesByCategoryId(int categoryId) {
        String subHql = "SELECT branchId FROM Commodity WHERE categoryId IN ("
                + new CategoryDao().getSubInstancesAndSelfIdsString(categoryId) + ")";
        return HibernateUtil.getList(Branch.class, "WHERE id IN (" + subHql + ")");
    }
}
