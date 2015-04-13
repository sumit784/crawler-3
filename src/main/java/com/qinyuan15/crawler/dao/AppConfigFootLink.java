package com.qinyuan15.crawler.dao;

/**
 * Persist Object of AppConfigFootLink
 * Created by qinyuan on 15-4-13.
 */
public class AppConfigFootLink extends PersistObject {
    private String text;
    private String link;

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
