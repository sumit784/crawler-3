package com.qinyuan15.crawler.core.category;

import com.qinyuan15.crawler.dao.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Group of hot search words
 * Created by qinyuan on 15-3-21.
 */
public class HotSearchWordGroup {
    private Category category;
    private List<HotSearchWord> hotSearchWords;
    private List<Branch> branches;
    private Integer categoryLevel;

    public void setHotSearchWords(List<HotSearchWord> hotSearchWords) {
        this.hotSearchWords = hotSearchWords;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
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

    public List<Branch> getBranches() {
        return branches;
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
        hotSearchWordGroup.setBranches(new BranchDao().getInstancesByCategoryId(category.getId()));

        return hotSearchWordGroup;
    }
}
