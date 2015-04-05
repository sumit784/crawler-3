package com.qinyuan15.crawler.controller.back;

import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.List;

import static com.qinyuan15.crawler.controller.ControllerTestUtils.*;

/**
 * Test AdminController
 * Created by qinyuan on 15-2-24.
 */
public class AdminControllerTest {
    @Test
    public void testIndex() throws Exception {
        AdminController controller = new AdminController();
        injectRequest(controller);
        injectImageDownloader(controller);

        ModelMap modelMap = mockModelMap();
        controller.index(modelMap, null);
        List commodities = (List) modelMap.get("commodities");
        System.out.println(commodities.size());
    }
}
