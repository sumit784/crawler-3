package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.core.image.ThumbnailBuilder;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityPicture;
import com.qinyuan15.crawler.dao.CommodityPictureDao;

/**
 * Simple snapshot class of Commodity
 * Created by qinyuan on 15-2-27.
 */
public class CommoditySimpleSnapshot {
    private int id;
    private String name;
    private String picture;
    private Boolean active;

    public CommoditySimpleSnapshot(Commodity commodity, PictureUrlConverter pictureUrlConverter) {
        this.id = commodity.getId();
        this.name = commodity.getName();
        this.active = commodity.getActive();
        ThumbnailBuilder thumbnailBuilder = new ThumbnailBuilder();
        CommodityPicture commodityPicture = new CommodityPictureDao().getFirstInstance(commodity.getId());
        if (commodityPicture != null) {
            String path = thumbnailBuilder.getMiddle(commodityPicture.getUrl());
            this.picture = pictureUrlConverter.pathToUrl(path);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public Boolean getActive() {
        return active;
    }
}
