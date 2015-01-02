package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.html.CommodityPageParser;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.HibernateUtil;
import com.qinyuan15.crawler.dao.Proxy;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qinyuan on 15-1-1.
 */
class SingleCommodityCrawler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SingleCommodityCrawler.class);

    private ProxyPool proxyPool;
    private CommodityPageParser commodityPageParser;

    public SingleCommodityCrawler(CommodityPageParser commodityPageParser) {
        this.commodityPageParser = commodityPageParser;
    }

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public void save(String url) {
        Proxy proxy = null;
        if (this.proxyPool != null) {
            proxy = proxyPool.next();
        }

        HttpClientWrapper client = new HttpClientWrapper();
        client.setProxy(proxy);
        try {
            String html = client.getContent(url);
            commodityPageParser.setHTML(html);
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
}
