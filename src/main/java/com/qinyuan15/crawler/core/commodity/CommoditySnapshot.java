package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.core.branch.BranchUrlAdapter;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
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

    public CommoditySnapshot(Commodity commodity, PictureUrlConverter pictureUrlConverter) {
        super(commodity, pictureUrlConverter);
        this.price = new CommodityPriceDao().getCurrentPrice(commodity.getId());
        this.branch = HibernateUtil.get(Branch.class, commodity.getBranchId());
        BranchUrlAdapter adapter = new BranchUrlAdapter(pictureUrlConverter);
        adapter.adjust(this.branch);
    }

    public Double getPrice() {
        return price;
    }

    public Branch getBranch() {
        return branch;
    }
}
