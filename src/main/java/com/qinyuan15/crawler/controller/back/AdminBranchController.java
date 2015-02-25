package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.branch.BranchUrlAdapter;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.BranchDao;
import com.qinyuan15.crawler.dao.HibernateUtil;
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
import java.util.List;
import java.util.Map;

/**
 * Page to edit commodity
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminBranchController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminBranchController.class);

    @Autowired
    private ImageDownloader imageDownloader;

    @RequestMapping("/admin-branch")
    @SuppressWarnings("unchecked")
    public String index(ModelMap model) {
        List<Branch> branches = new BranchDao().getInstances();
        PictureUrlConverter pictureUrlConverter = new PictureUrlConverter(imageDownloader, request.getLocalAddr());
        BranchUrlAdapter branchUrlAdapter = new BranchUrlAdapter(pictureUrlConverter);
        branchUrlAdapter.adjust(branches);

        model.addAttribute("branches", branches);
        model.addAttribute("host", request.getLocalAddr());
        setTitle("编辑品牌");
        return "admin-branch";
    }

    private String getLogoUrl(String logoUrl, MultipartFile logoFile) throws IOException {
        if (logoFile == null) {
            String filePath = imageDownloader.save(logoUrl);
            LOGGER.info("save upload image to {}", filePath);
            return filePath;
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
                                         @RequestParam(value = "parentId", required = true) Integer parentId,
                                         @RequestParam(value = "firstLetter", required = true) String firstLetter) {
        String logoUrl;
        try {
            logoUrl = getLogoUrl(logo, logoFile);
        } catch (Exception e) {
            LOGGER.error("fail to deal with upload logo, logo:{}, logoFile:{}, error:{}"
                    , logo, logoFile, e);
            return createFailResult("logo文件处理失败!");
        }

        boolean update = (id != null && id > 0);
        Session session = HibernateUtil.getSession();

        Branch branch = update ? (Branch) session.get(Branch.class, id) : new Branch();
        branch.setName(name);
        branch.setLogo(logoUrl);
        branch.setParentId(parentId);

        if (StringUtils.hasText(firstLetter)) {
            branch.setFirstLetter(firstLetter.substring(0, 1));
        }

        if (update) {
            session.update(branch);
        } else {
            session.save(branch);
        }
        HibernateUtil.commit(session);
        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/admin-branch-delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        HibernateUtil.delete(Branch.class, id);
        return SUCCESS;
    }
}
