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
    private boolean randomOrder = true;

    public DatabaseCommodityPool setRandomOrder(boolean randomOrder) {
        this.randomOrder = randomOrder;
        return this;
    }

    @Override
    public synchronized Commodity next() {
        long commodityCount = this.size();

        if (pointer >= commodityCount) {
            return null;
        }

        if (pointer % PAGE_SIZE == 0) {
            String whereClause = "active=true ";
            if (this.randomOrder) {
                whereClause += "ORDER BY rand()";
            } else {
                whereClause += "ORDER BY id DESC";
            }
            this.commodities = HibernateUtils.getList(Commodity.class,
                    whereClause, pointer, PAGE_SIZE);
        }

        int index = pointer % PAGE_SIZE;
        pointer++;
        return index >= this.commodities.size() ? null : this.commodities.get(index);
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