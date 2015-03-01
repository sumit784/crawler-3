package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.commodity.CommodityPictureDownloader;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.AppraiseGroupDao;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.dao.HibernateUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * Page to edit commodity
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminEditCommodityController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminEditCommodityController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ImageDownloader imageDownloader;

    @RequestMapping("/admin-edit-commodity")
    public String index(ModelMap model) {
        String idString = request.getParameter("id");
        if (isPositive(idString)) {
            setTitle("编辑商品");
            Integer id = NumberUtils.toInt(idString);
            model.addAttribute("commodity", getCommodity(id));
            AppraiseGroupDao appraiseGroupDao = new AppraiseGroupDao();
            model.addAttribute("positiveAppraiseGroups",
                    appraiseGroupDao.getInstancesByCommodityId(id, true));
            model.addAttribute("negativeAppraiseGroups",
                    appraiseGroupDao.getInstancesByCommodityId(id, false));
        } else {
            setTitle("添加商品");
            model.addAttribute("commodity", newCommodity());
        }

        return "admin-edit-commodity";
    }

    @RequestMapping("/admin-commodity-add-update")
    @ResponseBody
    public Map<String, Object> addUpdate(
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
            @RequestParam(value = "deleteSubmit", required = false) String deleteSubmit,
            @RequestParam(value = "activateSubmit", required = false) String activateSubmit,
            @RequestParam(value = "deactivateSubmit", required = false) String deactivateSubmit,
            @RequestParam(value = "publishSubmit", required = false) String publishSubmit,
            @RequestParam(value = "positiveAppraiseGroups", required = false) String[] positiveAppraiseGroups,
            @RequestParam(value = "negativeAppraiseGroups", required = false) String[] negativeAppraiseGroups,
            @RequestParam(value = "imageUrls", required = false) String[] imageUrls,
            @RequestParam(value = "detailImageUrls", required = false) String[] detailImageUrls) {
        //debugParameters();

        if (deleteSubmit != null) {
            if (isPositive(id)) {
                new CommodityDao().delete(id);
            }
            return SUCCESS;
        } else if (activateSubmit != null) {
            if (isPositive(id)) {
                new CommodityDao().activate(id);
            }
            return SUCCESS;
        } else if (deactivateSubmit != null) {
            if (isPositive(id)) {
                new CommodityDao().deactivate(id);
            }
            return SUCCESS;
        }


        if (publishSubmit == null) {
            return createFailResult("未知错误");
        }

        Commodity commodity = isPositive(id) ? HibernateUtil.get(Commodity.class, id) : new Commodity();

        if (!isPositive(branchId)) {
            return createFailResult("品牌未设置");
        }
        commodity.setBranchId(getBranchId(branchId, subBranch1Id, subBranch2Id));

        if (!isPositive(categoryId)) {
            return createFailResult("商品分类未设置");
        }
        commodity.setCategoryId(getCategoryId(categoryId, subCategoryId));

        if (!StringUtils.hasText(commodityName)) {
            return createFailResult("名称未设置");
        }
        commodity.setName(commodityName);

        if (!StringUtils.hasText(serialNumber)) {
            return createFailResult("商品编号未设置");
        }
        commodity.setSerialNumber(serialNumber);

        if (!StringUtils.hasText(showId)) {
            return createFailResult("商品ID未设置");
        }
        commodity.setShowId(showId);

        if (!StringUtils.hasText(buyUrl)) {
            return createFailResult("购买链接未设置");
        }
        commodity.setBuyUrl(buyUrl);

        if (!StringUtils.hasText(url)) {
            return createFailResult("爬虫链接未设置");
        }
        commodity.setUrl(url);

        commodity.setParameters(parameters);
        commodity.setActive(true);

        if (isPositive(id)) {
            HibernateUtil.update(commodity);
            LOGGER.info("Update Commodity {}", commodity.getId());
        } else {
            id = (Integer) HibernateUtil.save(commodity);
            LOGGER.info("Insert new Commodity {}", id);
        }

        AppraiseGroupDao appraiseGroupDao = new AppraiseGroupDao();
        appraiseGroupDao.clearAndSave(id, positiveAppraiseGroups, true);
        appraiseGroupDao.clearAndSave(id, negativeAppraiseGroups, false);

        CommodityPictureDownloader pictureDownloader = new CommodityPictureDownloader(imageDownloader);
        pictureDownloader.setLocalAddress(getLocalAddress());
        if (imageUrls != null) {
            pictureDownloader.clearAndSave(id, Arrays.asList(imageUrls));
        }
        if (detailImageUrls != null) {
            pictureDownloader.clearAndSaveDetail(id, Arrays.asList(detailImageUrls));
        }

        return SUCCESS;
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
