package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Test;

/**
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParserTest {
    private EtaoCommodityPageParser parser = new EtaoCommodityPageParser();

    @Test
    public void testGetCommodity() throws Exception {
        parser.setHTML(TestFileUtils.read("etao.html"));
        Commodity commodity = parser.getCommodity();
    }
}
