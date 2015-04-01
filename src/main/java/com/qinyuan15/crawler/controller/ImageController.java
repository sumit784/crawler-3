package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.core.branch.BranchUrlAdapter;
import com.qinyuan15.crawler.core.category.CategoryPosterUrlAdapter;
import com.qinyuan15.crawler.core.commodity.CommodityPictureUtils;
import com.qinyuan15.crawler.core.image.*;
import com.qinyuan15.crawler.core.index.IndexLogoUrlAdapter;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.CategoryPoster;
import com.qinyuan15.crawler.dao.CommodityPicture;
import com.qinyuan15.crawler.dao.IndexLogo;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller that need to deal with image/picture
 * Created by qinyuan on 15-3-3.
 */
@Component
public class ImageController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    protected ImageDownloader imageDownloader;

    private ImageFilter commodityPictureFilter = new ImageFilter().setFilterSize(ImageSize.VERY_SMALL);

    private List<String> getCommodityPictureUrls(List<CommodityPicture> commodityPictures) {
        List<String> paths = CommodityPictureUtils.getUrls(commodityPictures);
        return commodityPictureFilter.filterSize(paths);
    }

    protected List<String> parseCommodityPictureUrls(List<CommodityPicture> commodityPictures) {
        return pictureUrlConverter.pathsToUrls(getCommodityPictureUrls(commodityPictures));
    }

    protected List<String> parseCommodityPictureMiddleUrls(List<CommodityPicture> commodityPictures) {
        List<String> paths = getCommodityPictureUrls(commodityPictures);
        paths = new ThumbnailsBuilder().getMiddle(paths);
        return pictureUrlConverter.pathsToUrls(paths);
    }

    protected List<String> parseCommodityPictureSmallUrls(List<CommodityPicture> commodityPictures) {
        List<String> paths = getCommodityPictureUrls(commodityPictures);
        paths = new ThumbnailsBuilder().getSmall(paths);
        return pictureUrlConverter.pathsToUrls(paths);
    }

    protected BranchUrlAdapter getBranchUrlAdapter() {
        return new BranchUrlAdapter(pictureUrlConverter);
    }

    protected List<IndexLogo> adjustIndexLogos(List<IndexLogo> indexLogos) {
        return new IndexLogoUrlAdapter(pictureUrlConverter).adjust(indexLogos);
    }

    protected List<CategoryPoster> adjustCategoryPosters(List<CategoryPoster> categoryPosters) {
        return new CategoryPosterUrlAdapter(pictureUrlConverter).adjust(categoryPosters);
    }

    protected List<Branch> adjustBranches(List<Branch> branches) {
        return getBranchUrlAdapter().adjust(branches);
    }

    protected Branch adjustBranch(Branch branch) {
        return getBranchUrlAdapter().adjust(branch);
    }

    /**
     * web frontend may post a image url or file, this method validate
     * the  url and upload file and return a local path
     *
     * @param imageUrl       image url posted
     * @param imageFile      image file uploaded
     * @param savePathPrefix save path prefix
     * @return save path
     * @throws IOException
     */
    protected String getSavePath(String imageUrl, MultipartFile imageFile, String savePathPrefix) throws IOException {
        if (imageFile == null) {
            if (new PictureUrlValidator(getLocalAddress()).isLocal(imageUrl)) {
                return pictureUrlConverter.urlToPath(imageUrl);
            } else {
                String filePath = imageDownloader.save(imageUrl);
                LOGGER.info("save upload image to {}", filePath);
                return filePath;
            }
        } else {
            if (!savePathPrefix.endsWith("/")) {
                savePathPrefix = savePathPrefix + "/";
            }
            String relativePath = savePathPrefix + RandomStringUtils.randomAlphabetic(20)
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
}
