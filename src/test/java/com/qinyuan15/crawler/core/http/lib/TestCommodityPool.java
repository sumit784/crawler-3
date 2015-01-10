package com.qinyuan15.crawler.core.http.lib;

import com.qinyuan15.crawler.core.commodity.CommodityPool;
import com.qinyuan15.crawler.dao.Commodity;

/**
 * A CommodityPool for testing
 * Created by qinyuan on 15-1-2.
 */
public class TestCommodityPool implements CommodityPool {
    @Override
    public Commodity next() {
        Commodity commodity = new Commodity();
        commodity.setId(1);
        commodity.setName("testCommodity");
        commodity.setUrl("http://s.etao.com/detail/40780735321.html?tbpm=20150102");
        return commodity;
    }
}
