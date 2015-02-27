package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityPriceDao;
import com.qinyuan15.crawler.dao.HibernateUtil;

/**
 * Snapshot class of Commodity
 * Created by qinyuan on 15-2-27.
 */
public class CommoditySnapshot extends CommoditySimpleSnapshot {
    private Double price;
    private Branch branch;

    public CommoditySnapshot(Commodity commodity, ImageDownloader imageDownloader,
                             String localAddress) {
        super(commodity, imageDownloader, localAddress);
        this.price = new CommodityPriceDao().getCurrentPrice(commodity.getId());
        this.branch = HibernateUtil.get(Branch.class, commodity.getBranchId());
    }

    public Double getPrice() {
        return price;
    }

    public Branch getBranch() {
        return branch;
    }
}
