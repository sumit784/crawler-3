package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao of CategoryPoster
 * Created by qinyuan on 15-3-28.
 */
public class CategoryPosterDao {
    private final static String CATEGORY_ID = "categoryId";

    public List<CategoryPoster> getInstances(int categoryId) {
        return HibernateUtils.getList(CategoryPoster.class,
                CATEGORY_ID + "=" + categoryId + RankingDao.ASC_ORDER);
    }

    public CategoryPoster getInstance(Integer id) {
        return HibernateUtils.get(CategoryPoster.class, id);
    }

    public Integer add(Integer categoryId, String path, String link) {
        CategoryPoster categoryPoster = new CategoryPoster();
        categoryPoster.setCategoryId(categoryId);
        categoryPoster.setPath(path);
        categoryPoster.setLink(link);
        return new RankingDao().add(categoryPoster);
    }

    public void delete(Integer id) {
        HibernateUtils.delete(CategoryPoster.class, id);
    }

    public void rankUp(int id) {
        new RankingDao().rankUp(CategoryPoster.class, id, CATEGORY_ID);
    }

    public void rankDown(int id) {
        new RankingDao().rankDown(CategoryPoster.class, id, CATEGORY_ID);
    }
}
