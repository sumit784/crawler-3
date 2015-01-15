package com.qinyuan15.crawler.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Dao of CommodityPicture
 * Created by qinyuan on 15-1-15.
 */
public class CommodityPictureDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommodityPictureDao.class);

    public void save(Integer commodityId, List<String> urls) {
        for (String url : urls) {
            if (HibernateUtil.getCount("CommodityPicture",
                    "commodityId=" + commodityId + " AND url='" + url + "'") > 0) {
                LOGGER.warn("picture {} of commodityId {} already exists, give up saving it to database",
                        url, commodityId);
                continue;   // If picture already exists, skip it
            }
            CommodityPicture picture = new CommodityPicture();
            picture.setCommodityId(commodityId);
            picture.setUrl(url);
            try {
                HibernateUtil.save(picture);
                LOGGER.info("save picture {} of commodityId {} to database",
                        url, commodityId);
            } catch (Exception e) {
                LOGGER.error("fail to save picture {} of commodityId {} to database: {}",
                        url, commodityId, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<CommodityPicture> getInstances(Integer commodityId) {
        String hql = "FROM CommodityPicture WHERE commodityId=" + commodityId;
        return HibernateUtil.getList(hql);
    }

    /**
     * Judge whether one commodity has picture
     *
     * @param commodityId id of commodity
     * @return if has picture, return true, else return false
     */
    @SuppressWarnings("unchecked")
    public boolean hasPicture(Integer commodityId) {
        return HibernateUtil.getCount("CommodityPicture", "commodityId=" + commodityId) > 0;
    }
}
