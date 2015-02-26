package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test Category
 * Created by qinyuan on 15-2-25.
 */
public class CategoryTest {
    @Test
    public void test() throws Exception {
        System.out.println(HibernateUtil.getList(Category.class).size());
    }
}
