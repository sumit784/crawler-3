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
    //private String detailImages;
    private String globalLogo;
    private String globalBanner;
    private String indexHeadPoster;
    private String indexFootPoster;
    private String indexFootPosterLink;
    private String branchRankImage;
    private String noFoundImage;
    private String noFoundText;
    private Integer adminPaginationCommoditySize;
    private Integer adminPaginationButtonSize;
    private Integer maxCommodityPictureSize;
    private Integer maxCommodityDetailPictureSize;
    private Integer relatedCommoditySize;

    public Integer getRelatedCommoditySize() {
        return relatedCommoditySize;
    }

    public void setRelatedCommoditySize(Integer relatedCommoditySize) {
        this.relatedCommoditySize = relatedCommoditySize;
    }

    public void setMaxCommodityPictureSize(Integer maxCommodityPictureSize) {
        this.maxCommodityPictureSize = maxCommodityPictureSize;
    }

    public void setMaxCommodityDetailPictureSize(Integer maxCommodityDetailPictureSize) {
        this.maxCommodityDetailPictureSize = maxCommodityDetailPictureSize;
    }

    public Integer getMaxCommodityPictureSize() {
        return maxCommodityPictureSize;
    }

    public Integer getMaxCommodityDetailPictureSize() {
        return maxCommodityDetailPictureSize;
    }

    public Integer getAdminPaginationCommoditySize() {
        return adminPaginationCommoditySize;
    }

    public Integer getAdminPaginationButtonSize() {
        return adminPaginationButtonSize;
    }

    public void setAdminPaginationCommoditySize(Integer adminPaginationCommoditySize) {
        this.adminPaginationCommoditySize = adminPaginationCommoditySize;
    }

    public void setAdminPaginationButtonSize(Integer adminPaginationButtonSize) {
        this.adminPaginationButtonSize = adminPaginationButtonSize;
    }

    public void setIndexFootPosterLink(String indexFootPosterLink) {
        this.indexFootPosterLink = indexFootPosterLink;
    }

    public void setBranchRankImage(String branchRankImage) {
        this.branchRankImage = branchRankImage;
    }

    public void setNoFoundImage(String noFoundImage) {
        this.noFoundImage = noFoundImage;
    }

    public void setNoFoundText(String noFoundText) {
        this.noFoundText = noFoundText;
    }

    public void setGlobalLogo(String globalLogo) {
        this.globalLogo = globalLogo;
    }

    public void setGlobalBanner(String globalBanner) {
        this.globalBanner = globalBanner;
    }

    public void setIndexHeadPoster(String indexHeadPoster) {
        this.indexHeadPoster = indexHeadPoster;
    }

    public void setIndexFootPoster(String indexFootPoster) {
        this.indexFootPoster = indexFootPoster;
    }

    public String getGlobalLogo() {
        return globalLogo;
    }

    public String getGlobalBanner() {
        return globalBanner;
    }

    public String getDetailText() {
        return detailText;
    }

    public String getIndexHeadPoster() {
        return indexHeadPoster;
    }

    public String getIndexFootPoster() {
        return indexFootPoster;
    }

    /*public String getDetailImages() {
        return detailImages;
    }*/

    public String getIndexFootPosterLink() {
        return indexFootPosterLink;
    }

    public String getBranchRankImage() {
        return branchRankImage;
    }

    public String getNoFoundImage() {
        return noFoundImage;
    }

    public String getNoFoundText() {
        return noFoundText;
    }

    public List<String> getDetailImagesList() {
        return new ArrayList<>();
        /*
        if (StringUtils.hasText(detailImages)) {
            return Lists.newArrayList(detailImages.split(IMAGE_SEPARATOR));
        } else {
            return new ArrayList<>();
        }
        */
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    /*public void setDetailImages(String detailImages) {
        this.detailImages = detailImages;
    }*/

    public List<String> getNoFoundTexts() {
        if (!StringUtils.hasText(noFoundText)) {
            return new ArrayList<>();
        }
        return Lists.newArrayList(noFoundText.split("\n"));
    }
}
