package com.qinyuan15.crawler.dao;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.List;

/**
 * Dao of SeoKeyword
 * Created by qinyuan on 15-4-12.
 */
public class SeoKeywordDao {
    public List<SeoKeyword> getInstances() {
        return HibernateUtils.getList(SeoKeyword.class);
    }

    public SeoKeyword getInstance(Integer id) {
        return HibernateUtils.get(SeoKeyword.class, id);
    }

    public boolean hasInstance(String url) {
        return HibernateUtils.getCount(SeoKeyword.class, "url='" +
                StringEscapeUtils.escapeSql(url) + "'") > 0;
    }

    public void delete(Integer id) {
        HibernateUtils.delete(SeoKeyword.class, id);
    }

    public void add(String url, String word) {
        SeoKeyword seoKeyword = new SeoKeyword();
        seoKeyword.setUrl(url);
        seoKeyword.setWord(word);
        HibernateUtils.save(seoKeyword);
    }

    public void update(Integer id, String url, String word) {
        SeoKeyword seoKeyword = getInstance(id);
        seoKeyword.setUrl(url);
        seoKeyword.setWord(word);
        HibernateUtils.update(seoKeyword);
    }
}
