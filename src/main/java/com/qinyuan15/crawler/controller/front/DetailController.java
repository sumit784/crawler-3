package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Detail page
 * Created by qinyuan on 15-2-17.
 */
@Controller
public class DetailController extends ImageController {
    @RequestMapping("/detail")
    public String index(ModelMap model, @RequestParam(value = "id", required = true) Integer id) {
        if (!isPositive(id)) {
            return BLANK;
        }

        // commodity
        Commodity commodity = new CommodityDao().getInstance(id);
        model.addAttribute("commodity", commodity);

        // commodity picture
        CommodityPictureDao pictureDao = new CommodityPictureDao();
        List<CommodityPicture> pictures = pictureDao.getInstances(id);
        model.addAttribute("pictures", parseCommodityPictureUrls(pictures));
        List<CommodityPicture> detailPictures = pictureDao.getDetailInstances(id);
        model.addAttribute("detailPictures", parseCommodityPictureUrls(detailPictures));

        // branch
        Branch branch = commodity.getBranch();
        model.addAttribute("branch", adjustBranch(branch));

        // shoppe
        //model.addAttribute("shoppes", new ShoppeDao().getInstances(branch.getId()));

        addJs("list");
        addJs("commodity-parameters");
        addJs("lib/jsutils/jsutils");
        addJs("lib/linecharts/raphael-min");
        addJs("lib/linecharts/linecharts");

        setTitle("商品明细");

        return "detail";
    }
}
