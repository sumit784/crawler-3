package com.qinyuan15.crawler.controller.front;

import com.google.common.collect.Lists;
import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.Shoppe;
import com.qinyuan15.crawler.dao.ShoppeDao;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Shoppe page controller
 * Created by qinyuan on 15-2-17.
 */
@Controller
public class ShoppeController extends BaseController{

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/shoppe")
    public String index(ModelMap model) {

        List<String> moreCss = Lists.newArrayList("list");
        model.addAttribute("moreCss", moreCss);
        List<String> moreJs = Lists.newArrayList("list");
        model.addAttribute("moreJs", moreJs);

        int id = NumberUtils.toInt(request.getParameter("id"));
        Shoppe description = new ShoppeDao().getInstance(id);
        model.addAttribute("shopDescription", description);
        setTitle(description.getName() + " 店铺");

        return "shoppe";
    }
}
