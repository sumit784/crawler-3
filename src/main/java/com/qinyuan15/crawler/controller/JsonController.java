package com.qinyuan15.crawler.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Parent class of Controller return json
 * Created by qinyuan on 14-12-27.
 */
abstract public class JsonController {

    public final static String emptyListJson = "[]";
    public final static String emptyMapJson = "{}";

    protected String toJson(Object obj) {
        return toJson(obj, false);
    }

    protected String toJson(Object obj, boolean prettyFormat) {
        GsonBuilder builder = new GsonBuilder();
        if (prettyFormat) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();
        return gson.toJson(obj);
    }
}
