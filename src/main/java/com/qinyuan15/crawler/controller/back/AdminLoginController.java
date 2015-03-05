package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller about login
 * Created by qinyuan on 15-3-5.
 */
@Controller
public class AdminLoginController extends BaseController {
    @RequestMapping("/login")
    public String index(ModelMap model) {
        addCss("index");
        addJs("index");
        setTitle("用户登录");
        return "login";
    }
}
