package com.qinyuan15.crawler.ui;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test SeoKeywordUtils
 * Created by qinyuan on 15-4-13.
 */
public class SeoKeywordUtilsTest {
    @Test
    public void testGetKeyword() throws Exception {
        String testPage = "list?id=7";
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn(testPage);
        System.out.println(SeoKeywordUtils.getKeyword(request));
    }
}
