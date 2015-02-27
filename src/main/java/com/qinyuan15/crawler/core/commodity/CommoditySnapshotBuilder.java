package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.Commodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to build CommoditySnapshot
 * Created by qinyuan on 15-2-27.
 */
public class CommoditySnapshotBuilder {
    public List<CommoditySnapshot> build(List<Commodity> commodities,
                                                 ImageDownloader imageDownloader,
                                                 String localAddress) {
        List<CommoditySnapshot> snapshots = new ArrayList<CommoditySnapshot>();
        for (Commodity commodity : commodities) {
            CommoditySnapshot snapshot = new CommoditySnapshot(
                    commodity, imageDownloader, localAddress);
            snapshots.add(snapshot);
        }
        return snapshots;
    }
}
