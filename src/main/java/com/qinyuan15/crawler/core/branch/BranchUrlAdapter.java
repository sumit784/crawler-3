package com.qinyuan15.crawler.core.branch;

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

    public void adjust(List<Branch> branches) {
        for (Branch branch : branches) {
            branch.setLogo(this.pictureUrlConverter.pathToUrl(branch.getLogo()));
        }
    }
}
