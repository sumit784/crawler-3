package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.AppConfigFootLinkDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to get AppConfigFootLink
 * Created by qinyuan on 15-4-13.
 */
@Controller
public class AppConfigFootLinkController extends BaseController {
    @ResponseBody
    @RequestMapping("/json/footLink.json")
    public String index() {
        return toJson(new AppConfigFootLinkDao().getInstances());
    }
}
