package com.qinyuan15.crawler.controller.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Util class of Controller that return json
 * Created by qinyuan on 14-12-27.
 */
public class JsonControllerUtils {

    public final static String emptyListJson = "[]";
    public final static String emptyMapJson = "{}";
    public final static Map<String, Object> SUCCESS = createResultMap(true, null);

    private JsonControllerUtils() {
    }

    public static String toJson(Object obj) {
        return toJson(obj, false);
    }

    public static String toJson(Object obj, boolean prettyFormat) {
        GsonBuilder builder = new GsonBuilder();
        if (prettyFormat) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();
        return gson.toJson(obj);
    }

    public static Map<String, Object> createResultMap(boolean success, Object detail) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        map.put("detail", detail);
        return map;
    }

    public static Map<String, Object> createFailResult(Object detail) {
        return createResultMap(false, detail);
    }
}
