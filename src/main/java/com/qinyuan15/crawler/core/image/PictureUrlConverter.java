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
public class PictureUrlConverter {
    private final static Logger LOGGER = LoggerFactory.getLogger(PictureUrlConverter.class);
    private ImageDownloader imageDownloader;
    private String localAddress;
    private String urlPrefix = "ftp://";
    private ThumbnailType thumbnailType = ThumbnailType.NONE;
    private ImageSize filterSize;

    public PictureUrlConverter(ImageDownloader imageDownloader, String localAddress) {
        this.imageDownloader = imageDownloader;
        this.localAddress = localAddress;
    }

    public PictureUrlConverter setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
        return this;
    }

    public PictureUrlConverter setFilterSize(ImageSize filterSize) {
        this.filterSize = filterSize;
        return this;
    }

    public PictureUrlConverter setThumbnailType(ThumbnailType thumbnailType) {
        this.thumbnailType = thumbnailType;
        return this;
    }

    public String urlToPath(String url) {
        if (!StringUtils.hasText(url) || !url.startsWith(this.urlPrefix + this.localAddress)) {
            return url;
        }

        return url.replaceFirst(this.urlPrefix + this.localAddress, this.imageDownloader.getSaveDir());
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

            Thumbnail thumbnail = new Thumbnail();
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

        path = path.replaceFirst(imageDownloader.getSaveDir(), "");
        if (!path.startsWith("/")) {
            path += "/" + path;
        }
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
