package com.qinyuan15.crawler.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.qinyuan15.crawler.controller.utils.JspControllerUtils.setTitle;

/**
 * Page to edit commodity
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminEditCommodityController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/admin-edit-commodity")
    public String index(ModelMap model) {
        String idString = request.getParameter("id");
        if (StringUtils.hasText(idString)) {
            setTitle(model, "编辑商品");
        } else {
            setTitle(model, "添加商品");
        }

        return "admin-edit-commodity";
    }
}
