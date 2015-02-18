package com.qinyuan15.crawler.core;

/**
 * Configuration class for whole application
 * Created by qinyuan on 15-2-17.
 */
public class ApplicationConfig {
    private static ApplicationConfig INSTANCE = new ApplicationConfig();

    private ApplicationConfig() {
    }

    public String getDefaultEncoding() {
        return "utf-8";
    }

    public static ApplicationConfig getInstance() {
        return INSTANCE;
    }
}
