package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.branch.BranchUrlAdapter;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.core.image.PictureUrlValidator;
import com.qinyuan15.crawler.dao.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Page to edit branch
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminBranchController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminBranchController.class);

    @Autowired
    private ImageDownloader imageDownloader;

    @RequestMapping("/admin-branch")
    public String index(ModelMap model) {
        List<Branch> branches = new BranchDao().getInstances();
        PictureUrlConverter pictureUrlConverter = new PictureUrlConverter(imageDownloader, getLocalAddress());
        BranchUrlAdapter branchUrlAdapter = new BranchUrlAdapter(pictureUrlConverter);
        branchUrlAdapter.adjust(branches);

        model.addAttribute("branches", branches);
        setTitle("编辑品牌");
        return "admin-branch";
    }

    private String getLogoUrl(String logoUrl, MultipartFile logoFile) throws IOException {
        if (logoFile == null) {
            if (new PictureUrlValidator(getLocalAddress()).isLocal(logoUrl)) {
                return new PictureUrlConverter(imageDownloader, getLocalAddress()).urlToPath(logoUrl);
            } else {
                String filePath = imageDownloader.save(logoUrl);
                LOGGER.info("save upload image to {}", filePath);
                return filePath;
            }
        } else {
            String relativePath = "mall/branch/logo/" + RandomStringUtils.randomAlphabetic(20)
                    + "_" + logoFile.getOriginalFilename();
            String filePath = imageDownloader.getSaveDir() + "/" + relativePath;
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (!parent.isDirectory() && !parent.mkdirs()) {
                LOGGER.error("fail to create directory {}", parent.getAbsolutePath());
            }
            logoFile.transferTo(file);
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
                                         @RequestParam(value = "parentId", required = true) Integer parentId,
                                         @RequestParam(value = "firstLetter", required = true) String firstLetter,
                                         @RequestParam(value = "slogan", required = true) String slogan,
                                         @RequestParam(value = "shoppeNames", required = true) String[] shoppeNames,
                                         @RequestParam(value = "shoppeUrls", required = true) String[] shoppeUrls) {
        String logoUrl;
        try {
            logoUrl = getLogoUrl(logo, logoFile);
        } catch (Exception e) {
            LOGGER.error("fail to deal with upload logo, logo:{}, logoFile:{}, error:{}"
                    , logo, logoFile, e);
            return createFailResult("矩形Logo文件处理失败!");
        }

        String squareLogoUrl;
        try {
            squareLogoUrl = getLogoUrl(squareLogo, squareLogoFile);
        } catch (Exception e) {
            LOGGER.error("fail to deal with upload squareLogo, squareLogo:{}, squareLogoFile:{}, error:{}"
                    , logo, logoFile, e);
            return createFailResult("方形Logo文件处理失败!");
        }

        Session session = HibernateUtil.getSession();

        Branch branch = isPositive(id) ? (Branch) session.get(Branch.class, id) : new Branch();
        branch.setName(name);
        branch.setLogo(logoUrl);
        branch.setSquareLogo(squareLogoUrl);
        branch.setParentId(parentId);
        branch.setSlogan(slogan);

        if (StringUtils.hasText(firstLetter)) {
            branch.setFirstLetter(firstLetter.substring(0, 1));
        }

        if (isPositive(id)) {
            session.update(branch);
        } else {
            id = (Integer) session.save(branch);
        }
        HibernateUtil.commit(session);

        ShoppeDao dao = new ShoppeDao();
        dao.clear(id);
        dao.save(createShoppes(id, shoppeNames, shoppeUrls));
        return SUCCESS;
    }

    private List<Shoppe> createShoppes(Integer branchId, String[] shoppeNames, String[] shoppeUrls) {
        if (shoppeNames == null || shoppeUrls == null) {
            return new ArrayList<Shoppe>();
        }
        int size = Math.min(shoppeNames.length, shoppeUrls.length);
        List<Shoppe> shoppes = new ArrayList<Shoppe>(size);
        for (int i = 0; i < size; i++) {
            Shoppe shoppe = new Shoppe();
            shoppe.setBranchId(branchId);
            shoppe.setName(shoppeNames[i]);
            shoppe.setUrl(shoppeUrls[i]);
            shoppes.add(shoppe);
        }
        return shoppes;
    }

    private void addShoppes() {
        System.out.println("afdakfjdafj");
        List<Shoppe> shoppes = new ArrayList<Shoppe>();
        System.out.println(request.getParameterMap().keySet());
        @SuppressWarnings("unchecked")
        Enumeration<String> keyEnum = request.getParameterNames();
        while (keyEnum.hasMoreElements()) {
            String key = keyEnum.nextElement();
            System.out.println(key + "------");
            if (key != null && key.startsWith("shoppeName")) {
                String shoppeName = request.getParameter(key);
                String shoppeUrl = request.getParameter(key.replace("shoppeName", "shoppeUrl"));
                System.out.println(shoppeName + " " + shoppeUrl);
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin-branch-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        HibernateUtil.delete(Branch.class, id);
        return SUCCESS;
    }
}
