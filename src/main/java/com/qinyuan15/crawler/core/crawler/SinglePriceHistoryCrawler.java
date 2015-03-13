package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.core.commodity.CommodityPictureDownloader;
import com.qinyuan15.crawler.core.html.ComposableCommodityPageParser;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import com.qinyuan15.crawler.core.http.HttpClientWrapper;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.Map;

/**
 * Grub price of single commodity, then save the price history of it to database
 * Created by qinyuan on 15-1-1.
 */
class SinglePriceHistoryCrawler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SinglePriceHistoryCrawler.class);

    private HttpClientPool httpClientPool;
    private ComposableCommodityPageParser commodityPageParser;
    private ImageDownloader imageDownloader;

    public SinglePriceHistoryCrawler(ComposableCommodityPageParser commodityPageParser,
                                     ImageDownloader imageDownloader, HttpClientPool httpClientPool) {
        this.commodityPageParser = commodityPageParser;
        this.imageDownloader = imageDownloader;
        this.httpClientPool = httpClientPool;
    }

    /**
     * Save price history of certain Commodity to database
     *
     * @param commodity Commodity object to save
     */
    public void save(Commodity commodity) {
        if (this.commodityPageParser == null) {
            LOGGER.info("commodityPageParser is null, nothing to save");
            return;
        }

        String url = commodity.getUrl();
        HttpClientWrapper client = this.httpClientPool.next();
        try {
            LOGGER.info("prepare to save price history of {}", url);
            String html = client.getContent(url);
            commodityPageParser.setWebUrl(url);
            commodityPageParser.setHTML(html);
            if (commodityPageParser.isExpire()) {
                new CommodityDao().deactivate(commodity.getId());
                return;
            }

            Map<Date, Double> priceHistory = commodityPageParser.getPriceHistory();
            if (priceHistory == null) {
                LOGGER.info("can not get priceHistory from url {}, html contents: {}", url, html);
                client.feedbackRejection();
            } else {
                LOGGER.info("save price history of {}", url);
                for (Map.Entry<Date, Double> entry : priceHistory.entrySet()) {
                    savePriceRecord(entry.getKey(), entry.getValue(), commodity.getId());
                }

                // save images
                CommodityPictureDownloader downloader = new CommodityPictureDownloader(imageDownloader);
                downloader.saveIfNotExist(commodity.getId(), commodityPageParser.getImageUrls());

                // save sales and on self time
                CommodityDao commodityDao = new CommodityDao();
                commodityDao.updateSales(commodity.getId(), commodityPageParser.getSales());
                commodityDao.updateOnShelfTime(commodity.getId());
                commodityDao.updatePrice(commodity.getId());
            }
        } catch (Exception e) {
            LOGGER.error("fail to fetch price history of {}: {}", url, e);
        }
    }

    private void savePriceRecord(Date date, Double price, int commodityId) {
        if (!PriceRecordDao.factory().setCommodityId(commodityId)
                .setRecordTime(date).hasInstance()) {
            PriceRecord record = new PriceRecord();
            record.setRecordTime(date);
            record.setPrice(price);
            record.setCommodityId(commodityId);
            record.setGrabTime(DateUtils.now());
            HibernateUtil.save(record);
        }
    }
}
