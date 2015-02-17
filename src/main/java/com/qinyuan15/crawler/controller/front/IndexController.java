package com.qinyuan15.crawler.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static com.qinyuan15.crawler.controller.utils.JspControllerUtils.setTitle;

/**
 * Index page controller
 * Created by qinyuan on 15-2-14.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(ModelMap model) {
        setTitle(model, "果壳科技-首页");
        model.addAttribute("images", getIndexImages());
        return "index";
    }

    private List<IndexImage> getIndexImages() {
        List<IndexImage> images = new ArrayList<IndexImage>();
        for (int i = 0; i < 18; i++) {
            IndexImage image = new IndexImage();
            image.src = "resources/css/images/index/link1.png";
            image.text = "Feature";
            images.add(image);
        }
        return images;
    }

    public static class IndexImage {
        private String src;
        private String text;

        public String getSrc() {
            return src;
        }

        public String getText() {
            return text;
        }
    }
}
