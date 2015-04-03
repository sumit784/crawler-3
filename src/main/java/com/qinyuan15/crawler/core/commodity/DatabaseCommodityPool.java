package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.HibernateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Pool to store Commodity that get from database
 * Created by qinyuan on 15-1-9.
 */
public class DatabaseCommodityPool implements CommodityPool {

    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseCommodityPool.class);
    private final static int PAGE_SIZE = 100;
    private int pointer = 0;
    private List<Commodity> commodities;

    @SuppressWarnings("unchecked")
    @Override
    public synchronized Commodity next() {
        long commodityCount = this.size();

        if (pointer >= commodityCount) {
            return null;
        }

        if (pointer % PAGE_SIZE == 0) {
            this.commodities = HibernateUtils.getList("FROM Commodity WHERE active=true ORDER BY id DESC",
                    pointer, PAGE_SIZE);
        }

        Commodity commodity = this.commodities.get(pointer % PAGE_SIZE);
        pointer++;
        return commodity;
    }

    public long size() {
        return HibernateUtils.getCount(Commodity.class, "active=true");
    }

    @Override
    public synchronized void reset() {
        LOGGER.info("Commodity Pool reset");
        this.pointer = 0;
    }
}