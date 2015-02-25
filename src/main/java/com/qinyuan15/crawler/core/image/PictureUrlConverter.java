package com.qinyuan15.crawler.core.image;

import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.CommodityPicture;

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

    public PictureUrlConverter(ImageDownloader imageDownloader, String localAddress) {
        this.imageDownloader = imageDownloader;
        this.localAddress = localAddress;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String pathToUrl(String path) {
        if (path == null || !path.startsWith(imageDownloader.getSaveDir())) {
            return path;
        }

        return path.replaceFirst(imageDownloader.getSaveDir(), this.urlPrefix + this.localAddress + "/");
    }

    public List<String> pathsToUrls(List<CommodityPicture> commodityPictures) {
        List<String> pictures = new ArrayList<String>();
        for (CommodityPicture commodityPicture : commodityPictures) {
            pictures.add(this.pathToUrl(commodityPicture.getUrl()));
        }
        return pictures;
    }
}
