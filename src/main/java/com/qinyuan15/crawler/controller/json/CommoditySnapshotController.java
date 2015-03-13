package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.commodity.CommoditySnapshot;
import com.qinyuan15.crawler.core.commodity.CommoditySnapshotBuilder;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Controller to query commodity snapshot
 * Created by qinyuan on 15-2-27.
 */
@Controller
public class CommoditySnapshotController extends ImageController {

    @ResponseBody
    @RequestMapping("/json/commoditySnapshot.json")
    public String index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                        @RequestParam(value = "active", required = false) Boolean active) {
        // set default value of active to true
        if (active == null) {
            active = true;
        }

        List<Commodity> commodities = CommodityDao.factory()
                .setCategoryId(categoryId)
                .setActive(active)
                .orderByActive()
                .getInstances();
        List<CommoditySnapshot> snapshots = new CommoditySnapshotBuilder().build(
                commodities, imageDownloader, request.getLocalAddr());
        return toJson(snapshots);
    }
}
