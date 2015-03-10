package com.qinyuan15.crawler.core.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Class to parse image
 * Created by qinyuan on 15-3-10.
 */
public class ImageParser {
    private BufferedImage image;

    public ImageParser(BufferedImage image) {
        this.image = image;
    }

    public ImageParser(String sourcePath) throws Exception {
        this(ImageIO.read(new File(sourcePath)));
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
