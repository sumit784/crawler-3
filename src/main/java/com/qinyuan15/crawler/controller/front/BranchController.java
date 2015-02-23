package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * BranchController
 * Created by qinyuan on 15-2-14.
 */
@Controller
public class BranchController extends BaseController {

    @RequestMapping("/branch")
    public String index(ModelMap model) {
        setTitle("品牌大全");
        return "branch";
    }

    @ResponseBody
    @RequestMapping("/json/branch.json")
    public String query(@RequestParam(value = "parentId", required = false) Integer parentId) {
        String hql = parentId != null && parentId > 0 ?
                "Branch WHERE parentId=" + parentId :
                "Branch WHERE parentId IS NULL OR parentId<=0";
        return toJson(HibernateUtil.getList(hql));
    }
}
