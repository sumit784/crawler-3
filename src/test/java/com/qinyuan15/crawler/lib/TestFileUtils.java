package com.qinyuan15.crawler.lib;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by qinyuan on 15-1-2.
 */
public class TestFileUtils {
    private TestFileUtils() {
    }

    public static String read(String fileName) throws IOException {
        File file = new File("src/test/resources/" + fileName);
        return FileUtils.readFileToString(file);
    }
}
