package com.qinyuan15.crawler.dao;


import com.google.common.base.Joiner;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Dao of AppConfig
 * Created by qinyuan on 15-3-22.
 */
public class AppConfigDao {
    public AppConfig getInstance() {
        return (AppConfig) HibernateUtils.getFirstItem("From " + AppConfig.class.getSimpleName());
    }

    public void update(AppConfig appConfig) {
        HibernateUtils.update(appConfig);
    }

    private void updateImage(AppConfig appConfig, List<String> images) {
        appConfig.setDetailImages(Joiner.on(AppConfig.IMAGE_SEPARATOR).join(images));
        update(appConfig);
    }

    public void addImage(String imagePath) {
        if (!StringUtils.hasText(imagePath)) {
            return;
        }

        AppConfig config = this.getInstance();
        List<String> images = config.getDetailImagesList();
        images.add(imagePath);
        updateImage(config, images);
    }

    public void editImage(Integer index, String imagePath) {
        if (!StringUtils.hasText(imagePath)) {
            return;
        }
        if (index == null || index < 0) {
            return;
        }

        AppConfig config = this.getInstance();
        List<String> images = config.getDetailImagesList();
        if (index >= images.size()) {
            return;
        }

        images.set(index, imagePath);
        updateImage(config, images);
    }

    public void deleteImage(Integer index) {
        if (index == null || index < 0) {
            return;
        }
        AppConfig config = this.getInstance();
        List<String> images = config.getDetailImagesList();
        if (index >= images.size()) {
            return;
        }

        images.remove(index.intValue());
        updateImage(config, images);
    }
}
