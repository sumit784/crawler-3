package com.qinyuan15.crawler.core.image;

import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Test;

/**
 * Test ImageCompressor
 * Created by qinyuan on 15-3-9.
 */
public class ImageCompressorTest {
    @Test
    public void testCompress() throws Exception {
        ImageCompressor compressor = new ImageCompressor(TestFileUtils.getAbsolutePath("meituan.png"));

        String targetPath = TestFileUtils.tempDir + "/meituan2.png";
        compressor.compress(targetPath, 0.5);

        targetPath = TestFileUtils.tempDir + "/meituan3.png";
        compressor.compress(targetPath, 300, 400);
    }
}