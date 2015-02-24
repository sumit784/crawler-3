package com.qinyuan15.crawler.controller;

import org.powermock.reflect.Whitebox;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tool class for controller test case
 * Created by qinyuan on 15-2-17.
 */
public class ControllerTestUtils {
    public static void injectRequest(BaseController controller) throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getLocalAddr()).thenReturn("192.168.8.1");
        when(request.getParameter("pretty")).thenReturn("true");
        inject(controller, "request", request);
    }

    public static void inject(BaseController controller, String fieldName, Object value) throws Exception {
        Whitebox.getField(controller.getClass(), fieldName).set(controller, value);
    }

    public static ModelMap mockModelMap() {
        return new ModelMap();
    }
}

