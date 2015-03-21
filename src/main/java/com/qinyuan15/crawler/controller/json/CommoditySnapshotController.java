package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.commodity.CommoditySnapshot;
import com.qinyuan15.crawler.core.commodity.CommoditySnapshotBuilder;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
                        @RequestParam(value = "active", required = false) Boolean active,
                        @RequestParam(value = "keyWord", required = false) String keyWord,
                        @RequestParam(value = "branchId", required = false) Integer branchId,
                        @RequestParam(value = "orderField", required = false) String orderField,
                        @RequestParam(value = "orderType", required = false) String orderType,
                        @RequestParam(value = "inLowPrice", required = false) Boolean inLowPrice) {
        // set default value of active to true
        if (active == null) {
            active = true;
        }
        CommodityDao.Factory factory = CommodityDao.factory().setCategoryId(categoryId)
                .setActive(active).setKeyWord(keyWord).setBranchId(branchId)
                .setInLowPrice(inLowPrice).orderByActive();

        if (StringUtils.hasText(orderField) && StringUtils.hasText(orderType)) {
            CommodityDao.Order order = new CommodityDao.Order()
                    .setField(CommodityDao.OrderField.create(orderField))
                    .setType(CommodityDao.OrderType.create(orderType));
            factory.setOrder(order);
        }

        List<Commodity> commodities = factory.getInstances();
        List<CommoditySnapshot> snapshots = new CommoditySnapshotBuilder().build(
                commodities, pictureUrlConverter);
        return toJson(snapshots);
    }
}
