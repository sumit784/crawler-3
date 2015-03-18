package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.image.PictureUrlValidator;
import com.qinyuan15.crawler.dao.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Page to edit branch
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminBranchController extends ImageController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminBranchController.class);

    @RequestMapping("/admin-branch")
    public String index(ModelMap model) {
        List<Branch> branches = new BranchDao().getInstances();
        model.addAttribute("branches", adjustBranches(branches));
        setTitle("编辑品牌");
        return "admin-branch";
    }

    private String getImageUrl(String imageUrl, MultipartFile imageFile) throws IOException {
        if (imageFile == null) {
            if (new PictureUrlValidator(getLocalAddress()).isLocal(imageUrl)) {
                return getPictureUrlConverter().urlToPath(imageUrl);
            } else {
                String filePath = imageDownloader.save(imageUrl);
                LOGGER.info("save upload image to {}", filePath);
                return filePath;
            }
        } else {
            String relativePath = "mall/branch/logo/" + RandomStringUtils.randomAlphabetic(20)
                    + "_" + imageFile.getOriginalFilename();
            String filePath = imageDownloader.getSaveDir() + "/" + relativePath;
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (!parent.isDirectory() && !parent.mkdirs()) {
                LOGGER.error("fail to create directory {}", parent.getAbsolutePath());
            }
            imageFile.transferTo(file);
            LOGGER.info("save upload image to {}", filePath);
            return filePath;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin-branch-add-update", method = RequestMethod.POST)
    public Map<String, Object> addUpdate(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "name", required = true) String name,
                                         @RequestParam(value = "logo", required = true) String logo,
                                         @RequestParam(value = "logoFile", required = false) MultipartFile logoFile,
                                         @RequestParam(value = "squareLogo", required = true) String squareLogo,
                                         @RequestParam(value = "squareLogoFile", required = false) MultipartFile squareLogoFile,
                                         @RequestParam(value = "poster", required = true) String poster,
                                         @RequestParam(value = "posterFile", required = false) MultipartFile posterFile,
                                         @RequestParam(value = "parentId", required = true) Integer parentId,
                                         @RequestParam(value = "firstLetter", required = true) String firstLetter,
                                         @RequestParam(value = "slogan", required = true) String slogan,
                                         @RequestParam(value = "shoppeNames", required = false) String[] shoppeNames,
                                         @RequestParam(value = "shoppeUrls", required = true) String[] shoppeUrls) {
        // deal with logUrl
        String logoUrl;
        try {
            logoUrl = getImageUrl(logo, logoFile);
        } catch (Exception e) {
            LOGGER.error("fail to deal with logoUrl, logo:{}, logoFile:{}, error:{}"
                    , logo, logoFile, e);
            return createFailResult("矩形Logo文件处理失败!");
        }

        // deal with squareLogoUrl
        String squareLogoUrl;
        try {
            squareLogoUrl = getImageUrl(squareLogo, squareLogoFile);
        } catch (Exception e) {
            LOGGER.error("fail to deal with squareLogoUrl, squareLogo:{}, squareLogoFile:{}, error:{}"
                    , logo, logoFile, e);
            return createFailResult("方形Logo文件处理失败!");
        }

        // deal with poster
        String posterUrl;
        try {
            posterUrl = getImageUrl(poster, posterFile);
        } catch (Exception e) {
            LOGGER.error("fail to deal with posterUrl, poster:{}, posterFile:{}, error:{}"
                    , poster, posterFile, e);
            return createFailResult("海报文件处理失败!");
        }

        // build branch object
        Branch branch = isPositive(id) ? new BranchDao().getInstance(id) : new Branch();
        branch.setName(name);
        branch.setLogo(logoUrl);
        branch.setSquareLogo(squareLogoUrl);
        branch.setPoster(posterUrl);
        branch.setParentId(parentId);
        branch.setSlogan(slogan);
        if (StringUtils.hasText(firstLetter)) {
            branch.setFirstLetter(firstLetter.substring(0, 1));
        }

        // save or update branch
        if (isPositive(id)) {
            HibernateUtil.update(branch);
            logAction("更新品牌'%s'", branch.getName());
        } else {
            id = (Integer) HibernateUtil.save(branch);
            logAction("添加品牌'%s'", branch.getName());
        }

        ShoppeDao dao = new ShoppeDao();
        dao.clear(id);
        dao.save(createShoppes(id, shoppeNames, shoppeUrls));
        return SUCCESS;
    }

    private List<Shoppe> createShoppes(Integer branchId, String[] shoppeNames, String[] shoppeUrls) {
        if (shoppeNames == null || shoppeUrls == null) {
            return new ArrayList<>();
        }
        int size = Math.min(shoppeNames.length, shoppeUrls.length);
        List<Shoppe> shoppes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Shoppe shoppe = new Shoppe();
            shoppe.setBranchId(branchId);
            shoppe.setName(shoppeNames[i]);
            shoppe.setUrl(shoppeUrls[i]);
            shoppes.add(shoppe);
        }
        return shoppes;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-branch-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        BranchDao dao = new BranchDao();
        if (dao.isUsed(id)) {
            return createFailResult("该品牌已经被某些商品或其他品牌使用，不允许删除！");
        } else {
            logAction("删除品牌'%s'", dao.getInstance(id).getName());
            dao.delete(id);
            return SUCCESS;
        }
    }
}
