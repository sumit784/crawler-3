package com.qinyuan15.crawler.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Dao of AppConfigFootLink
 * Created by qinyuan on 15-4-13.
 */
public class AppConfigFootLinkDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppConfigFootLink.class);

    public AppConfigFootLink getInstance(Integer id) {
        return HibernateUtils.get(AppConfigFootLink.class, id);
    }

    public List<AppConfigFootLink> getInstances() {
        return HibernateUtils.getList(AppConfigFootLink.class);
    }

    public void clear() {
        HibernateUtils.deleteAll(AppConfigFootLink.class);
    }

    public void add(String path, String link) {
        AppConfigFootLink image = new AppConfigFootLink();
        image.setId(HibernateUtils.getMaxId(AppConfigFootLink.class) + 1);
        image.setText(path);
        image.setLink(link);
        HibernateUtils.save(image);
    }

    public void edit(Integer id, String text, String link) {
        AppConfigFootLink image = getInstance(id);
        if (image == null) {
            LOGGER.error("fail to update AppConfigFootLink instance whose id is {}", id);
        } else {
            image.setText(text);
            image.setLink(link);
            HibernateUtils.update(image);
        }
    }

    public void delete(Integer id) {
        HibernateUtils.delete(AppConfigFootLink.class, id);
    }
}
