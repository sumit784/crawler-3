package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.Commodity;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Page to edit commodity
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminEditCommodityController extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/admin-edit-commodity")
    public String index(ModelMap model) {
        String idString = request.getParameter("id");
        if (StringUtils.hasText(idString)) {
            setTitle("编辑商品");
            model.addAttribute("commodity", getCommodity(NumberUtils.toInt(idString)));
        } else {
            setTitle("添加商品");
            model.addAttribute("commodity", newCommodity());
        }

        return "admin-edit-commodity";
    }

    private Commodity getCommodity(int id) {
        return newCommodity();
    }

    private Commodity newCommodity() {
        Commodity commodity = new Commodity();
        commodity.setSerialNumber(RandomStringUtils.randomNumeric(12));
        return commodity;
    }
}
