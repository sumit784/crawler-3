package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao of CategoryBranch
 * Created by qinyuan on 15-3-25.
 */
public class CategoryBranchDao {
    private final static String CATEGORY_ID = "categoryId";

    public List<CategoryBranch> getInstances(int categoryId) {
        return HibernateUtils.getList(CategoryBranch.class,
                CATEGORY_ID + "=" + categoryId + RankingDao.ASC_ORDER);
    }

    public Integer add(Integer categoryId, Integer branchId) {
        CategoryBranch categoryBranch = new CategoryBranch();
        categoryBranch.setCategoryId(categoryId);
        categoryBranch.setBranchId(branchId);
        return new RankingDao().add(categoryBranch);
    }

    public void delete(Integer id) {
        HibernateUtils.delete(CategoryBranch.class, id);
    }

    public CategoryBranch getInstance(int id) {
        return HibernateUtils.get(CategoryBranch.class, id);
    }

    public void rankUp(int id) {
        new RankingDao().rankUp(CategoryBranch.class, id, CATEGORY_ID);
    }

    public void rankDown(int id) {
        new RankingDao().rankDown(CategoryBranch.class, id, CATEGORY_ID);
    }
}
