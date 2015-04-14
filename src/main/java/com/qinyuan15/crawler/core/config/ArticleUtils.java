package com.qinyuan15.crawler.core.config;

import org.springframework.util.StringUtils;

/**
 * Utility class of article
 * Created by qinyuan on 15-4-14.
 */
public class ArticleUtils {
    private ArticleUtils() {
    }

    public static String getTitle(String content) {
        if (!StringUtils.hasText(content)) {
            return null;
        }

        return content.split("\n")[0].trim();
    }
}
