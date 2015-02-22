package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.dao.HibernateUtil;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static com.qinyuan15.crawler.controller.ControllerTestUtils.*;

/**
 * Test AdminBranchController
 * Created by qinyuan on 15-2-21.
 */
public class AdminBranchControllerTest {
    @Test
    public void testIndex() throws Exception {
        AdminBranchController controller = new AdminBranchController();
        ModelMap modelMap = mockModelMap();
        for (int i = 0; i < 100; i++) {
            controller.index(modelMap);
        }
    }
}
