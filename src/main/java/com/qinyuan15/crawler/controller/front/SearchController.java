package com.qinyuan15.crawler.controller.front;

import com.google.common.collect.Lists;
import com.qinyuan15.crawler.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Index page controller
 * Created by qinyuan on 15-2-16.
 */
@Controller
public class SearchController extends BaseController{

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/search")
    public String index(ModelMap model) {
        String keyWord = request.getParameter("keyWord");

        List<String> moreCss = Lists.newArrayList("list");
        model.addAttribute("moreCss", moreCss);
        List<String> moreJs = Lists.newArrayList("list");
        model.addAttribute("moreJs", moreJs);

        if (hasResult(keyWord)) {
            moreCss.add("shoppe");
            setTitle(keyWord + " 相关商品");
            model.addAttribute("keyWord", keyWord);
            return "search";
        } else {
            setTitle("找不到相关商品");
            return "search-no-result";
        }
    }

    private boolean hasResult(String keyWord) {
        return StringUtils.hasText(keyWord);
    }
}
