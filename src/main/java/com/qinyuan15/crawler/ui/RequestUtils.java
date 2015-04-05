package com.qinyuan15.crawler.ui;

import com.qinyuan15.crawler.dao.AppConfig;

import javax.servlet.http.HttpServletRequest;

/**
 * Tool Class of
 * Created by qinyuan on 15-3-27.
 */
public class RequestUtils {
    private RequestUtils() {
    }

    private final static String CSS_PREFIX = "resources/css/";
    private final static String JS_PREFIX = "resources/js/";

    public static String getCss(String fileName) {
        return CSS_PREFIX + fileName + ".css?t=" + AppConfig.VERSION;
    }

    public static String getJs(String fileName) {
        return JS_PREFIX + fileName + ".js?t=" + AppConfig.VERSION;
    }

    private static String getFileInURI(HttpServletRequest request) {
        return request.getRequestURI().replaceAll("^.*/", "").replace(".jsp", "");
    }

    public static String getRelativeCss(HttpServletRequest request) {
        return getCss(getFileInURI(request));
    }

    public static String getRelativeJs(HttpServletRequest request) {
        return getJs(getFileInURI(request));
    }
}
