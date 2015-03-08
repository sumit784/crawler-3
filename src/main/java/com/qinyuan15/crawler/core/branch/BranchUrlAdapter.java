package com.qinyuan15.crawler.core.branch;

import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.dao.Branch;

import java.util.List;

/**
 * Adjust url of branch
 * Created by qinyuan on 15-2-25.
 */
public class BranchUrlAdapter {
    private PictureUrlConverter pictureUrlConverter;

    public BranchUrlAdapter(PictureUrlConverter pictureUrlConverter) {
        this.pictureUrlConverter = pictureUrlConverter;
    }

    public BranchUrlAdapter(ImageDownloader imageDownloader, String localAddress) {
        this(new PictureUrlConverter(imageDownloader, localAddress));
    }

    public List<Branch> adjust(List<Branch> branches) {
        for (Branch branch : branches) {
            adjust(branch);
        }
        return branches;
    }

    public Branch adjust(Branch branch) {
        branch.setLogo(this.pictureUrlConverter.pathToUrl(branch.getLogo()));
        branch.setSquareLogo(this.pictureUrlConverter.pathToUrl(branch.getSquareLogo()));
        branch.setPoster(this.pictureUrlConverter.pathToUrl(branch.getPoster()));
        return branch;
    }
}
