package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.config.ArticleUtils;

/**
 * Persist Object of Article
 * Created by qinyuan on 15-4-13.
 */
public class Article extends PersistObject {
    private String content;
    private String backgroundColor;

    public String getContent() {
        return content;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTitle() {
        return ArticleUtils.getTitle(content);
    }
}
