package com.qinyuan15.crawler.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Dao of CommodityPicture
 * Created by qinyuan on 15-1-15.
 */
public class CommodityPictureDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommodityPictureDao.class);

    /**
     * Save normal commodity picture to database
     *
     * @param commodityId id of commodity
     * @param urls        url of commodities
     */
    public void save(Integer commodityId, List<String> urls) {
        save(commodityId, urls, false);
    }

    /**
     * Save detail commodity picture to database
     *
     * @param commodityId id of commodity
     * @param urls        url of commodities
     */
    public void saveDetail(Integer commodityId, List<String> urls) {
        save(commodityId, urls, true);
    }

    /**
     * Save normal/detail commodity picture to database
     *
     * @param commodityId id of commodity
     * @param urls        url of commodities
     * @param detail      detail picture or normal picture
     */
    public void save(Integer commodityId, List<String> urls, boolean detail) {
        for (String url : urls) {
            if (HibernateUtil.getCount("CommodityPicture",
                    "WHERE commodityId=" + commodityId + " AND url='" + url + "'") > 0) {
                LOGGER.warn("picture {} of commodityId {} already exists, give up saving it to database",
                        url, commodityId);
                continue;   // If picture already exists, skip it
            }
            CommodityPicture picture = new CommodityPicture();
            picture.setCommodityId(commodityId);
            picture.setUrl(url);
            picture.setDetail(detail);
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

    public void deleteInstances(Integer commodityId) {
        Session session = HibernateUtil.getSession();
        session.createQuery("DELETE FROM CommodityPicture WHERE commodityId=:commodityId AND detail=false")
                .setInteger("commodityId", commodityId).executeUpdate();
        HibernateUtil.commit(session);
    }

    public void deleteDetailInstances(Integer commodityId) {
        Session session = HibernateUtil.getSession();
        session.createQuery("DELETE FROM CommodityPicture WHERE commodityId=:commodityId AND detail=true")
                .setInteger("commodityId", commodityId).executeUpdate();
        HibernateUtil.commit(session);
    }

    /**
     * Judge whether one commodity has picture
     *
     * @param commodityId id of commodity
     * @return if has picture, return true, else return false
     */
    @SuppressWarnings("unchecked")
    public boolean hasPicture(Integer commodityId) {
        return HibernateUtil.getCount("CommodityPicture", "WHERE commodityId=" + commodityId) > 0;
    }
}
