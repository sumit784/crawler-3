package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.config.LinkAdapter;
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
    public Map<String, Object> update(
            @RequestParam(value = "globalBanner", required = true) String globalBanner,
            @RequestParam(value = "globalBannerFile", required = false) MultipartFile globalBannerFile,
            @RequestParam(value = "globalLogo", required = true) String globalLogo,
            @RequestParam(value = "globalLogoFile", required = false) MultipartFile globalLogoFile,
            @RequestParam(value = "indexHeadPoster", required = true) String indexHeadPoster,
            @RequestParam(value = "indexHeadPosterFile", required = false) MultipartFile indexHeadPosterFile,
            @RequestParam(value = "indexFootPoster", required = true) String indexFootPoster,
            @RequestParam(value = "indexFootPosterFile", required = false) MultipartFile indexFootPosterFile,
            @RequestParam(value = "indexFootPosterLink", required = true) String indexFootPosterLink,
            @RequestParam(value = "branchRankImage", required = true) String branchRankImage,
            @RequestParam(value = "branchRankImageFile", required = false) MultipartFile branchRankImageFile,
            @RequestParam(value = "noFoundImage", required = true) String noFoundImage,
            @RequestParam(value = "noFoundImageFile", required = false) MultipartFile noFoundImageFile,
            @RequestParam(value = "noFoundText", required = true) String noFoundText,
            @RequestParam(value = "adminPaginationCommoditySize", required = true) Integer adminPaginationCommoditySize,
            @RequestParam(value = "adminPaginationButtonSize", required = true) Integer adminPaginationButtonSize,
            @RequestParam(value = "maxCommodityPictureSize", required = true) Integer maxCommodityPictureSize,
            @RequestParam(value = "maxCommodityDetailPictureSize", required = true) Integer maxCommodityDetailPictureSize) {
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

        String branchRankImagePath;
        try {
            branchRankImagePath = getSavePath(branchRankImage, branchRankImageFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with branchRankImage, branchRankImage:{}, branchRankImageFile:{}, error:{}",
                    branchRankImage, branchRankImageFile, e);
            return createFailResult("品牌排行图片处理失败!");
        }

        String noFoundImagePath;
        try {
            noFoundImagePath = getSavePath(noFoundImage, noFoundImageFile, SAVE_PATH_PREFIX);
        } catch (Exception e) {
            LOGGER.error("fail to deal with noFoundImage, noFoundImage:{}, noFoundImageFile:{}, error:{}",
                    noFoundImage, noFoundImageFile, e);
            return createFailResult("无对应商品时显示的图片处理失败!");
        }

        if (!isPositive(adminPaginationButtonSize)) {
            adminPaginationButtonSize = 0;
        }

        if (!isPositive(adminPaginationCommoditySize)) {
            adminPaginationCommoditySize = 0;
        }

        AppConfigDao dao = new AppConfigDao();
        AppConfig appConfig = dao.getInstance();

        if (isDifferent(appConfig.getGlobalBanner(), globalBannerPath)) {
            appConfig.setGlobalBanner(globalBannerPath);
            logAction("将页头横幅修改为'%s'", pictureUrlConverter.pathToUrl(globalBannerPath));
        }

        if (isDifferent(appConfig.getGlobalLogo(), globalLogoPath)) {
            appConfig.setGlobalLogo(globalLogoPath);
            logAction("将页头Logo修改为'%s'", pictureUrlConverter.pathToUrl(globalLogoPath));
        }

        if (isDifferent(appConfig.getIndexHeadPoster(), indexHeadPosterPath)) {
            appConfig.setIndexHeadPoster(indexHeadPosterPath);
            logAction("将主页头部海报修改为'%s'", pictureUrlConverter.pathToUrl(indexHeadPosterPath));
        }

        if (isDifferent(appConfig.getIndexFootPoster(), indexFootPosterPath)) {
            appConfig.setIndexFootPoster(indexFootPosterPath);
            logAction("将主页尾部海报修改为'%s'", pictureUrlConverter.pathToUrl(indexFootPosterPath));
        }

        indexFootPosterLink = new LinkAdapter().adjust(indexFootPosterLink);
        if (isDifferent(appConfig.getIndexFootPosterLink(), indexFootPosterLink)) {
            appConfig.setIndexFootPosterLink(indexFootPosterLink);
            logAction("将主页尾部海报目标链接修改为'%s'", indexFootPosterLink);
        }

        if (isDifferent(appConfig.getBranchRankImage(), branchRankImagePath)) {
            appConfig.setBranchRankImage(branchRankImagePath);
            logAction("将品牌排行图片修改为'%s'", pictureUrlConverter.pathToUrl(branchRankImagePath));
        }

        if (isDifferent(appConfig.getNoFoundImage(), noFoundImagePath)) {
            appConfig.setNoFoundImage(noFoundImagePath);
            logAction("将无对应商品时显示的图片修改为'%s'", pictureUrlConverter.pathToUrl(noFoundImagePath));
        }

        if (isDifferent(appConfig.getNoFoundText(), noFoundText)) {
            appConfig.setNoFoundText(noFoundText);
            logAction("将无对应商品时显示的文字修改为'%s'", noFoundText);
        }

        if (isDifferent(appConfig.getAdminPaginationCommoditySize(), adminPaginationCommoditySize)) {
            appConfig.setAdminPaginationCommoditySize(adminPaginationCommoditySize);
            logAction("商品列表中每个分页的商品数量修改为'%s'", adminPaginationCommoditySize);
        }

        if (isDifferent(appConfig.getAdminPaginationButtonSize(), adminPaginationButtonSize)) {
            appConfig.setAdminPaginationButtonSize(adminPaginationButtonSize);
            logAction("将商品列表中每个分页的底部链接数量修改为'%s'", adminPaginationButtonSize);
        }

        if (isDifferent(appConfig.getMaxCommodityPictureSize(), maxCommodityPictureSize)) {
            appConfig.setMaxCommodityPictureSize(maxCommodityPictureSize);
            logAction("将商品编辑页的商品图片数量限制修改为'%s'", maxCommodityPictureSize);
        }

        if (isDifferent(appConfig.getMaxCommodityDetailPictureSize(), maxCommodityDetailPictureSize)) {
            appConfig.setMaxCommodityDetailPictureSize(maxCommodityDetailPictureSize);
            logAction("将商品编辑页的商品详情图片数量限制修改为'%s'", maxCommodityDetailPictureSize);
        }

        dao.update(appConfig);
        return SUCCESS;
    }
}
