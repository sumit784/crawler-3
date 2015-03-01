package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test Shoppe
 * Created by qinyuan on 15-3-1.
 */
public class ShoppeTest {
    @Test
    public void test() {
        System.out.println(HibernateUtil.getList(Shoppe.class).size());
    }
}
