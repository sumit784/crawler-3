package com.qinyuan15.crawler.core;

import org.junit.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test DateUtils
 * Created by qinyuan on 15-1-11.
 */
public class DateUtilsTest {
    @Test
    public void testNewDate() throws Exception {
        long milliSecondsOfOneDay = 3600 * 24 * 1000;

        Date date = DateUtils.newDate("2011-2-28");
        assertThat(date.toString()).isEqualTo("2011-02-28");
        date.setTime(date.getTime() + milliSecondsOfOneDay); // add one day
        assertThat(date.toString()).isEqualTo("2011-03-01");

        date.setTime(date.getTime() + milliSecondsOfOneDay * 364);
        assertThat(date.toString()).isEqualTo("2012-02-28");
        date.setTime(date.getTime() + milliSecondsOfOneDay);
        assertThat(date.toString()).isEqualTo("2012-02-29");
    }
}
