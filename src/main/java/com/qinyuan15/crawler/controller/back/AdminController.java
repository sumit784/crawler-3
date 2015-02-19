package com.qinyuan15.crawler.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.qinyuan15.crawler.controller.utils.JspControllerUtils.setTitle;

/**
 * Detail page
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/admin")
    public String index(ModelMap model) {
        model.addAttribute("commodities", getCommodities(1));
        setTitle(model, "商品管理");

        return "admin";
    }

    private List<SimpleCommodity> getCommodities(int adminId) {
        List<SimpleCommodity> commodities = new ArrayList<SimpleCommodity>();
        for (int i = 0; i < 6; i++) {
            SimpleCommodity commodity = new SimpleCommodity();
            commodity.name = "麂皮磨砂真皮款小包 单肩包手提包复古包潮款包包";
            commodity.picture = "resources/css/images/manage-commodity/c1.png";
            commodities.add(commodity);
        }
        return commodities;
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
