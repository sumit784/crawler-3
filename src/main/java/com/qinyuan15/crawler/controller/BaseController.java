package com.qinyuan15.crawler.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class of controller
 * Created by qinyuan on 15-2-22.
 */
@Component
public class BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

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

    protected boolean isPositive(Integer intValue) {
        return intValue != null && intValue > 0;
    }

    protected boolean isPositive(String strValue) {
        return NumberUtils.isNumber(strValue) && NumberUtils.toInt(strValue) > 0;
    }

    protected void debugParameters() {
        @SuppressWarnings("unchecked")
        Map<String, String[]> map = request.getParameterMap();
        String result = "";
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            result += "\n" + entry.getKey() + ": " + Arrays.toString(entry.getValue());
        }
        LOGGER.info(result);
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
