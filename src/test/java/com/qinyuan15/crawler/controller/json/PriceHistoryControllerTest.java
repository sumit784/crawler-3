package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.json.PriceHistoryController;
import org.junit.Test;

/**
 * Test PriceHistoryController
 * Created by qinyuan on 15-1-13.
 */
public class PriceHistoryControllerTest {
    @Test
    public void testGet() throws Exception {
        PriceHistoryController controller = new PriceHistoryController();
        System.out.println(controller.get(null, null, null, null));
    }
}
