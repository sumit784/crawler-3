package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshot;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshotBuilder;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.security.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Admin page
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminController extends ImageController {

    @RequestMapping("/admin")
    public String index(ModelMap model) {
        CommodityDao.Factory factory = CommodityDao.factory().orderByActive();

        if (!SecurityUtils.isSupperAdmin()) {
            Integer userId = SecurityUtils.getUserId();
            factory.setUserId(userId);
        }

        List<Commodity> commodities = factory.getInstances();
        List<CommoditySimpleSnapshot> snapshots = new CommoditySimpleSnapshotBuilder().build(
                commodities, pictureUrlConverter);
        model.addAttribute("commodities", snapshots);
        setTitle("商品管理");

        return "admin";
    }
}
