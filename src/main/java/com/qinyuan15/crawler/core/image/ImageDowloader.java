package com.qinyuan15.crawler.core.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Class to download image from certain url
 * Created by qinyuan on 15-1-14.
 */
public class ImageDowloader {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageDowloader.class);

    private String saveDir;

    public ImageDowloader(String saveDir) {
        this.saveDir = saveDir;
    }

    /**
     * Save image of certain url as image then return the save path
     *
     * @param url url of image to save
     * @return save path of the image
     */
    public String save(String url) {
        String savePath = getSavePath(url);
        try {
            // create input stream and output stream
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(savePath));

            // save file
            byte[] buf = new byte[2048];
            int length;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            in.close();
            out.close();

            return savePath;
        } catch (Exception e) {
            LOGGER.error("fail to save image {} to path {}: {}", url, savePath, e);
            throw new RuntimeException(e);
        }
    }

    private String getSavePath(String url) {
        String fileName = url.substring(url.lastIndexOf("/"));
        String savePath = this.saveDir + fileName;
        if (!new File(savePath).exists()) {
            return savePath;
        }


        // if savePath already exists, create a new one
        int extesionNameIndex = fileName.lastIndexOf(".");
        String fileNameWithoutExtension, extensionName;
        if (extesionNameIndex > 0 && extesionNameIndex < fileName.length() - 1) {
            fileNameWithoutExtension = this.saveDir + fileName.substring(0, extesionNameIndex);
            extensionName = fileName.substring(extesionNameIndex);
        } else {
            fileNameWithoutExtension = this.saveDir + fileName;
            extensionName = "";
        }
        int i = 1;
        while (true) {
            savePath = fileNameWithoutExtension + "_" + i + extensionName;
            if (new File(savePath).exists()) {
                i++;
            } else {
                return savePath;
            }
        }
    }
}
