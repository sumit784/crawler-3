package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.CommodityDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
        addCss("commodity-search-form");
        model.addAttribute("keyWord", keyWord);

        long count = CommodityDao.factory().setKeyWord(keyWord).setCategoryId(categoryId).getCount();
        if (count > 0) {
            setTitle(keyWord + " 相关商品");
            return "search";
        } else {
            setTitle("找不到相关商品");
            return "search-no-result";
        }
    }
}
