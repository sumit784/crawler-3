package com.qinyuan15.crawler.core.image;

import com.qinyuan15.crawler.dao.CommodityPicture;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to convert commodity picture url
 * Created by qinyuan on 15-2-24.
 */
public class PictureUrlConverter {
    private ImageDownloader imageDownloader;
    private String localAddress;
    private String urlPrefix = "ftp://";
    private ThumbnailType thumbnailType = ThumbnailType.NONE;

    public PictureUrlConverter(ImageDownloader imageDownloader, String localAddress) {
        this.imageDownloader = imageDownloader;
        this.localAddress = localAddress;
    }

    public PictureUrlConverter setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
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

        Thumbnail thumbnail = new Thumbnail();
        switch (this.thumbnailType) {
            case SMALL:
                path = thumbnail.getSmall(path);
                break;
            case MIDDLE:
                path = thumbnail.getMiddle(path);
                break;
        }

        path = path.replaceFirst(imageDownloader.getSaveDir(), "");
        if (!path.startsWith("/")) {
            path += "/" + path;
        }
        return this.urlPrefix + this.localAddress + path;
    }

    public List<String> pathsToUrls(List<CommodityPicture> commodityPictures) {
        List<String> pictures = new ArrayList<String>();
        for (CommodityPicture commodityPicture : commodityPictures) {
            pictures.add(this.pathToUrl(commodityPicture.getUrl()));
        }
        return pictures;
    }
}
