package com.qinyuan15.crawler.core.image;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to filter images
 * Created by qinyuan on 15-3-21.
 */
public class ImageFilter {
    private ImageSize filterSize;

    public ImageFilter setFilterSize(ImageSize filterSize) {
        this.filterSize = filterSize;
        return this;
    }

    private boolean matchFilterSize(String path) {
        if (this.filterSize == null) {
            return true;
        }

        ImageParser imageParser = new ImageParser(path);
        return imageParser.getHeight() > this.filterSize.height ||
                imageParser.getWidth() > this.filterSize.width;
    }


    public List<String> filterSize(List<String> paths) {
        List<String> filteredPaths = new ArrayList<>();
        if (paths == null) {
            return filteredPaths;
        }

        for (String path : this.filterNull(paths)) {
            if (this.matchFilterSize(path)) {
                filteredPaths.add(path);
            }
        }
        return filteredPaths;
    }

    public List<String> filterNull(List<String> paths) {
        List<String> filteredPaths = new ArrayList<>();
        for (String path : paths) {
            if (path != null) {
                filteredPaths.add(path);
            }
        }
        return filteredPaths;
    }
}
