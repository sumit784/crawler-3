package com.qinyuan15.crawler.dao;

/**
 * Persist Object of IndexLogo
 * Created by qinyuan on 15-2-18.
 */
public class IndexLogo extends PersistObject {
    private String path;
    private String link;
    private Integer ranking;

    public String getPath() {
        return path;
    }

    public String getLink() {
        return link;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
