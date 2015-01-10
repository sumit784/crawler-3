package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.dao.Commodity;

/**
 * Pool to store Commodity objects
 * Created by qinyuan on 15-1-2.
 */
public interface CommodityPool {
    Commodity next();
}
