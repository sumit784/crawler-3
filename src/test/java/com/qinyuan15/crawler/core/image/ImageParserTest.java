package com.qinyuan15.crawler.core.image;

import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ImageParser
 * Created by qinyuan on 15-3-10.
 */
public class ImageParserTest {
    @Test
    public void test() throws Exception {
        String path = TestFileUtils.getAbsolutePath("meituan.png");
        ImageParser imageParser = new ImageParser(path);

        assertThat(imageParser.getWidth()).isEqualTo(911);
        assertThat(imageParser.getHeight()).isEqualTo(399);
    }
}
