package com.qinyuan15.crawler.dao;

/**
 * Persist object of seo keyword
 * Created by qinyuan on 15-4-12.
 */
public class SeoKeyword extends PersistObject {
    private String url;
    private String word;

    public String getUrl() {
        return url;
    }

    public String getWord() {
        return word;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
