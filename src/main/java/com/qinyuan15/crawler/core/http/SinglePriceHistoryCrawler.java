package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.html.CommodityPageParser;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.HibernateUtil;
import com.qinyuan15.crawler.dao.PriceRecord;
import com.qinyuan15.crawler.dao.Proxy;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.Map;

/**
 * Grub price of single commodity
 * Created by qinyuan on 15-1-1.
 */
class SinglePriceHistoryCrawler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SinglePriceHistoryCrawler.class);

    private ProxyPool proxyPool;
    private CommodityPageParser commodityPageParser;

    public SinglePriceHistoryCrawler(CommodityPageParser commodityPageParser) {
        this.commodityPageParser = commodityPageParser;
    }

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public void save(Commodity commodity) {
        String url = commodity.getUrl();
        Proxy proxy = null;
        if (this.proxyPool != null) {
            proxy = proxyPool.next();
        }

        HttpClientWrapper client = new HttpClientWrapper();
        client.setProxy(proxy);
        try {
            String html = client.getContent(url);
            commodityPageParser.setHTML(html);
            Map<Date, Double> priceHistory = commodityPageParser.getPriceHistory();
            for (Map.Entry<Date, Double> entry : priceHistory.entrySet()) {
                savePriceRecord(entry.getKey(), entry.getValue(), commodity.getId());
            }
            commodityPageParser.getPriceHistory();
            //PriceHistory priceHistory = new PriceHistory();

            /*
            Commodity commodity = commodityPageParser.getCommodity();

            Session session = HibernateUtil.getSession();
            session.save(commodity);
            HibernateUtil.commit(session);
            */
        } catch (Exception e) {
            LOGGER.error("fail to fetch {} with proxy: {}", url, proxy, e.getMessage());
            if (proxy != null) {
                proxy.setSpeed(Integer.MAX_VALUE);
            }
            proxyPool.updateSpeed(proxy);
        }
    }

    private void savePriceRecord(Date date, Double price, int commodityId) {
        Session session = HibernateUtil.getSession();
        if (session.createQuery("FROM PriceRecord WHERE recordtime=? AND commodityId=?")
                .setDate(0, date).setInteger(1, commodityId).list().size() == 0) {
            PriceRecord record = new PriceRecord();
            record.setRecordTime(date);
            record.setPrice(price);
            record.setCommodityId(commodityId);
            session.save(record);
        }
        HibernateUtil.commit(session);
    }
}
