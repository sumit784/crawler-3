package com.qinyuan15.crawler.controller.back;

import com.google.common.collect.Lists;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityPicture;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.qinyuan15.crawler.controller.utils.JspControllerUtils.BLANK;
import static com.qinyuan15.crawler.controller.utils.JspControllerUtils.setTitle;

/**
 * Detail page
 * Created by qinyuan on 15-2-17.
 */
@Controller
public class DetailController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/detail")
    public String index(ModelMap model) {
        String idString = request.getParameter("id");
        if (!NumberUtils.isDigits(idString)) {
            return BLANK;
        }

        int id = NumberUtils.toInt(idString);
        Commodity commodity = getCommodity(id);
        model.addAttribute("commodity", commodity);
        model.addAttribute("pictures", getCommodityPictures(commodity.getId()));
        model.addAttribute("branch", getBranch(commodity.getId()));

        List<String> moreJs = Lists.newArrayList("list");
        moreJs.add("lib/jsutils/jsutils");
        moreJs.add("lib/linecharts/raphael-min");
        moreJs.add("lib/linecharts/linecharts");
        model.addAttribute("moreJs", moreJs);

        setTitle(model, "商品明细");

        return "detail";
    }

    private Commodity getCommodity(int id) {
        Commodity commodity = new Commodity();
        commodity.setId(id);
        return commodity;
    }

    private List<CommodityPicture> getCommodityPictures(int commodityId) {
        List<CommodityPicture> pictures = new ArrayList<CommodityPicture>();

        CommodityPicture commodity = new CommodityPicture();
        commodity.setId(1);
        commodity.setUrl("resources/css/images/detail/detail-large2.png");
        commodity.setCommodityId(commodityId);

        pictures.add(commodity);

        return pictures;
    }

    private Branch getBranch(int id) {
        Branch branch = new Branch();
        branch.setId(id);
        branch.setLogoPath("resources/css/images/branchs/branch4.png");
        branch.setName("Adidas");
        return branch;
    }
}
