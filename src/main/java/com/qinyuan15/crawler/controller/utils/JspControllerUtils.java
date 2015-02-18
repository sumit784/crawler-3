package com.qinyuan15.crawler.controller.utils;

import org.springframework.ui.ModelMap;

/**
 * Tool class of jsp controller
 * Created by qinyuan on 15-2-14.
 */
public class JspControllerUtils {
    private JspControllerUtils() {
    }

    public final static String TITLE = "title";

    public final static String BLANK = "blank";

    public static void setTitle(ModelMap model, String title) {
        model.addAttribute(TITLE, title);
    }
}
