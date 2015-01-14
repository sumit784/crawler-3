package com.qinyuan15.crawler.core.image;

import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ImageDownloader
 * Created by qinyuan on 15-1-14.
 */
public class ImageDowloaderTest {
    private ImageDowloader dowloader;

    @Before
    public void setUp() throws Exception {
        this.dowloader = new ImageDowloader(TestFileUtils.tempDir);
    }


    @Test
    public void testDownload() throws Exception {
        String url = "http://www.baidu.com/img/bdlogo.png";
        String savePath = this.dowloader.save(url);
        assertThat(new File(savePath)).isFile();
    }
}
