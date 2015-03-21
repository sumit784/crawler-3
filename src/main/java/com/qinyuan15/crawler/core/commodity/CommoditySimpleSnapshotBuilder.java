package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.dao.Commodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to build commoditySimpleSnapshot
 * Created by qinyuan on 15-2-27.
 */
public class CommoditySimpleSnapshotBuilder {

    public List<CommoditySimpleSnapshot> build(List<Commodity> commodities,
                                               PictureUrlConverter pictureUrlConverter) {
        List<CommoditySimpleSnapshot> snapshots = new ArrayList<>();
        for (Commodity commodity : commodities) {
            CommoditySimpleSnapshot snapshot = new CommoditySimpleSnapshot(
                    commodity, pictureUrlConverter);
            snapshots.add(snapshot);
        }
        return snapshots;
    }
}
