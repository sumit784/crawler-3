package com.qinyuan15.crawler.core.category;

import com.qinyuan15.crawler.core.image.PictureUrlConverter;
import com.qinyuan15.crawler.dao.CategoryPoster;

import java.util.List;

/**
 * Adjust url of category poster
 * Created by qinyuan on 15-2-25.
 */
public class CategoryPosterUrlAdapter {
    private PictureUrlConverter pictureUrlConverter;

    public CategoryPosterUrlAdapter(PictureUrlConverter pictureUrlConverter) {
        this.pictureUrlConverter = pictureUrlConverter;
    }

    public List<CategoryPoster> adjust(List<CategoryPoster> categoryPosters) {
        for (CategoryPoster poster : categoryPosters) {
            adjust(poster);
        }
        return categoryPosters;
    }

    public CategoryPoster adjust(CategoryPoster categoryPoster) {
        categoryPoster.setPath(this.pictureUrlConverter.pathToUrl(categoryPoster.getPath()));
        return categoryPoster;
    }
}
