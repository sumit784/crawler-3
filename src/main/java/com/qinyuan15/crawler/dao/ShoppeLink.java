package com.qinyuan15.crawler.dao;

/**
 * Link of Shoppe
 * Created by qinyuan on 15-2-17.
 */
public class ShoppeLink {
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

    public static ShoppeLink create(String url, String name) {
        ShoppeLink shoppeLink = new ShoppeLink();
        shoppeLink.url = url;
        shoppeLink.name = name;
        return shoppeLink;
    }
}
