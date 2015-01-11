package com.qinyuan15.crawler.core;

import com.qinyuan15.crawler.core.html.HtmlParser;
import com.qinyuan15.crawler.lib.TestFileUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.*;

/**
 * Test Case of HtmlParser
 * Created by qinyuan on 14-12-24.
 */
public class HtmlParserTest {
    private String html;

    @Before
    public void setUp() throws Exception {
        html = TestFileUtils.read("baidu.html");
    }

    @Test
    public void testGetInnerHTML() throws Exception {
        HtmlParser parser = new HtmlParser(html);
        assertThat(parser.getInnerHTML("body")).isNotEmpty();
    }

    @Test
    public void testGetTitle() throws Exception {
        HtmlParser parser = new HtmlParser(html);
        assertThat(parser.getTitle()).isEqualTo("百度一下，你就知道");
    }
}
