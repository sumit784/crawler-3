package com.qinyuan15.crawler.core.image;

/**
 * class about image size
 * Created by qinyuan on 15-3-10.
 */
public class ImageSize {
    public final int width;
    public final int height;

    private ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static ImageSize SMALL = new ImageSize(50, 50);
    public static ImageSize MIDDLE = new ImageSize(310, 310);
    public static ImageSize VERY_SMALL = new ImageSize(10, 10);
}
