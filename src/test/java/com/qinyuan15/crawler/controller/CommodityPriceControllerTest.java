package com.qinyuan15.crawler.controller;

import org.junit.Test;

/**
 * Test CommodityPriceController
 * Created by qinyuan on 15-1-22.
 */
public class CommodityPriceControllerTest {
    private CommodityPriceController controller = new CommodityPriceController();

    @Test
    public void testGetLow() throws Exception {
        System.out.println(controller.getLow(null, null, null, null));
    }

    @Test
    public void testGetHigh() throws Exception {
        System.out.println(controller.getHigh(null, null, null, null));
    }
}
