package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * CompanyInfoController
 * Created by qinyuan on 15-2-14.
 */
@Controller
public class CompanyInfoController extends BaseController{

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/company-info")
    public String index(ModelMap model) {
        String tabIndex = request.getParameter("tab");

        if (tabIndex.equals("1")) {
            setTitle("关于我们");
        } else if (tabIndex.equals("2")) {
            setTitle("联系我们");
        } else if (tabIndex.equals("3")) {
            setTitle("免费声明");
        } else if (tabIndex.equals("4")) {
            setTitle("友情链接");
        }

        return "company-info";
    }
}
