package com.qinyuan15.crawler.core.config;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ArticleUtils
 * Created by qinyuan on 15-4-14.
 */
public class ArticleUtilsTest {
    @Test
    public void testGetTitle() throws Exception {
        String article = "HelloWorld\nAAAAAAAAAAAAAAAAAAAA";
        assertThat(ArticleUtils.getTitle(article)).isEqualTo("HelloWorld");
    }
}
