package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.CategoryDao;
import com.qinyuan15.crawler.dao.HotSearchWordDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Controller to edit hot search word
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminHotSearchWordController extends BaseController {
    @ResponseBody
    @RequestMapping(value = "/admin-hot-search-word-add-update", method = RequestMethod.POST)
    public Map<String, Object> addUpdate(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "content", required = true) String content,
                                         @RequestParam(value = "hot", required = false) Boolean hot,
                                         @RequestParam(value = "categoryId", required = true) Integer categoryId) {

        if (hot == null) {
            hot = false;
        }

        HotSearchWordDao dao = new HotSearchWordDao();
        if (isPositive(id)) {
            dao.update(id, content, categoryId, hot);
            logAction("更新搜索关键词'%s'", content);
        } else {
            dao.add(content, categoryId, hot);
            logAction("添加搜索关键词'%s'", content);
        }

        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-hot-search-word-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        CategoryDao dao = new CategoryDao();
        if (dao.isUsed(id)) {
            return createFailResult("该商品分类已经被某些商品或其他分类使用，不能删除");
        } else {
            logAction("删除商品分类'%s'", dao.getInstance(id).getName());
            dao.delete(id);
            return SUCCESS;
        }
    }
}
