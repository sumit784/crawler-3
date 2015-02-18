package com.qinyuan15.crawler.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * class about shoppe
 * Created by qinyuan on 15-2-17.
 */
public class Shoppe {
    private String name;
    private String logoUrl;
    private String desc;
    private List<ShoppeLink> links;

    public void setName(String name) {
        this.name = name;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLinks(List<ShoppeLink> links) {
        this.links = links;
    }

    public synchronized void addLinks(ShoppeLink link) {
        if (this.links == null) {
            this.links = new ArrayList<ShoppeLink>();
        }
        this.links.add(link);
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getDesc() {
        return desc;
    }

    public List<ShoppeLink> getLinks() {
        return links;
    }
}
