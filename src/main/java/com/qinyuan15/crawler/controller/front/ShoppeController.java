package com.qinyuan15.crawler.controller.front;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.BranchDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Branch shoppe page controller
 * Created by qinyuan on 15-2-17.
 */
@Controller
public class ShoppeController extends ImageController {

    @RequestMapping("/shoppe")
    public String index(ModelMap model, @RequestParam(value = "id", required = false) Integer id) {
        addCssAndJs("list-snapshots");

        Branch branch = adjustBranch(new BranchDao().getInstance(id));
        model.addAttribute("branch", branch);
        if (StringUtils.hasText(branch.getPoster())) {
            model.addAttribute("headerAdditions",
                    "<div class='poster'><img src='" + branch.getPoster() + "'/></div>");
        }
        return "shoppe";
    }
}
