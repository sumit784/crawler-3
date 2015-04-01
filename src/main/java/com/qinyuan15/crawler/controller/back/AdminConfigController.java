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
    public Map<String, Object> update(@RequestParam(value = "globalBanner", required = true) String globalBanner,
                                      @RequestParam(value = "globalBannerFile", required = false) MultipartFile globalBannerFile,
                                      @RequestParam(value = "globalLogo", required = true) String globalLogo,
                                      @RequestParam(value = "globalLogoFile", required = false) MultipartFile globalLogoFile,
                                      @RequestParam(value = "indexHeadPoster", required = true) String indexHeadPoster,
                                      @RequestParam(value = "indexHeadPosterFile", required = false) MultipartFile indexHeadPosterFile,
                                      @RequestParam(value = "indexFootPoster", required = true) String indexFootPoster,
                                      @RequestParam(value = "indexFootPosterFile", required = false) MultipartFile indexFootPosterFile) {
        String globalBannerPath;
        try {
            globalBannerPath = getSavePath(globalBanner, globalBannerFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with globalBanner, globalBanner:{}, globalBannerFile:{}, error:{}",
                    globalBanner, globalBannerFile, e);
            return createFailResult("页头横幅处理失败!");
        }

        String globalLogoPath;
        try {
            globalLogoPath = getSavePath(globalLogo, globalLogoFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with globalLogo, globalLogo:{}, globalLogoFile:{}, error:{}",
                    globalLogo, globalLogoFile, e);
            return createFailResult("页头Logo文件处理失败!");
        }

        String indexHeadPosterPath;
        try {
            indexHeadPosterPath = getSavePath(indexHeadPoster, indexHeadPosterFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with indexHeadPoster, indexHeadPoster:{}, indexHeadPosterFile:{}, error:{}",
                    indexHeadPoster, indexHeadPosterFile, e);
            return createFailResult("首页头部海报处理失败!");
        }

        String indexFootPosterPath;
        try {
            indexFootPosterPath = getSavePath(indexFootPoster, indexFootPosterFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with indexFootPoster, indexFootPoster:{}, indexFootPosterFile:{}, error:{}",
                    indexFootPoster, indexFootPosterFile, e);
            return createFailResult("首页尾部海报处理失败!");
        }

        AppConfigDao dao = new AppConfigDao();
        AppConfig appConfig = dao.getInstance();

        appConfig.setGlobalBanner(globalBannerPath);
        logAction("将页头横幅修改为'%s'", pictureUrlConverter.pathToUrl(globalBannerPath));

        appConfig.setGlobalLogo(globalLogoPath);
        logAction("将页头Logo修改为'%s'", pictureUrlConverter.pathToUrl(globalLogoPath));

        appConfig.setIndexHeadPoster(indexHeadPosterPath);
        logAction("将主页头部海报修改为'%s'", pictureUrlConverter.pathToUrl(indexHeadPosterPath));

        appConfig.setIndexFootPoster(indexFootPosterPath);
        logAction("将主页尾部海报修改为'%s'", pictureUrlConverter.pathToUrl(indexFootPosterPath));

        dao.update(appConfig);
        return SUCCESS;
    }
}
