package com.qinyuan15.crawler.core.image;

import com.qinyuan15.crawler.dao.CommodityPicture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to convert commodity picture url
 * Created by qinyuan on 15-2-24.
 */
public class PictureUrlConverterBackup {
    private final static Logger LOGGER = LoggerFactory.getLogger(PictureUrlConverterBackup.class);
    private ImageDownloader imageDownloader;
    private String localAddress;
    private String urlPrefix = "http://";
    private String context = "images";
    private ThumbnailType thumbnailType = ThumbnailType.NONE;
    private ImageSize filterSize;

    public PictureUrlConverterBackup(ImageDownloader imageDownloader, String localAddress) {
        this.imageDownloader = imageDownloader;
        this.localAddress = localAddress;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public PictureUrlConverterBackup setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
        return this;
    }

    public PictureUrlConverterBackup setFilterSize(ImageSize filterSize) {
        this.filterSize = filterSize;
        return this;
    }

    public PictureUrlConverterBackup setThumbnailType(ThumbnailType thumbnailType) {
        this.thumbnailType = thumbnailType;
        return this;
    }

    public String urlToPath(String url) {
        if (!StringUtils.hasText(url) || !url.startsWith(this.urlPrefix + this.localAddress)) {
            return url;
        }

        return url.replace(this.urlPrefix + this.localAddress, this.imageDownloader.getSaveDir());
    }

    public String pathToUrl(String path) {
        if (!StringUtils.hasText(path) || !path.startsWith(imageDownloader.getSaveDir())) {
            return path;
        }


        try {
            if (this.filterSize != null) {
                ImageParser imageParser = new ImageParser(path);
                if (imageParser.getHeight() < this.filterSize.height &&
                        imageParser.getWidth() < filterSize.width) {
                    return null;
                }
            }

            ThumbnailBuilder thumbnail = new ThumbnailBuilder();
            switch (this.thumbnailType) {
                case SMALL:
                    path = thumbnail.getSmall(path);
                    break;
                case MIDDLE:
                    path = thumbnail.getMiddle(path);
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("fail to convert path {} to url, info: {}", path, e);
            return null;
        }

        path = path.replace(imageDownloader.getSaveDir(), "");
        if (this.context != null) {
            path = "/" + this.context + "/" + path;
        }
        path = path.replace("//", "/");
        return this.urlPrefix + this.localAddress + path;
    }

    public List<String> pathsToUrls(List<CommodityPicture> commodityPictures) {
        List<String> pictures = new ArrayList<>();
        for (CommodityPicture commodityPicture : commodityPictures) {
            String url = this.pathToUrl(commodityPicture.getUrl());
            if (url != null) {
                pictures.add(url);
            }
        }
        return pictures;
    }
}
