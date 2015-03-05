package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.Category;
import com.qinyuan15.crawler.dao.CategoryDao;
import com.qinyuan15.crawler.dao.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Page to edit category
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminCategoryController extends BaseController {
    @RequestMapping("/admin-category")
    public String index(ModelMap model) {
        CategoryDao categoryDao = new CategoryDao();
        model.addAttribute("categories", categoryDao.getInstances());
        model.addAttribute("rootCategories", categoryDao.getRootInstances());

        setTitle("编辑商品分类");
        return "admin-category";
    }

    @ResponseBody
    @RequestMapping(value = "/admin-category-add-update", method = RequestMethod.POST)
    public Map<String, Object> addUpdate(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "name", required = true) String name,
                                         @RequestParam(value = "parentId", required = true) Integer parentId) {
        Session session = HibernateUtil.getSession();
        Category category = isPositive(id) ? (Category) session.get(Category.class, id) : new Category();
        category.setName(name);
        category.setParentId(parentId);
        session.saveOrUpdate(category);
        HibernateUtil.commit(session);
        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-category-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        HibernateUtil.delete(Branch.class, id);
        return SUCCESS;
    }
}
