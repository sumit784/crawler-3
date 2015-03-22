package com.qinyuan15.crawler.core.category;

import com.qinyuan15.crawler.dao.Category;
import com.qinyuan15.crawler.dao.CategoryDao;
import com.qinyuan15.crawler.dao.HotSearchWord;
import com.qinyuan15.crawler.dao.HotSearchWordDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Group of hot search words
 * Created by qinyuan on 15-3-21.
 */
public class HotSearchWordGroup {
    private Category category;
    private List<HotSearchWord> hotSearchWords;
    private Integer categoryLevel;

    public void setHotSearchWords(List<HotSearchWord> hotSearchWords) {
        this.hotSearchWords = hotSearchWords;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public List<HotSearchWord> getHotSearchWords() {
        return hotSearchWords;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public static List<HotSearchWordGroup> getInstances() {
        List<HotSearchWordGroup> hotSearchWordGroups = new ArrayList<>();

        CategoryDao categoryDao = new CategoryDao();
        List<Category> rootCategories = categoryDao.getRootInstances();
        for (Category category : rootCategories) {
            hotSearchWordGroups.add(getInstance(category, 0));
            for (Category subCategory : categoryDao.getSubInstances(category.getId())) {
                hotSearchWordGroups.add(getInstance(subCategory, 1));
            }
        }

        return hotSearchWordGroups;
    }

    private static HotSearchWordGroup getInstance(Category category, int categoryLevel) {
        HotSearchWordGroup hotSearchWordGroup = new HotSearchWordGroup();

        hotSearchWordGroup.setCategory(category);
        hotSearchWordGroup.setCategoryLevel(categoryLevel);
        hotSearchWordGroup.setHotSearchWords(new HotSearchWordDao().getInstances(category.getId()));

        return hotSearchWordGroup;
    }
}
