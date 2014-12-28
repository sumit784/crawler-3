package com.qinyuan15.crawler.controller;

import org.junit.Test;

/**
 * Test CommodityBasicController
 * Created by qinyuan on 14-12-27.
 */
public class CommodityBasicControllerTest {
    @Test
    public void testGet() throws Exception {
        CommodityBasicController controller = new CommodityBasicController();
        System.out.println(controller.get("true", 0, 0, null));
        System.out.println("=====================");
        System.out.println(controller.get("true", 0, 1, null));
        System.out.println("=====================");
        System.out.println(controller.get("true", 1, 0, null));
        System.out.println("=====================");
        System.out.println(controller.get("true", 0, 0, "ZZ YY"));
    }
}
