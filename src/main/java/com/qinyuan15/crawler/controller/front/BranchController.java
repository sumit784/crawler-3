package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.BranchDao;
import com.qinyuan15.crawler.dao.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
        BranchDao dao = new BranchDao();
        if (isPositive(parentId)) {
            return toJson(dao.getSubInstances(parentId));
        } else {
            return toJson(dao.getRootInstances());
        }
    }


    @ResponseBody
    @RequestMapping("/json/parentBranch.json")
    public String queryParent(@RequestParam(value = "branchId", required = true) Integer branchId) {
        Branch branch = HibernateUtil.get(Branch.class, branchId);
        if (branch == null) {
            return createParentResult(branchId, null, null);
        }

        Integer parentBranchId = branch.getParentId();
        Branch parentBranch = HibernateUtil.get(Branch.class, parentBranchId);
        if (parentBranch == null) {
            return createParentResult(branchId, null, null);
        }

        Integer grandBranchId = parentBranch.getParentId();
        Branch grandBranch = HibernateUtil.get(Branch.class, parentBranch.getParentId());
        if (grandBranch == null) {
            return createParentResult(parentBranchId, branchId, null);
        }

        return createParentResult(grandBranchId, parentBranchId, branchId);
    }

    private String createParentResult(Integer branchId, Integer subBranch1Id, Integer subBranch2Id) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("branchId", branchId);
        result.put("subBranch1Id", subBranch1Id);
        result.put("subBranch2Id", subBranch2Id);
        return toJson(result);
    }
}
