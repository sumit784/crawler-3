package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.commodity.CommodityPictureDownloader;
import com.qinyuan15.crawler.dao.AppraiseGroupDao;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.dao.HibernateUtils;
import com.qinyuan15.crawler.security.SecurityUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

/**
 * Page to edit commodity
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminEditCommodityController extends ImageController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminEditCommodityController.class);

    private final static String INDEX = "admin";
    private final static String EDIT_PAGE = "admin-edit-commodity";
    private CommodityDao commodityDao = new CommodityDao();

    @RequestMapping("/admin-edit-commodity")
    public String index(ModelMap model, @RequestParam(value = "id", required = false) Integer id) {
        if (isPositive(id)) {
            setTitle("编辑商品");
            model.addAttribute("commodity", getCommodity(id));
            AppraiseGroupDao appraiseGroupDao = new AppraiseGroupDao();
            model.addAttribute("positiveAppraiseGroups", appraiseGroupDao.getPositiveInstances(id));
            model.addAttribute("negativeAppraiseGroups", appraiseGroupDao.getNegativeInstances(id));
        } else {
            setTitle("添加商品");
            model.addAttribute("commodity", newCommodity());
        }

        addJs("commodity-parameters");
        return EDIT_PAGE;
    }

    @RequestMapping("/admin-commodity-delete")
    public String delete(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            logAction("彻底删除商品'%s'", commodityDao.getNameById(id));
            commodityDao.delete(id);
        }
        return redirect(INDEX);
    }

    @RequestMapping("/admin-commodity-activate")
    public String activate(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            logAction("激活商品'%s'", commodityDao.getNameById(id));
            commodityDao.activate(id);
        }
        return redirect(INDEX);
    }

    @RequestMapping("/admin-commodity-deactivate")
    public String deactivate(@RequestParam(value = "id", required = true) Integer id) {
        if (isPositive(id)) {
            logAction("删除商品'%s'", commodityDao.getNameById(id));
            commodityDao.deactivate(id);
        }
        return redirect(INDEX);
    }

    @RequestMapping(value = "/admin-commodity-add-update", method = RequestMethod.POST)
    public String addUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "branchId", required = true) Integer branchId,
            @RequestParam(value = "subBranch1Id", required = true) Integer subBranch1Id,
            @RequestParam(value = "subBranch2Id", required = true) Integer subBranch2Id,
            @RequestParam(value = "categoryId", required = true) Integer categoryId,
            @RequestParam(value = "subCategoryId", required = true) Integer subCategoryId,
            @RequestParam(value = "commodityName", required = true) String commodityName,
            @RequestParam(value = "serialNumber", required = true) String serialNumber,
            @RequestParam(value = "showId", required = true) String showId,
            @RequestParam(value = "buyUrl", required = true) String buyUrl,
            @RequestParam(value = "url", required = true) String url,
            @RequestParam(value = "parameters", required = true) String parameters,
            @RequestParam(value = "positiveAppraiseGroups", required = false) String[] positiveAppraiseGroups,
            @RequestParam(value = "negativeAppraiseGroups", required = false) String[] negativeAppraiseGroups,
            @RequestParam(value = "imageUrls", required = false) String[] imageUrls,
            @RequestParam(value = "detailImageUrls", required = false) String[] detailImageUrls) {
        //debugParameters();
        Commodity commodity = isPositive(id) ? commodityDao.getInstance(id) : new Commodity();

        if (!isPositive(branchId)) {
            return toEditPage("品牌未设置", id);
        }
        commodity.setBranchId(getBranchId(branchId, subBranch1Id, subBranch2Id));

        if (!isPositive(categoryId)) {
            return toEditPage("商品分类未设置", id);
        }
        commodity.setCategoryId(getCategoryId(categoryId, subCategoryId));

        if (!StringUtils.hasText(commodityName)) {
            return toEditPage("名称未设置", id);
        }
        commodity.setName(commodityName);

        if (!StringUtils.hasText(serialNumber)) {
            return toEditPage("商品编号未设置", id);
        }
        commodity.setSerialNumber(serialNumber);

        if (!StringUtils.hasText(showId)) {
            return toEditPage("商品ID未设置", id);
        }
        commodity.setShowId(showId);

        if (!StringUtils.hasText(buyUrl)) {
            return toEditPage("购买链接未设置", id);
        }
        commodity.setBuyUrl(buyUrl);

        if (!StringUtils.hasText(url)) {
            return toEditPage("爬虫链接未设置", id);
        }
        commodity.setUrl(url);

        commodity.setParameters(parameters);

        // add or update
        if (isPositive(id)) {
            if (!(SecurityUtils.isSupperAdmin() ||
                    SecurityUtils.getUserId().equals(commodity.getUserId()))) {
                LOGGER.error("forbid user {} to edit commodity {}",
                        SecurityUtils.getUsername(), commodity.getName());
                return toEditPage("您无权修改此商品!", id);
            }

            try {
                HibernateUtils.update(commodity);
                logAction("更新商品'%s'", commodity.getName());
                LOGGER.info("Update Commodity {}", commodity.getId());
            } catch (Exception e) {
                logAction("更新商品'%s'失败", commodity.getName());
                LOGGER.error("fail to Update Commodity {}, info: {}", commodity.getId(), e);
                return toEditPage("更新商品失败", id);
            }
        } else {
            commodity.setActive(true);
            commodity.setUserId(SecurityUtils.getUserId());
            commodity.setInLowPrice(false);
            try {
                id = HibernateUtils.save(commodity);
                logAction("添加商品'%s'", commodity.getName());
                LOGGER.info("Insert new Commodity {}", id);
            } catch (Exception e) {
                logAction("添加商品'%s'失败", commodity.getName());
                LOGGER.error("fail to insert new Commodity: {}", e);
                return toEditPage("添加商品失败", id);
            }
        }

        try {
            AppraiseGroupDao appraiseGroupDao = new AppraiseGroupDao();
            appraiseGroupDao.clearAndSave(id, positiveAppraiseGroups, true);
            appraiseGroupDao.clearAndSave(id, negativeAppraiseGroups, false);
        } catch (Exception e) {
            LOGGER.error("fail to add appraiseGroups, id:{}, positiveAppraiseGroups:{}, negativeAppraiseGroups:{}, info:{}",
                    id, Arrays.toString(positiveAppraiseGroups), Arrays.toString(negativeAppraiseGroups), e);
            return toEditPage("添加商品评价失败", id);
        }

        CommodityPictureDownloader pictureDownloader = new CommodityPictureDownloader(imageDownloader, pictureUrlConverter);
        pictureDownloader.setLocalAddress(getLocalAddress());
        try {
            if (imageUrls == null) {
                imageUrls = new String[0];
            }
            LOGGER.info("start saving images");
            pictureDownloader.clearAndSave(id, Arrays.asList(imageUrls));
            LOGGER.info("complete saving images");
        } catch (Exception e) {
            LOGGER.error("fail to save commodity images, imageUrls: {}, info: {}",
                    Arrays.toString(imageUrls), e);
            return toEditPage("保存商品图片失败", id);
        }

        try {
            if (detailImageUrls == null) {
                detailImageUrls = new String[0];
            }
            LOGGER.info("start saving detail images");
            pictureDownloader.clearAndSaveDetail(id, Arrays.asList(detailImageUrls));
            LOGGER.info("complete saving detail images");
        } catch (Exception e) {
            LOGGER.error("fail to save commodity detail images, detailImageUrls: {}, info: {}",
                    Arrays.toString(detailImageUrls), e);
            return toEditPage("保存商品描述图片失败", id);
        }

        return redirect(EDIT_PAGE + "?id=" + id);
    }

    private String toEditPage(String errorInfo, Integer id) {
        request.setAttribute("errorInfo", errorInfo);
        if (isPositive(id)) {
            return redirect(EDIT_PAGE + "?id=" + id);
        } else {
            return redirect(EDIT_PAGE);
        }
    }

    private Integer getCategoryId(Integer id1, Integer id2) {
        if (!isPositive(id2)) {
            return id1;
        } else {
            return id2;
        }
    }

    private Integer getBranchId(Integer id1, Integer id2, Integer id3) {
        if (!isPositive(id2)) {
            return id1;
        } else if (!isPositive(id3)) {
            return id2;
        } else {
            return id3;
        }
    }

    private Commodity getCommodity(int id) {
        return new CommodityDao().getInstance(id);
    }

    private Commodity newCommodity() {
        Commodity commodity = new Commodity();
        commodity.setSerialNumber(RandomStringUtils.randomNumeric(12));
        return commodity;
    }
}
