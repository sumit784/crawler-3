package com.qinyuan15.crawler.dao;

/**
 * Commodity Branch
 * Created by qinyuan on 14-12-27.
 */
public class Branch extends PersistObject {
    private String name;
    private String logoUrl;

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
