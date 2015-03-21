package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.core.branch.BranchUrlAdapter;
import com.qinyuan15.crawler.core.commodity.CommodityPictureUtils;
import com.qinyuan15.crawler.core.image.*;
import com.qinyuan15.crawler.core.index.IndexLogoUrlAdapter;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.CommodityPicture;
import com.qinyuan15.crawler.dao.IndexLogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Controller that need to deal with image/picture
 * Created by qinyuan on 15-3-3.
 */
@Component
public class ImageController extends BaseController {
    @Autowired
    protected ImageDownloader imageDownloader;

    @Autowired
    protected PictureUrlConverter pictureUrlConverter;

    private ImageFilter commodityPictureFilter = new ImageFilter().setFilterSize(ImageSize.VERY_SMALL);

    private List<String> getCommodityPictureUrils(List<CommodityPicture> commodityPictures) {
        List<String> paths = CommodityPictureUtils.getUrls(commodityPictures);
        return commodityPictureFilter.filterSize(paths);

    }

    protected List<String> parseCommodityPictureUrls(List<CommodityPicture> commodityPictures) {
        return pictureUrlConverter.pathsToUrls(getCommodityPictureUrils(commodityPictures));
    }

    protected List<String> parseCommodityPictureMiddleUrls(List<CommodityPicture> commodityPictures) {
        List<String> paths = getCommodityPictureUrils(commodityPictures);
        paths = new ThumbnailsBuilder().getMiddle(paths);
        return pictureUrlConverter.pathsToUrls(paths);
    }

    protected List<String> parseCommodityPictureSmallUrls(List<CommodityPicture> commodityPictures) {
        List<String> paths = getCommodityPictureUrils(commodityPictures);
        paths = new ThumbnailsBuilder().getSmall(paths);
        return pictureUrlConverter.pathsToUrls(paths);
    }

    protected BranchUrlAdapter getBranchUrlAdapter() {
        return new BranchUrlAdapter(pictureUrlConverter);
    }

    protected List<IndexLogo> adjustIndexLogos(List<IndexLogo> indexLogos) {
        return new IndexLogoUrlAdapter(pictureUrlConverter).adjust(indexLogos);
    }

    protected List<Branch> adjustBranches(List<Branch> branches) {
        return getBranchUrlAdapter().adjust(branches);
    }

    protected Branch adjustBranch(Branch branch) {
        return getBranchUrlAdapter().adjust(branch);
    }
}
