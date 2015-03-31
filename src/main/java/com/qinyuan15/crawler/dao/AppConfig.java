package com.qinyuan15.crawler.dao;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Persist Object of AppConfig
 * Created by qinyuan on 15-3-22.
 */
public class AppConfig extends PersistObject {
    public final static String DEFAULT_ENCODING = "utf-8";
    public final static String VERSION = String.valueOf(System.currentTimeMillis())/* DateUtils.now().toString()*/;
    public final static String IMAGE_SEPARATOR = ",";
    private String detailText;
    private String detailImages;
    private String globalLogo;
    private String globalBanner;
    private String globalBottomPoster;

    public void setGlobalLogo(String globalLogo) {
        this.globalLogo = globalLogo;
    }

    public void setGlobalBanner(String globalBanner) {
        this.globalBanner = globalBanner;
    }

    public void setGlobalBottomPoster(String globalBottomPoster) {
        this.globalBottomPoster = globalBottomPoster;
    }

    public String getGlobalLogo() {
        return globalLogo;
    }

    public String getGlobalBanner() {
        return globalBanner;
    }

    public String getGlobalBottomPoster() {
        return globalBottomPoster;
    }

    public String getDetailText() {
        return detailText;
    }

    public String getDetailImages() {
        return detailImages;
    }

    public List<String> getDetailImagesList() {
        if (StringUtils.hasText(detailImages)) {
            return Lists.newArrayList(detailImages.split(IMAGE_SEPARATOR));
        } else {
            return new ArrayList<>();
        }
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public void setDetailImages(String detailImages) {
        this.detailImages = detailImages;
    }

}
