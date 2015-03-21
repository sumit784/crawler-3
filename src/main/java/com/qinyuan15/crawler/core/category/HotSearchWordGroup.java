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
    private Integer categoryId;
    private String categoryName;
    private int categoryLevel;
    private List<HotSearchWord> hotSearchWords;

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryLevel(int categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setHotSearchWords(List<HotSearchWord> hotSearchWords) {
        this.hotSearchWords = hotSearchWords;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<HotSearchWord> getHotSearchWords() {
        return hotSearchWords;
    }

    public int getCategoryLevel() {
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

        hotSearchWordGroup.setCategoryId(category.getId());
        hotSearchWordGroup.setCategoryName(category.getName());
        hotSearchWordGroup.setCategoryLevel(categoryLevel);
        hotSearchWordGroup.setHotSearchWords(new HotSearchWordDao().getInstances(category.getId()));

        return hotSearchWordGroup;
    }
}
