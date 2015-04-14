package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Admin page
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminArticleController extends ImageController {
    @RequestMapping("/admin-article-edit")
    public String index(ModelMap model,
                        @RequestParam(value = "keyWord", required = false) String keyWord) {
        addJs("lib/ckeditor/ckeditor");
        setTitle("文章编辑");
        return "admin-article-edit";
    }
}
