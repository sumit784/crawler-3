package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.AppConfig;
import com.qinyuan15.crawler.dao.AppConfigDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Page to edit category
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminDetailController extends BaseController {
    private final static String ADMIN_DETAIL = "admin-detail";

    @RequestMapping("/admin-detail")
    public String index(ModelMap model) {
        model.addAttribute("appConfig", new AppConfigDao().getInstance());
        addCssAndJs("admin-normal-edit-page");
        addJs("lib/ckeditor/ckeditor");

        setTitle("编辑详细页面");
        return ADMIN_DETAIL;
    }

    @RequestMapping(value = "/admin-detail-update", method = RequestMethod.POST)
    public String update(@RequestParam(value = "detailText", required = false) String detailText) {
        AppConfigDao dao = new AppConfigDao();
        AppConfig appConfig = dao.getInstance();
        if (detailText != null) {
            appConfig.setDetailText(detailText);
        }
        dao.update(appConfig);
        return redirect(ADMIN_DETAIL);
    }

    @RequestMapping(value = "/admin-detail-add-image", method = RequestMethod.POST)
    public String addImage(@RequestParam(value = "logo", required = true) String logo,
                           @RequestParam(value = "logoFile", required = false) MultipartFile logoFile) {
        return redirect(ADMIN_DETAIL);
    }
}
