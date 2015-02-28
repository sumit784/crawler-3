package com.qinyuan15.crawler.dao;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test HotSearchWord
 * Created by qinyuan on 15-2-28.
 */
public class HotSearchWordTest {

    @Test
    public void test() throws Exception {
        for (HotSearchWord hotSearchWord : HibernateUtil.getList(HotSearchWord.class)) {
            assertThat(hotSearchWord.getId()).isGreaterThan(0);
        }
    }
}
