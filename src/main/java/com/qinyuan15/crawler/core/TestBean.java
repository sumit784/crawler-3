package com.qinyuan15.crawler.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Test Class for String MVC
 * Created by qinyuan on 15-1-9.
 */
public class TestBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestBean.class);

    public void log() {
        String info = "TestBean#log() is called";
        LOGGER.debug(info);
        LOGGER.info(info);
        LOGGER.warn(info);
        LOGGER.error(info);
    }
}
