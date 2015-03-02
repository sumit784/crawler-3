package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Persist Object of Branch
 * Created by qinyuan on 15-2-18.
 */
public class Branch extends PersistObject {
    private String name;
    private String logo;
    private Integer parentId;
    private String firstLetter;
    private String squareLogo;
    private String slogan;

    public void setSquareLogo(String squareLogo) {
        this.squareLogo = squareLogo;
    }

    public String getSquareLogo() {
        return squareLogo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public Integer getParentId() {
        return parentId;
    }

    private boolean parentNameInit = false;
    private String parentName;

    public synchronized String getParentName() {
        if (!parentNameInit) {
            Branch parent = HibernateUtil.get(Branch.class, this.parentId);
            parentName = parent == null ? null : parent.getName();
            parentNameInit = true;
        }
        return parentName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Shoppe> getShoppes() {
        return new ShoppeDao().getInstances(this.getId());
    }
}
