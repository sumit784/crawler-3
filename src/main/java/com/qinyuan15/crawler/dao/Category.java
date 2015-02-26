package com.qinyuan15.crawler.dao;

/**
 * Persist object of category
 * Created by qinyuan on 15-2-25.
 */
public class Category extends PersistObject {
    private String name;
    private Integer parentId;

    public String getName() {
        return name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        Category category = HibernateUtil.get(Category.class, this.parentId);
        return category == null ? null : category.getName();
    }
}
