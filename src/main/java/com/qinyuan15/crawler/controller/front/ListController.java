package com.qinyuan15.crawler.controller.front;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.qinyuan15.crawler.controller.utils.JspControllerUtils.setTitle;

/**
 * Index page controller
 * Created by qinyuan on 15-2-15.
 */
@Controller
public class ListController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/list")
    public String index(ModelMap model) {
        String keyWord = request.getParameter("keyWord");
        setTitle(model, keyWord + " 相关商品");
        model.addAttribute("classifications", getClassifications());
        return "list";
    }

    private List<String> getClassifications() {
        return Lists.newArrayList(
                "女士钱包", "双肩包", "旅行箱", "书包", "单肩包", "百搭单鞋", "女士钱包", "双肩包"
        );
    }
}
