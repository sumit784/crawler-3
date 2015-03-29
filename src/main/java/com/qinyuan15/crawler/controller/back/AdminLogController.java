package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.UserLog;
import com.qinyuan15.crawler.dao.UserLogDao;
import com.qinyuan15.crawler.security.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Page to edit branch
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminLogController extends ImageController {
    @RequestMapping("/admin-log")
    public String index(ModelMap model) {
        Integer userId = SecurityUtils.getUserId();
        List<UserLog> userLogs = new UserLogDao().getInstancesByUserId(userId);
        model.addAttribute("userLogs", userLogs);
        addCssAndJs("admin-normal-edit-page");
        setTitle("操作日志");
        return "admin-log";
    }
}
