package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.BranchShoppe;
import com.qinyuan15.crawler.dao.BranchShoppeDao;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BranchShoppe page controller
 * Created by qinyuan on 15-2-17.
 */
@Controller
public class ShoppeController extends BaseController {

    @RequestMapping("/shoppe")
    public String index(ModelMap model) {
        addCss("list");
        addJs("list");

        int id = NumberUtils.toInt(request.getParameter("id"));
        BranchShoppe description = new BranchShoppeDao().getInstance(id);
        model.addAttribute("shopDescription", description);
        setTitle(description.getName() + " 店铺");

        return "shoppe";
    }
}
