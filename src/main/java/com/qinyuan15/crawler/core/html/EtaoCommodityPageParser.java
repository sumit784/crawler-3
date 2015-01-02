package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Commodity;

/**
 * Class to parse page of http://s.etao.com
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParser extends AbstractCommodityPageParser {

    @Override
    public Commodity getCommodity() {
        System.out.println(this.html);
        return null;
    }
}
