package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.ApplicationConfig;
import com.qinyuan15.crawler.core.commodity.CategoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * Commodity list page controller
 * Created by qinyuan on 15-2-15.
 */
@Controller
public class ListController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ListController.class);

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/list")
    public String index(ModelMap model) {
        ApplicationConfig config = ApplicationConfig.getInstance();
        String keyWord = request.getParameter("keyWord");
        if (!StringUtils.hasText(keyWord)) {
            try {
                keyWord = URLEncoder.encode(CategoryUtils.getCategories().get(0), config.getDefaultEncoding());
                return "redirect:list?keyWord=" + keyWord;
            } catch (Exception e) {
                LOGGER.error("fail to redirect /list: {}", e);
            }
        }

        setTitle(keyWord + " 相关商品");
        model.addAttribute("classifications", CategoryUtils.getSubCategories(keyWord));
        return "list";
    }
}
