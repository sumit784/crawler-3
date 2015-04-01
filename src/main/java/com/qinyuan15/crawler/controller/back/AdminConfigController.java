package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.AppConfig;
import com.qinyuan15.crawler.dao.AppConfigDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Page to edit application config
 * Created by qinyuan on 15-4-1.
 */
@Controller
public class AdminConfigController extends ImageController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminConfigController.class);
    private final static String ADMIN_CONFIG = "admin-config";
    private final static String SAVE_PATH_PREFIX = "mall/config/images/";

    @RequestMapping("/admin-config")
    public String index() {
        addCssAndJs("admin-normal-edit-page");
        addHeadJs("image-adjust");

        setTitle("系统配置");
        return ADMIN_CONFIG;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-config-update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestParam(value = "globalLogo", required = true) String globalLogo,
                                      @RequestParam(value = "globalLogoFile", required = false) MultipartFile globalLogoFile,
                                      @RequestParam(value = "globalBanner", required = true) String globalBanner,
                                      @RequestParam(value = "globalBannerFile", required = false) MultipartFile globalBannerFile,
                                      @RequestParam(value = "globalBottomPoster", required = true) String globalBottomPoster,
                                      @RequestParam(value = "globalBottomPosterFile", required = false) MultipartFile globalBottomPosterFile) {
        String globalLogoPath;
        try {
            globalLogoPath = getSavePath(globalLogo, globalLogoFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with globalLogo, globalLogo:{}, globalLogoFile:{}, error:{}",
                    globalLogo, globalLogoFile, e);
            return createFailResult("首页Logo文件处理失败!");
        }

        String globalBannerPath;
        try {
            globalBannerPath = getSavePath(globalBanner, globalBannerFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with globalBanner, globalBanner:{}, globalBannerFile:{}, error:{}",
                    globalBanner, globalBannerFile, e);
            return createFailResult("首页头部横幅处理失败!");
        }

        String globalBottomPosterPath;
        try {
            globalBottomPosterPath = getSavePath(globalBottomPoster, globalBottomPosterFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with globalBottomPoster, globalBottomPoster:{}, globalBottomPosterFile:{}, error:{}",
                    globalBottomPoster, globalBottomPosterFile, e);
            return createFailResult("首页尾部海报处理失败!");
        }

        AppConfigDao dao = new AppConfigDao();
        AppConfig appConfig = dao.getInstance();

        appConfig.setGlobalLogo(globalLogoPath);
        logAction("将主页Logo修改为'%s'", pictureUrlConverter.pathToUrl(globalLogoPath));

        appConfig.setGlobalBanner(globalBannerPath);
        logAction("将主页头部横幅修改为'%s'", pictureUrlConverter.pathToUrl(globalBannerPath));

        appConfig.setGlobalBottomPoster(globalBottomPosterPath);
        logAction("将主页尾部海报修改为'%s'", pictureUrlConverter.pathToUrl(globalBottomPosterPath));

        dao.update(appConfig);
        return SUCCESS;
    }
}
