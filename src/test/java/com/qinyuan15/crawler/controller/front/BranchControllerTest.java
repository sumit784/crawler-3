package com.qinyuan15.crawler.controller.front;

import org.junit.Test;

import static com.qinyuan15.crawler.controller.ControllerTestUtils.injectRequest;

/**
 * Test BranchController
 * Created by qinyuan on 15-2-23.
 */
public class BranchControllerTest {
    @Test
    public void testQuery() throws Exception {
        BranchController controller = new BranchController();
        injectRequest(controller);
        System.out.println(controller.query(null, null));
        System.out.println(controller.query(7, null));
    }
}
