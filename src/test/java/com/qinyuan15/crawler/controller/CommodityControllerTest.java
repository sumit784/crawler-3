package com.qinyuan15.crawler.controller;

import org.junit.Test;

/**
 * Test CommodityController
 * Created by qinyuan on 15-1-14.
 */
public class CommodityControllerTest {
    @Test
    public void testGet() throws Exception {
        CommodityController controller = new CommodityController();
        System.out.println(controller.get("true", null));
    }
}
