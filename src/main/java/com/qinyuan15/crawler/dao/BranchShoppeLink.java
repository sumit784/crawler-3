package com.qinyuan15.crawler.dao;

/**
 * Link of BranchShoppe
 * Created by qinyuan on 15-2-17.
 */
public class BranchShoppeLink {
    private String url;
    private String name;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public static BranchShoppeLink create(String url, String name) {
        BranchShoppeLink branchShoppeLink = new BranchShoppeLink();
        branchShoppeLink.url = url;
        branchShoppeLink.name = name;
        return branchShoppeLink;
    }
}
