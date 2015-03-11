package com.qinyuan15.crawler.core.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class to compress image
 * Created by qinyuan on 15-3-9.
 */
public class ImageCompressor {

    private String sourcePath;
    private BufferedImage sourceImage;

    public ImageCompressor(String sourcePath) {
        try {
            this.sourcePath = sourcePath;
            if (isJPG(sourcePath)) {
                JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(
                        new FileInputStream(sourcePath));
                this.sourceImage = decoder.decodeAsBufferedImage();
            } else {
                this.sourceImage = ImageIO.read(new File(sourcePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isJPG(String sourcePath) {
        sourcePath = sourcePath.toLowerCase();
        return sourcePath.endsWith(".jpg") || sourcePath.endsWith(".jpeg")
                || sourcePath.endsWith(".jpe");
    }

    public void compress(String targetPath, double rate) {
        int width = (int) (this.sourceImage.getWidth() * rate);
        int height = (int) (this.sourceImage.getHeight() * rate);
        compress(targetPath, width, height);
    }

    public void compress(String targetPath, int width, int height) {
        try {
            File targetFile = new File(targetPath);
            BufferedImage targetImage = Scalr.resize(sourceImage, Scalr.Method.QUALITY,
                    Scalr.Mode.AUTOMATIC, width, height, Scalr.OP_ANTIALIAS);
            ImageIO.write(targetImage, getFormatName(), targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compress(String targetPath, ImageSize size) {
        this.compress(targetPath, size.width, size.height);
    }

    private String getFormatName() {
        sourcePath = sourcePath.toLowerCase();
        if (sourcePath.endsWith(".png")) {
            return "png";
        } else if (sourcePath.endsWith(".gif")) {
            return "gif";
        } else {
            return "jpeg";
        }
    }
}
