package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test CategoryDao
 * Created by qinyuan on 15-3-7.
 */
public class CategoryDaoTest {
    private CategoryDao dao = new CategoryDao();

    @Test
    public void testIsUsed() throws Exception {
        System.out.println(dao.isUsed(3));
        System.out.println(dao.isUsed(4));
        System.out.println(dao.isUsed(5));
        System.out.println(dao.isUsed(6));
    }
}
