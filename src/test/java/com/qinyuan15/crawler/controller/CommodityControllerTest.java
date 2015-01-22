package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.core.image.ImageDownloader;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test CommodityController
 * Created by qinyuan on 15-1-14.
 */
public class CommodityControllerTest {

    private CommodityController controller = new CommodityController();

    @Before
    public void setUp() throws Exception {
        ImageDownloader imageDownloader = new ImageDownloader("/var/ftp");
        Whitebox.getField(CommodityController.class, "imageDownloader").set(controller, imageDownloader);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getLocalAddr()).thenReturn("192.168.8.1");
        Whitebox.getField(CommodityController.class, "request").set(controller, request);
    }

    @Test
    public void testGet() throws Exception {
        System.out.println(controller.get("true", null));
    }

    @Test
    public void testAdd() throws Exception {
        //java.util.Map<String, Object> result = controller.add("hello", "http://www.baidu.com", 10.0, "2012-01-01");
        //System.out.println(result);
    }
}
