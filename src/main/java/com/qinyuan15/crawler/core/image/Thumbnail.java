package com.qinyuan15.crawler.core.image;

import java.io.File;

/**
 * Class about image thumbnail
 * Created by qinyuan on 15-3-10.
 */
public class Thumbnail {
    private final static String SMALL_SUFFIX = "thumbnail_small";
    private final static String MIDDLE_SUFFIX = "thumbnail_middle";

    public String buildSmall(String imagePath) {
        String path = getPath(imagePath, SMALL_SUFFIX);
        return buildThumbnail(imagePath, path, ImageSize.SMALL);
    }

    public String buildMiddle(String imagePath) {
        String path = getPath(imagePath, MIDDLE_SUFFIX);
        return buildThumbnail(imagePath, path, ImageSize.MIDDLE);
    }

    private String buildThumbnail(String imagePath, String targetPath, ImageSize size) {
        ImageCompressor compressor = new ImageCompressor(imagePath);
        compressor.compress(targetPath, size);
        return targetPath;
    }

    public String getSmall(String imagePath) {
        String path = getPath(imagePath, SMALL_SUFFIX);
        if (new File(path).isFile()) {
            return path;
        } else {
            return buildSmall(imagePath);
        }
    }

    public String getMiddle(String imagePath) {
        String path = getPath(imagePath, MIDDLE_SUFFIX);
        if (new File(path).isFile()) {
            return path;
        } else {
            return buildMiddle(imagePath);
        }
    }

    private String getPath(String imagePath, String suffix) {
        int extendNameIndex = imagePath.lastIndexOf('.');
        if (extendNameIndex >= 0) {
            return imagePath.substring(0, extendNameIndex) + "_" +
                    suffix + imagePath.substring(extendNameIndex);
        } else {
            return imagePath + "_" + suffix;
        }
    }
}
