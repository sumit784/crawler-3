package com.qinyuan15.crawler.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class of controller
 * Created by qinyuan on 15-2-22.
 */
@Component
public class BaseController {

    protected final static Map<String, Object> SUCCESS = createResultMap(true, null);

    protected final static String TITLE = "title";

    protected final static String BLANK = "blank";

    @Autowired
    protected HttpServletRequest request;

    protected void setTitle(Object title) {
        request.setAttribute(TITLE, title);
    }

    protected String toJson(Object obj) {
        GsonBuilder builder = new GsonBuilder();
        if (this.request.getParameter("pretty") != null) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();
        return gson.toJson(obj);
    }

    protected static Map<String, Object> createResultMap(boolean success, Object detail) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        map.put("detail", detail);
        return map;
    }

    protected static Map<String, Object> createFailResult(Object detail) {
        return createResultMap(false, detail);
    }
}
