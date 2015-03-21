package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.util.List;

/**
 * Test HotSearchWordDao
 * Created by qinyuan on 15-2-28.
 */
public class HotSearchWordDaoTest {
    @Test
    public void testGetInstances() throws Exception {
        HotSearchWordDao dao = new HotSearchWordDao();
        List<HotSearchWord> hotSearchWords = dao.getInstances(5);
        System.out.println(hotSearchWords.size());
    }
}
