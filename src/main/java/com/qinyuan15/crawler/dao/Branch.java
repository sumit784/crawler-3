package com.qinyuan15.crawler.dao;

/**
 * Persist Object of Branch
 * Created by qinyuan on 15-2-18.
 */
public class Branch extends PersistObject {
    private String name;
    private String logoPath;

    public String getName() {
        return name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
