package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.HotSearchWordDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to query hot search word
 * Created by qinyuan on 15-2-26.
 */
@Controller
public class HotSearchWordController extends BaseController {

    private final static int DEFAULT_QUERY_SIZE = 8;

    @ResponseBody
    @RequestMapping("/json/hotSearchWord.json")
    public String index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                        @RequestParam(value = "size", required = false) Integer size) {
        if (!isPositive(size)) {
            size = DEFAULT_QUERY_SIZE;
        }

        HotSearchWordDao dao = new HotSearchWordDao();
        return toJson(dao.getInstances(categoryId, size));
    }
}
