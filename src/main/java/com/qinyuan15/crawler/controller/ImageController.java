package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.core.branch.BranchUrlAdapter;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.core.image.ThumbnailType;
import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.CommodityPicture;
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

    protected List<String> parseCommodityPictureUrls(List<CommodityPicture> commodityPictures) {
        return getPictureUrlConverter().pathsToUrls(commodityPictures);
    }

    protected List<String> parseCommodityPictureMiddleUrls(List<CommodityPicture> commodityPictures){
        return getPictureUrlConverter().setThumbnailType(ThumbnailType.MIDDLE)
                .pathsToUrls(commodityPictures);
    }

    protected List<String> parseCommodityPictureSmallUrls(List<CommodityPicture> commodityPictures) {
        return getPictureUrlConverter().setThumbnailType(ThumbnailType.SMALL)
                .pathsToUrls(commodityPictures);
    }

    protected PictureUrlConverter getPictureUrlConverter() {
        return new PictureUrlConverter(imageDownloader, getLocalAddress());
    }

    protected BranchUrlAdapter getBranchUrlAdapter() {
        return new BranchUrlAdapter(imageDownloader, getLocalAddress());
    }

    protected List<Branch> adjustBranches(List<Branch> branches) {
        return getBranchUrlAdapter().adjust(branches);
    }

    protected Branch adjustBranch(Branch branch) {
        return getBranchUrlAdapter().adjust(branch);
    }
}
