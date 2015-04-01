package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.AppConfig;
import com.qinyuan15.crawler.dao.AppConfigDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Page to edit category
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminDetailController extends ImageController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminDetailController.class);
    private final static String ADMIN_DETAIL = "admin-detail";
    private final static String SAVE_PATH_PREFIX = "mall/detail/images/";

    @RequestMapping("/admin-detail")
    public String index() {
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

    @ResponseBody
    @RequestMapping(value = "/admin-detail-image-add-update", method = RequestMethod.POST)
    public Map<String, Object> addUpdateImage(@RequestParam(value = "id", required = true) Integer id,
                                              @RequestParam(value = "url", required = true) String url,
                                              @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile) {
        String savePath;
        try {
            savePath = getSavePath(url, uploadFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with logoUrl, logo:{}, logoFile:{}, error:{}",
                    url, uploadFile, e);
            return createFailResult("矩形Logo文件处理失败!");
        }

        AppConfigDao dao = new AppConfigDao();
        if (id != null && id >= 0) {
            dao.editImage(id, savePath);
            logAction("将商品详细页配置第'%d'个配置图片修改为'%s'", id, savePath);
        } else {
            dao.addImage(savePath);
            logAction("为商品详细页配置添加图片'%s'", savePath);
        }

        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-detail-image-delete", method = RequestMethod.POST)
    public Map<String, Object> deleteImage(@RequestParam(value = "id", required = true) Integer id) {
        AppConfigDao dao = new AppConfigDao();
        if (id != null && id >= 0) {
            dao.deleteImage(id);
            logAction("删除商品详细页配置第'%d'个配置图片", id);
        }
        return SUCCESS;
    }

}
