package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshot;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshotBuilder;
import com.qinyuan15.crawler.dao.AppConfig;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.security.SecurityUtils;
import com.qinyuan15.crawler.ui.PaginationAttributeAdder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Admin page
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminController extends ImageController {
    @RequestMapping("/admin")
    public String index(ModelMap model,
                        @RequestParam(value = "keyWord", required = false) String keyWord) {
        CommodityDao.Factory factory = CommodityDao.factory().setKeyWord(keyWord);

        // security
        if (!SecurityUtils.isSupperAdmin()) {
            Integer userId = SecurityUtils.getUserId();
            factory.setUserId(userId);
        }

        AppConfig appConfig = getAppConfig();
        new PaginationAttributeAdder(factory, request)
                .setPageSize(appConfig.getAdminPaginationCommoditySize())
                .setVisibleButtonSize(appConfig.getAdminPaginationButtonSize())
                .setRowItemCountName("commodityCount")
                .add();

        @SuppressWarnings("unchecked")
        List<Commodity> commodities = (List) request.getAttribute(
                PaginationAttributeAdder.DEFAULT_ROW_ITEMS_NAME);
        List<CommoditySimpleSnapshot> snapshots = new CommoditySimpleSnapshotBuilder().build(
                commodities, pictureUrlConverter);
        model.addAttribute("commodities", snapshots);
        model.addAttribute("keyWord", keyWord);
        addIEJs("admin-ie-patch");
        setTitle("商品管理");

        return "admin";
    }
}
