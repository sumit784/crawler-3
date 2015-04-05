package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.UserLogDao;
import com.qinyuan15.crawler.security.SecurityUtils;
import com.qinyuan15.crawler.ui.PaginationAnchor;
import com.qinyuan15.crawler.ui.PaginationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Page to edit branch
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminLogController extends ImageController {
    private final static int PAGE_SIZE = 50;
    private final static int VISIBLE_BUTTON_COUNT = 10;

    @RequestMapping("/admin-log")
    public String index(ModelMap model,
                        @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
        Integer userId = SecurityUtils.getUserId();

        if (!isPositive(pageNumber)) {
            pageNumber = 1;
        }

        UserLogDao.Factory factory = UserLogDao.factory().setUserId(userId);
        long logCount = factory.getCount();
        int pageCount = PaginationUtils.getPageCount(logCount, PAGE_SIZE);
        if (pageNumber > pageCount) {
            pageNumber = pageCount;
        }
        String pageUrl = "admin-log";
        List<PaginationAnchor> anchors = PaginationAnchor.create(
                pageUrl, pageCount, VISIBLE_BUTTON_COUNT, pageNumber);
        model.addAttribute("paginationAnchors", anchors);
        model.addAttribute("userLogs", factory.getInstances((pageNumber - 1) * PAGE_SIZE, PAGE_SIZE));
        model.addAttribute("rowStartIndex", (pageNumber - 1) * PAGE_SIZE + 1);

        addCssAndJs("admin-normal-edit-page");
        setTitle("操作日志");
        return "admin-log";
    }
}
