package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index page controller
 * Created by qinyuan on 15-2-16.
 */
@Controller
public class SearchController extends BaseController {

    @RequestMapping("/search")
    public String index(ModelMap model) {
        addCss("list");
        addJs("list");
        String keyWord = request.getParameter("keyWord");
        if (hasResult(keyWord)) {
            addCss("shoppe");
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
