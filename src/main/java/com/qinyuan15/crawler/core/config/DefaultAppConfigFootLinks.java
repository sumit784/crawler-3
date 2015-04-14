package com.qinyuan15.crawler.core.config;

import com.qinyuan15.crawler.dao.AppConfigFootLink;

import java.util.List;

/**
 * Class to wrap default AppConfigFootLink
 * Created by qinyuan on 15-4-14.
 */
public class DefaultAppConfigFootLinks {
    private List<AppConfigFootLink> footLinks;

    public void setFootLinks(List<AppConfigFootLink> footLinks) {
        this.footLinks = footLinks;
    }

    public List<AppConfigFootLink> getFootLinks() {
        return footLinks;
    }
}
