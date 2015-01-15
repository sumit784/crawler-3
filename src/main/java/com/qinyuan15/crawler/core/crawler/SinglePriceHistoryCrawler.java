package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.core.html.ComposableCommodityPageParser;
import com.qinyuan15.crawler.core.http.HttpClientWrapper;
import com.qinyuan15.crawler.core.http.proxy.ProxyPool;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Grub price of single commodity, then save the price history of it to database
 * Created by qinyuan on 15-1-1.
 */
class SinglePriceHistoryCrawler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SinglePriceHistoryCrawler.class);

    private ProxyPool proxyPool;
    private ComposableCommodityPageParser commodityPageParser;
    private ImageDownloader imageDownloader;

    public SinglePriceHistoryCrawler(ComposableCommodityPageParser commodityPageParser,
                                     ImageDownloader imageDownloader) {
        this.commodityPageParser = commodityPageParser;
        this.imageDownloader = imageDownloader;
    }

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
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
        Proxy proxy = null;
        if (this.proxyPool != null) {
            proxy = proxyPool.next();
        }

        HttpClientWrapper client = new HttpClientWrapper();
        client.setProxy(proxy);
        try {
            LOGGER.info("prepare to save price history of {} with proxy {}", url, proxy);
            String html = client.getContent(url);
            if (proxyPool != null) {
                proxyPool.updateSpeed(proxy, client.getLastConnectTime());
            }
            commodityPageParser.setWebUrl(url);
            commodityPageParser.setHTML(html);
            Map<Date, Double> priceHistory = commodityPageParser.getPriceHistory();
            if (priceHistory == null) {
                LOGGER.info("can not get priceHistory from url {}", url);
            } else {
                LOGGER.info("save price history of {}", url);
                for (Map.Entry<Date, Double> entry : priceHistory.entrySet()) {
                    savePriceRecord(entry.getKey(), entry.getValue(), commodity.getId());
                }
            }

            CommodityPictureDao dao = new CommodityPictureDao();
            if (dao.hasPicture(commodity.getId())) {
                LOGGER.info("commodity {} already has picture, give up downloading picture",
                        commodity.getId());
            } else {
                List<String> savePaths = imageDownloader.save(commodityPageParser.getImageUrls());
                dao.save(commodity.getId(), savePaths);
            }
        } catch (Exception e) {
            LOGGER.error("fail to fetch {} with proxy {}: {}", url, proxy, e);
            if (proxyPool != null) {
                proxyPool.updateSpeed(proxy, Integer.MAX_VALUE);
            }
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
