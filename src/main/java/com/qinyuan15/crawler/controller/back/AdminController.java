package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshot;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshotBuilder;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Admin page
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminController extends BaseController {

    @Autowired
    private ImageDownloader imageDownloader;

    @RequestMapping("/admin")
    public String index(ModelMap model) {
        List<Commodity> commodities = CommodityDao.factory().orderByActive().getInstances();
        List<CommoditySimpleSnapshot> snapshots = new CommoditySimpleSnapshotBuilder().build(
                commodities, imageDownloader, request.getLocalAddr());
        model.addAttribute("commodities", snapshots);
        setTitle("商品管理");

        return "admin";
    }
}
