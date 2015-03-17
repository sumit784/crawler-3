package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.core.image.ThumbnailType;
import com.qinyuan15.crawler.core.share.ShareLinks;
import com.qinyuan15.crawler.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Detail page
 * Created by qinyuan on 15-2-17.
 */
@Controller
public class DetailController extends ImageController {
    @Autowired
    private ShareLinks shareLinks;

    @RequestMapping("/detail")
    public String index(ModelMap model, @RequestParam(value = "id", required = true) Integer id) {
        if (!isPositive(id)) {
            return BLANK;
        }

        // commodity
        Commodity commodity = new CommodityDao().getInstance(id);
        if (commodity == null) {
            return BLANK;
        }

        model.addAttribute("commodity", commodity);

        // commodity picture
        CommodityPictureDao pictureDao = new CommodityPictureDao();
        List<CommodityPicture> pictures = pictureDao.getInstances(id);
        model.addAttribute("pictures", parseCommodityPictureUrls(pictures));
        model.addAttribute("middlePictures", parseCommodityPictureMiddleUrls(pictures));
        model.addAttribute("smallPictures", parseCommodityPictureSmallUrls(pictures));
        List<CommodityPicture> detailPictures = pictureDao.getDetailInstances(id);
        model.addAttribute("detailPictures", parseCommodityPictureUrls(detailPictures));

        // related commodity
        List<Commodity> commodities = new RelatedCommodityDao().getInstances(commodity);
        model.addAttribute("relatedCommodities", createRelatedCommodityWrappers(commodities));

        // branch
        Branch branch = commodity.getBranch();
        model.addAttribute("branch", adjustBranch(branch));

        // price
        CommodityPriceDao priceDao = new CommodityPriceDao();
        model.addAttribute("lowPrice", priceDao.getMinPriceInThreeMonth(id));
        model.addAttribute("highPrice", priceDao.getMaxPrice(id));

        // appraise
        AppraiseGroupDao appraiseGroupDao = new AppraiseGroupDao();
        model.addAttribute("positiveAppraiseGroups", appraiseGroupDao.getPositiveInstances(id));
        model.addAttribute("negativeAppraiseGroups", appraiseGroupDao.getNegativeInstances(id));

        // share
        model.put("share", shareLinks);

        addJs("commodity-parameters");
        addJs("lib/linecharts/raphael-min");
        addJs("lib/linecharts/linecharts");

        setTitle("商品明细");

        return "detail";
    }

    private List<RelatedCommodityWrapper> createRelatedCommodityWrappers(
            List<Commodity> commodities) {
        List<RelatedCommodityWrapper> wrappers = new ArrayList<>();
        CommodityPictureDao pictureDao = new CommodityPictureDao();
        PictureUrlConverter urlConverter = new PictureUrlConverter(imageDownloader, getLocalAddress())
                .setThumbnailType(ThumbnailType.MIDDLE);
        for (Commodity commodity : commodities) {
            CommodityPicture picture = pictureDao.getFirstInstance(commodity.getId());
            RelatedCommodityWrapper wrapper = new RelatedCommodityWrapper();
            wrapper.id = commodity.getId();
            wrapper.picture = urlConverter.pathToUrl(picture.getUrl());
            wrapper.price = commodity.getPrice();
            wrappers.add(wrapper);
        }
        return wrappers;
    }

    public static class RelatedCommodityWrapper {
        private Integer id;
        private String picture;
        private Double price;

        public Integer getId() {
            return id;
        }

        public String getPicture() {
            return picture;
        }

        public Double getPrice() {
            return price;
        }
    }
}
