package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.config.ArticleUtils;
import com.qinyuan15.crawler.dao.ArticleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Admin page
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminArticleController extends ImageController {
    private ArticleDao dao = new ArticleDao();

    @RequestMapping("/admin-article-edit")
    public String index(ModelMap model,
                        @RequestParam(value = "id", required = false) Integer id) {
        if (isPositive(id)) {
            model.addAttribute(dao.getInstance(id));
        }

        addJs("lib/ckeditor/ckeditor");
        setTitle("文章编辑");
        return "admin-article-edit";
    }

    @RequestMapping(value = "/admin-article-add-update", method = RequestMethod.POST)
    public String addUpdate(@RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "content", required = true) String content,
                            @RequestParam(value = "backgroundColor", required = false) String backgroundColor) {

        if (isPositive(id)) {
            dao.update(id, content, backgroundColor);
            logAction("更新文章'%s'", ArticleUtils.getTitle(content));
        } else {
            dao.add(content, backgroundColor);
            logAction("添加文章'%s'", ArticleUtils.getTitle(content));
        }

        return "admin-article-list";
    }


    @ResponseBody
    @RequestMapping(value = "/admin-article-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        logAction("删除文章'%s'", dao.getTitleById(id));
        dao.delete(id);
        return SUCCESS;
    }
}
