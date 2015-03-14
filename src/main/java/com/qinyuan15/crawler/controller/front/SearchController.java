package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Index page controller
 * Created by qinyuan on 15-2-16.
 */
@Controller
public class SearchController extends BaseController {

    @RequestMapping("/search")
    public String index(ModelMap model,
                        @RequestParam(value = "keyWord", required = true) String keyWord,
                        @RequestParam(value = "categoryId", required = false) Integer categoryId) {
        addCssAndJs("list-snapshots");
        if (hasResult(keyWord)) {
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
