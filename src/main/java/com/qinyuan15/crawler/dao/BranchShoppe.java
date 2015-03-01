package com.qinyuan15.crawler.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * class about shoppe
 * Created by qinyuan on 15-2-17.
 */
public class BranchShoppe {
    private String name;
    private String logoUrl;
    private String desc;
    private List<BranchShoppeLink> links;

    public void setName(String name) {
        this.name = name;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLinks(List<BranchShoppeLink> links) {
        this.links = links;
    }

    public synchronized void addLinks(BranchShoppeLink link) {
        if (this.links == null) {
            this.links = new ArrayList<BranchShoppeLink>();
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

    public List<BranchShoppeLink> getLinks() {
        return links;
    }
}
