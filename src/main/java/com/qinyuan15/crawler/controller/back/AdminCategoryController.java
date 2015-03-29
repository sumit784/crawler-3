package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.category.RichCategory;
import com.qinyuan15.crawler.dao.BranchDao;
import com.qinyuan15.crawler.dao.Category;
import com.qinyuan15.crawler.dao.CategoryDao;
import com.qinyuan15.crawler.dao.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Page to edit category
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminCategoryController extends ImageController {
    @RequestMapping("/admin-category")
    public String index(ModelMap model) {
        CategoryDao categoryDao = new CategoryDao();
        model.addAttribute("rootCategories", categoryDao.getRootInstances());

        List<RichCategory> richCategories = RichCategory.getInstances();
        for (RichCategory richCategory : richCategories) {
            adjustBranches(richCategory.getBranches());
            adjustCategoryPosters(richCategory.getPosters());
        }
        model.addAttribute("richCategories", richCategories);
        model.addAttribute("branches", adjustBranches(new BranchDao().getInstances()));
        addCssAndJs("admin-normal-edit-page");

        setTitle("编辑商品分类");
        return "admin-category";
    }

    @ResponseBody
    @RequestMapping(value = "/admin-category-add-update", method = RequestMethod.POST)
    public Map<String, Object> addUpdate(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "name", required = true) String name,
                                         @RequestParam(value = "parentId", required = true) Integer parentId) {
        if (!isPositive(parentId)) {
            parentId = null;
        }

        // build Category object
        Category category = isPositive(id) ? new CategoryDao().getInstance(id) : new Category();
        category.setName(name);
        category.setParentId(parentId);

        // save or update
        if (isPositive(id)) {
            HibernateUtils.update(category);
            logAction("更新商品分类'%s'", category.getName());
        } else {
            HibernateUtils.save(category);
            logAction("添加商品分类'%s'", category.getName());
        }
        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-category-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        CategoryDao dao = new CategoryDao();
        if (dao.isUsed(id)) {
            return createFailResult("该商品分类已经被某些商品或其他分类使用，不能删除");
        } else {
            logAction("删除商品分类'%s'", dao.getInstance(id).getName());
            dao.delete(id);
            return SUCCESS;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin-category-rank-up", method = RequestMethod.POST)
    public Map<String, Object> rankUp(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            CategoryDao dao = new CategoryDao();
            logAction("上移'%s'分类的排序", dao.getInstance(id).getName());
            dao.rankUp(id);
        }
        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-category-rank-down", method = RequestMethod.POST)
    public Map<String, Object> rankDown(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            CategoryDao dao = new CategoryDao();
            logAction("下移'%s'分类的排序", dao.getInstance(id).getName());
            dao.rankDown(id);
        }
        return SUCCESS;
    }
}
