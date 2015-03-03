package com.qinyuan15.crawler.dao;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Dao object of branch
 * Created by qinyuan on 15-2-24.
 */
public class BranchDao {

    public Branch getInstance(Integer id) {
        return HibernateUtil.get(Branch.class, id);
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

    public List<Branch> getInstancesByCategoryId(int categoryId) {
        List<Integer> categoryIds = Lists.newArrayList(categoryId);
        for (Category subCategory : new CategoryDao().getSubInstances(categoryId)) {
            categoryIds.add(subCategory.getId());
        }

        String subHql = "SELECT branchId FROM Commodity WHERE categoryId IN ("
                + StringUtils.join(categoryIds, ",") + ")";
        return HibernateUtil.getList(Branch.class, "WHERE id IN (" + subHql + ")");
    }
}
