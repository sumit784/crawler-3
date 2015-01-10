package com.qinyuan15.crawler.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Tool class about date
 * Created by qinyuan on 15-1-5.
 */
public class DateUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    private DateUtils() {
    }

    /**
     * create Date Object by String such as '2000-12-01'
     * @param dateStr date String such as '2000-12-01'
     * @return a {@link java.sql.Date} instance
     */
    public static Date newDate(String dateStr) {
       try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            LOGGER.error("error in parsing date String '{}': {}", dateStr, e);
            throw new RuntimeException(e);
        }
    }
}
