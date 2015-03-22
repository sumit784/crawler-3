package com.qinyuan15.crawler.dao;

/**
 * Persist Object of AppConfig
 * Created by qinyuan on 15-3-22.
 */
public class AppConfig extends PersistObject {
    private String detailText;
    private String detailImages;

    public String getDetailText() {
        return detailText;
    }

    public String getDetailImages() {
        return detailImages;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public void setDetailImages(String detailImages) {
        this.detailImages = detailImages;
    }
}
