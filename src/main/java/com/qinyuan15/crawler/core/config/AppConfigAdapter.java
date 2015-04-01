package com.qinyuan15.crawler.core.config;

import com.google.common.base.Joiner;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.dao.AppConfig;

import java.util.List;

/**
 * Adjust url of AppConfig
 * Created by qinyuan on 15-2-25.
 */
public class AppConfigAdapter {
    private PictureUrlConverter pictureUrlConverter;

    public AppConfigAdapter(PictureUrlConverter pictureUrlConverter) {
        this.pictureUrlConverter = pictureUrlConverter;
    }

    public AppConfig adjust(AppConfig appConfig) {
        appConfig.setGlobalLogo(pictureUrlConverter.pathToUrl(appConfig.getGlobalLogo()));
        appConfig.setGlobalBanner(pictureUrlConverter.pathToUrl(appConfig.getGlobalBanner()));
        appConfig.setIndexHeadPoster(pictureUrlConverter.pathToUrl(appConfig.getIndexHeadPoster()));
        appConfig.setIndexFootPoster(pictureUrlConverter.pathToUrl(appConfig.getIndexFootPoster()));
        appConfig.setBranchRankImage(pictureUrlConverter.pathToUrl(appConfig.getBranchRankImage()));
        appConfig.setNoFoundImage(pictureUrlConverter.pathToUrl(appConfig.getNoFoundImage()));

        List<String> images = appConfig.getDetailImagesList();
        for (int i = 0; i < images.size(); i++) {
            images.set(i, pictureUrlConverter.pathToUrl(images.get(i)));
        }
        appConfig.setDetailImages(Joiner.on(AppConfig.IMAGE_SEPARATOR).join(images));

        return appConfig;
    }
}
