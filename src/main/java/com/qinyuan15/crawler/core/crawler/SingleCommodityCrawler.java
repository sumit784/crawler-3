package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.html.CommodityPageParser;
import com.qinyuan15.crawler.core.html.ComposableCommodityPageParser;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import com.qinyuan15.crawler.core.http.HttpClientWrapper;
import com.qinyuan15.crawler.dao.*;
import com.qinyuan15.crawler.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.Map;

/**
 * Grub price of single commodity, then save the price history of it to database
 * Created by qinyuan on 15-1-1.
 */
class SingleCommodityCrawler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SingleCommodityCrawler.class);
    private final static ExpireCommodityRecorder expireCommodityRecorder = new ExpireCommodityRecorder();

    private HttpClientPool httpClientPool;
    private ComposableCommodityPageParser commodityPageParser;
    private CommodityDao commodityDao = new CommodityDao();
    private CommodityCrawlLogDao crawlLogDao = new CommodityCrawlLogDao();

    public SingleCommodityCrawler(ComposableCommodityPageParser commodityPageParser,
                                  HttpClientPool httpClientPool) {
        this.commodityPageParser = commodityPageParser;
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
        Integer commodityId = commodity.getId();
        try {
            LOGGER.info("prepare to save price history of {}", url);

            String html = client.getContent(url);
            commodityPageParser.setWebUrl(url);
            commodityPageParser.setHTML(html);

            if (!validateExpiration(commodityPageParser, commodityId)) {
                return;
            }

            if (!updatePriceHistory(commodityPageParser, commodityId)) {
                LOGGER.info("can not get priceHistory from url {}, html contents: {}",
                        url, html);
                client.feedbackRejection();
            } else {
                LOGGER.info("save price history of {}", url);
            }

            updateSales(commodityPageParser, commodity);

            // TODO comment out this someday
            updateOtherCommodityInfo(commodity.getId());
        } catch (Exception e) {
            LOGGER.error("fail to fetch price history of {}: {}", url, e);
            crawlLogDao.logFail(commodityId, "未知错误");
        }
    }

    private boolean updatePriceHistory(CommodityPageParser commodityPageParser, int commodityId) {
        Map<Date, Double> priceHistory = commodityPageParser.getPriceHistory();
        if (priceHistory == null) {
            crawlLogDao.logFail(commodityId, "网页解析失败");
            return false;
        } else {
            for (Map.Entry<Date, Double> entry : priceHistory.entrySet()) {
                savePriceRecord(entry.getKey(), entry.getValue(), commodityId);
            }
            crawlLogDao.logSuccess(commodityId, "价格记录抓取成功");
            return true;
        }
    }

    private void updateSales(CommodityPageParser commodityPageParser, Commodity commodity) {
        Integer sales = commodityPageParser.getSales();
        if (sales == null) {
            LOGGER.error("fail to parse sales of commodity {}", commodity.getName());
        } else {
            commodityDao.updateSales(commodity.getId(), sales);
            if (!sales.equals(commodity.getSales())) {
                LOGGER.info("update sales of commodity {} to {}", commodity.getName(),
                        sales);
            }
        }
    }

    private boolean validateExpiration(CommodityPageParser commodityPageParser, int commodityId) {
        if (commodityPageParser.isExpire()) {
            String logInfo = "网已过期，第" + (expireCommodityRecorder.getFailTimes(commodityId) + 1)
                    + "次发现该网页过期";
            if (expireCommodityRecorder.reachMaxFailTimes(commodityId)) {
                commodityDao.deactivate(commodityId);
                logInfo += "，自动标记为过期网页";
            }
            crawlLogDao.logFail(commodityId, logInfo);
            return false;
        } else {
            expireCommodityRecorder.clear(commodityId);
            return true;
        }
    }

    private void updateOtherCommodityInfo(Integer commodityId) {
        commodityDao.updateDiscoverTime(commodityId);
        commodityDao.updatePrice(commodityId);
        commodityDao.updateInLowPrice(commodityId);
    }

    private void savePriceRecord(Date date, Double price, int commodityId) {
        if (!PriceRecordDao.factory().setCommodityId(commodityId)
                .setRecordTime(date).hasInstance()) {
            PriceRecord record = new PriceRecord();
            record.setRecordTime(date);
            record.setPrice(price);
            record.setCommodityId(commodityId);
            record.setGrabTime(DateUtils.now());
            HibernateUtils.save(record);
        }
    }
}
