package com.qinyuan15.crawler.controller;

import org.junit.Test;

/**
 * Test CommodityController
 * Created by qinyuan on 15-1-14.
 */
public class CommodityControllerTest {

    private CommodityController controller = new CommodityController();

    @Test
    public void testGet() throws Exception {
        System.out.println(controller.get("true", null));
    }

    @Test
    public void testAdd() throws Exception {
        //java.util.Map<String, Object> result = controller.add("hello", "http://www.baidu.com", 10.0, "2012-01-01");
        //System.out.println(result);
    }
}
