package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.config.LinkAdapter;
import com.qinyuan15.crawler.dao.AppConfigFootLinkDao;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Page to edit foot link
 * Created by qinyuan on 15-3-19.
 */
@Controller
public class AdminFootLinkController extends ImageController {
    //private final static Logger LOGGER = LoggerFactory.getLogger(AdminFootLinkController.class);
    AppConfigFootLinkDao dao = new AppConfigFootLinkDao();

    @ResponseBody
    @RequestMapping(value = "/app-config-foot-link-add-update", method = RequestMethod.POST)
    public Map<String, Object> addUpdate(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "text", required = true) String text,
                                         @RequestParam(value = "link", required = true) String link) {
        if (!StringUtils.hasText(text)) {
            return createFailResult("显示文字不能为空");
        }
        if (!StringUtils.hasText(link)) {
            return createFailResult("目标链接不能为空");
        }

        // adjust link
        link = new LinkAdapter().adjust(link);

        if (isPositive(id)) {
            dao.edit(id, text, link);
            logAction("更新页尾链接'%s'", text);
        } else {
            dao.add(text, link);
            logAction("添加页尾链接'%s'", text);
        }

        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/app-config-foot-link-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            logAction("删除页尾链接'%s'", dao.getTextById(id));
            dao.delete(id);
        }
        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/app-config-foot-link-rank-up", method = RequestMethod.POST)
    public Map<String, Object> rankUp(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            logAction("上移页尾链接'%s'的排序", dao.getTextById(id));
            dao.rankUp(id);
        }
        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/app-config-foot-link-rank-down", method = RequestMethod.POST)
    public Map<String, Object> rankDown(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            logAction("下移页尾链接'%s'的排序", dao.getTextById(id));
            dao.rankDown(id);
        }
        return SUCCESS;
    }
}
