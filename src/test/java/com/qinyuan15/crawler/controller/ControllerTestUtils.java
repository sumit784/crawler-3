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
    public final static void injectRequest(Object controller) throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getLocalAddr()).thenReturn("192.168.8.1");
        when(request.getParameter("pretty")).thenReturn("true");

        Whitebox.getField(controller.getClass(), "request").set(controller, request);
    }

    public static ModelMap mockModelMap() {
        return new ModelMap();
    }
}

