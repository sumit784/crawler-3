package com.qinyuan15.crawler.controller.json;

import org.junit.Test;

import static com.qinyuan15.crawler.controller.ControllerTestUtils.injectRequest;

/**
 * Test CommodityValidatorController
 * Created by qinyuan on 15-2-28.
 */
public class CommodityValidatorControllerTest {
    @Test
    public void testIndex() throws Exception {
        String showId = "43756398361";
        CommodityValidatorController controller = new CommodityValidatorController();
        injectRequest(controller);
        String result = controller.index(null, showId);
        System.out.println(result);

        result = controller.index(7, showId);
        System.out.println(result);

        result = controller.index(6, showId);
        System.out.println(result);
    }
}
