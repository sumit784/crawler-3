package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.UserLogDao;
import com.qinyuan15.crawler.security.SecurityUtils;
import com.qinyuan15.crawler.ui.PaginationAttributeAdder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Page to edit branch
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminLogController extends ImageController {
    @RequestMapping("/admin-log")
    public String index() {
        UserLogDao.Factory factory = UserLogDao.factory()
                .setUserId(SecurityUtils.getUserId());
        new PaginationAttributeAdder(factory, request).setRowItemsName("userLogs").add();

        addCssAndJs("admin-normal-edit-page");
        setTitle("操作日志");
        return "admin-log";
    }
}
