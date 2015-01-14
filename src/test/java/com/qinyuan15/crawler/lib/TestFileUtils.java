package com.qinyuan15.crawler.lib;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Utils class about test file
 * Created by qinyuan on 15-1-2.
 */
public class TestFileUtils {
    public static String tempDir = System.getProperty("java.io.tmpdir");

    private TestFileUtils() {
    }

    public static String read(String fileName) throws IOException {
        File file = new File("src/test/resources/" + fileName);
        return FileUtils.readFileToString(file, "utf8");
    }
}
