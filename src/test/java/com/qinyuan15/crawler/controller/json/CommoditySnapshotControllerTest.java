package com.qinyuan15.crawler.controller.json;

import org.junit.Test;

import static com.qinyuan15.crawler.controller.ControllerTestUtils.injectImageDownloader;
import static com.qinyuan15.crawler.controller.ControllerTestUtils.injectRequest;

/**
 * Test CommoditySnapshotController
 * Created by qinyuan on 15-3-14.
 */
public class CommoditySnapshotControllerTest {
    @Test
    public void testIndex() throws Exception {
        CommoditySnapshotController controller = new CommoditySnapshotController();
        injectRequest(controller);
        injectImageDownloader(controller);

        //System.out.println(controller.index(1, true, null, null));
        System.out.println(controller.index(2, true, "price", "asc"));
    }
}
