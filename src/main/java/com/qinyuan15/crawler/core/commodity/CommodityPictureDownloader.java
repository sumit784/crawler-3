package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.CommodityPictureDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class to download commodity picture and save them to database
 * Created by qinyuan on 15-2-24.
 */
public class CommodityPictureDownloader {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommodityPictureDownloader.class);
    private ImageDownloader imageDownloader;

    public CommodityPictureDownloader(ImageDownloader imageDownloader) {
        this.imageDownloader = imageDownloader;
    }

    public void saveIfNotExist(Integer commodityId, List<String> imageUrls) {
        CommodityPictureDao dao = new CommodityPictureDao();
        if (dao.hasPicture(commodityId)) {
            LOGGER.info("commodity {} already has picture, give up downloading picture",
                    commodityId);
        } else {
            save(commodityId, imageUrls);
        }
    }

    public void save(Integer commodityId, List<String> imageUrls) {
        CommodityPictureDao dao = new CommodityPictureDao();
        List<String> savePaths = imageDownloader.save(imageUrls);
        dao.save(commodityId, savePaths);
        LOGGER.info("save {} picture of commodity {}", savePaths.size(), commodityId);
    }

    public void saveDetail(Integer commodityId, List<String> imageUrls) {
        CommodityPictureDao dao = new CommodityPictureDao();
        List<String> savePaths = imageDownloader.save(imageUrls);
        dao.saveDetail(commodityId, savePaths);
        LOGGER.info("save {} picture of commodity {}", savePaths.size(), commodityId);
    }

    public void clearAndSave(Integer commodityId, List<String> imageUrls) {
        CommodityPictureDao dao = new CommodityPictureDao();
        dao.deleteInstances(commodityId);
        save(commodityId, imageUrls);
    }

    public void clearAndSaveDetail(Integer commodityId, List<String> imageUrls) {
        CommodityPictureDao dao = new CommodityPictureDao();
        dao.deleteDetailInstances(commodityId);
        saveDetail(commodityId, imageUrls);
    }
}

