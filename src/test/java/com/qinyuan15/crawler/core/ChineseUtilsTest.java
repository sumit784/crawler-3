package com.qinyuan15.crawler.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ChineseUtils
 * Created by qinyuan on 15-2-25.
 */
public class ChineseUtilsTest {
    @Test
    public void testGetPhoneticLetter() throws Exception {
        String testString = "我的奋斗";
        String result = ChineseUtils.getPhoneticLetter(testString);
        assertThat(result).isEqualTo("WDFD");

        System.out.println(ChineseUtils.getPhoneticLetter("小"));
    }
}
