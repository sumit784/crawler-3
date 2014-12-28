package com.qinyuan15.crawler.controller;

import org.junit.Test;

/**
 * Test BranchController
 * Created by qinyuan on 14-12-27.
 */
public class BranchControllerTest {
    @Test
    public void testGet() throws Exception {
        BranchController controller = new BranchController();
        System.out.println(controller.get("true", 0));
        System.out.println("=====================");
        System.out.println(controller.get("true", 1));
        System.out.println("=====================");
        System.out.println(controller.get("true", 10));
    }
}
