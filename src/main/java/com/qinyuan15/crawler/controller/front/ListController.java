package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.Category;
import com.qinyuan15.crawler.dao.CategoryDao;
import com.qinyuan15.crawler.dao.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Commodity list page controller
 * Created by qinyuan on 15-2-15.
 */
@Controller
public class ListController extends BaseController {

    @RequestMapping("/list")
    public String index(ModelMap model, @RequestParam(value = "id", required = false) Integer id) {
        CategoryDao dao = new CategoryDao();

        Category category = HibernateUtils.get(Category.class, id);
        if (category == null) {
            category = dao.getFirstInstance();
        }

        addCssAndJs("list-snapshots");
        addCssAndJs("commodity-search-form");
        addIEJs("list-ie-patch");
        addIEJs("commodity-search-form-ie-patch");

        setTitle(category.getName() + " 相关商品");
        model.addAttribute("categoryId", category.getId());
        model.addAttribute("categoryName", category.getName());
        model.addAttribute("subCategories", dao.getSubInstances(category.getId()));
        return "list";
    }
}
