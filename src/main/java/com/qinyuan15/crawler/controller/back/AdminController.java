package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.dao.CommodityPicture;
import com.qinyuan15.crawler.dao.CommodityPictureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
        model.addAttribute("commodities", getCommodities(1));
        setTitle("商品管理");

        return "admin";
    }

    private List<SimpleCommodity> getCommodities(int adminId) {
        List<Commodity> commodities = CommodityDao.factory().getInstances();
        List<SimpleCommodity> simpleCommodities = new ArrayList<SimpleCommodity>();
        CommodityPictureDao commodityPictureDao = new CommodityPictureDao();
        PictureUrlConverter urlConverter = new PictureUrlConverter(
                imageDownloader, request.getLocalAddr());
        for (Commodity commodity : commodities) {
            SimpleCommodity simpleCommodity = new SimpleCommodity();

            simpleCommodity.id = commodity.getId();
            simpleCommodity.name = commodity.getName();
            CommodityPicture commodityPicture = commodityPictureDao.getFirstInstance(commodity.getId());
            if (commodityPicture != null) {
                simpleCommodity.picture = urlConverter.pathToUrl(commodityPicture.getUrl());
            }

            simpleCommodities.add(simpleCommodity);
        }
        return simpleCommodities;
    }

    public static class SimpleCommodity {
        int id;
        String name;
        String picture;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPicture() {
            return picture;
        }
    }
}
