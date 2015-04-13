package com.qinyuan15.crawler.ui;

import com.qinyuan15.crawler.dao.SeoKeyword;
import com.qinyuan15.crawler.dao.SeoKeywordDao;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Tool class about SeoKeyword
 * Created by qinyuan on 15-4-12.
 */
public class SeoKeywordUtils {
    private SeoKeywordUtils() {
    }

    public static String getKeyword(HttpServletRequest request) {
        String uri = request.getRequestURI();

        List<SeoKeyword> seoKeywords = getMatchSeoKeywords(uri);
        if (seoKeywords.size() == 0) {
            return "";
        } else {
            return getLongestUrlKeyword(seoKeywords).getWord();
        }
    }

    private static SeoKeyword getLongestUrlKeyword(List<SeoKeyword> keywords) {
        SeoKeyword result = null;
        for (SeoKeyword seoKeyword : keywords) {
            if (result == null) {
                result = seoKeyword;
            } else {
                if (seoKeyword.getUrl().length() > result.getUrl().length()) {
                    result = seoKeyword;
                }
            }
        }
        return result;
    }

    private static List<SeoKeyword> getMatchSeoKeywords(String uri) {
        List<SeoKeyword> keywords = new ArrayList<>();
        for (SeoKeyword seoKeyword : new SeoKeywordDao().getInstances()) {
            if (uri.contains(seoKeyword.getUrl())) {
                keywords.add(seoKeyword);
            }
        }
        return keywords;
    }
}
