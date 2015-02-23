package com.qinyuan15.crawler.core.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to download image from certain url
 * Created by qinyuan on 15-1-14.
 */
// TODO this class doesn't use proxy
public class ImageDownloader {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageDownloader.class);

    private String saveDir;

    public ImageDownloader(String saveDir) {
        this.saveDir = saveDir;
    }

    public String getSaveDir() {
        return this.saveDir;
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
            File saveFile = new File(savePath);
            File parentDir = saveFile.getParentFile();
            if (!parentDir.isDirectory() && !parentDir.mkdirs()) {
                throw new RuntimeException("fail to create directory " + parentDir.getAbsolutePath());
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));

            // save file
            byte[] buf = new byte[2048];
            int length;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            in.close();
            out.close();

            LOGGER.info("save image {} to {}", url, savePath);

            return savePath;
        } catch (Exception e) {
            LOGGER.error("fail to save image {} to path {}: {}", url, savePath, e);
            throw new RuntimeException(e);
        }
    }

    public List<String> save(List<String> urls) {
        List<String> savePaths = new ArrayList<String>();
        for (String url : urls) {
            try {
                savePaths.add(this.save(url));
            } catch (Exception e) {
                LOGGER.error("fail to download {}: {}", url, e);
            }
        }
        return savePaths;
    }

    private String getSavePath(String url) {
        url = url.replaceAll("^.*\\://", "");
        return this.saveDir + "/" + url;
    }
}
