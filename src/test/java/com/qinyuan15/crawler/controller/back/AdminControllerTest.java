package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.core.image.ImageDownloader;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.springframework.ui.ModelMap;

import java.util.List;

import static com.qinyuan15.crawler.controller.ControllerTestUtils.inject;
import static com.qinyuan15.crawler.controller.ControllerTestUtils.injectRequest;
import static com.qinyuan15.crawler.controller.ControllerTestUtils.mockModelMap;

/**
 * Test AdminController
 * Created by qinyuan on 15-2-24.
 */
public class AdminControllerTest {
    @Test
    public void testIndex() throws Exception {
        AdminController controller = new AdminController();
        ImageDownloader imageDownloader = new ImageDownloader("/var/ftp");
        injectRequest(controller);
        inject(controller, "imageDownloader", imageDownloader);

        ModelMap modelMap = mockModelMap();
        controller.index(modelMap);
        List commodities = (List) modelMap.get("commodities");
        System.out.println(commodities.size());
    }
}
