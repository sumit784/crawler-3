package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.core.html.ComposableCommodityPageParserTest;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static com.qinyuan15.crawler.controller.ControllerTestUtils.injectRequest;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CommodityCrawlerController
 * Created by qinyuan on 15-2-23.
 */
public class CommodityCrawlerControllerTest {
    @Test
    public void testIndex() throws Exception {
        CommodityCrawlerController controller = new CommodityCrawlerController();
        injectRequest(controller);
        Whitebox.getField(CommodityCrawlerController.class, "pageParser").set(controller,
                ComposableCommodityPageParserTest.mockComposableCommodityPageParser());
        Whitebox.getField(CommodityCrawlerController.class, "httpClientPool").set(controller,
                new HttpClientPool());

        String result = controller.index("http://s.etao.com/detail/40780735321.html");
        System.out.println(result);
        assertThat(result).contains("name").contains("buyUrl").contains("imageUrls")
                .contains("detailImageUrls");
    }
}
