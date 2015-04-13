package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.SeoKeywordDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Controller to edit hot search word
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminSeoKeywordController extends BaseController {
    private SeoKeywordDao dao = new SeoKeywordDao();

    @RequestMapping("/admin-seo")
    public String index(ModelMap model) {
        addCssAndJs("admin-normal-edit-page");
        model.addAttribute("seoKeywords", new SeoKeywordDao().getInstances());

        setTitle("SEO设置");
        return "admin-seo";
    }

    @ResponseBody
    @RequestMapping(value = "/admin-seo-keyword-add-update", method = RequestMethod.POST)
    public Map<String, Object> addUpdate(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "url", required = true) String url,
                                         @RequestParam(value = "word", required = true) String word) {
        if (isPositive(id)) {
            dao.update(id, url, word);
            logAction("将'%s'链接的SEO关键词更新为'%s'", url, word);
        } else {
            if (dao.hasInstance(url)) {
                return createFailResult("'" + url + "'这一链接的SEO关键词已经被添加，不能重复添加！");
            }
            dao.add(url, word);
            logAction("为'%s'链接添加了SEO关键词'%s'", url, word);
        }

        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-seo-keyword-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            logAction("删除'%s'链接的SEO关键词", dao.getInstance(id).getUrl());
            dao.delete(id);
        }
        return SUCCESS;
    }
}
