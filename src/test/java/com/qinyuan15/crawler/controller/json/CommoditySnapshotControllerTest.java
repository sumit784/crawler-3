package com.qinyuan15.crawler.controller.json;

import com.google.common.collect.Lists;
import org.junit.Test;

import static com.qinyuan15.crawler.controller.ControllerTestUtils.*;

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
        injectSnapshotConfig(controller);
        injectPictureUrlConvertor(controller);

        System.out.println(controller.index(null, true, null, null, "price", "asc", null, 0));
    }
}
