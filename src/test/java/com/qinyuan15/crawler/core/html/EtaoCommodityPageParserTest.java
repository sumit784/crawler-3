package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParserTest {
    private EtaoCommodityPageParser parser = new EtaoCommodityPageParser();

    @Test
    public void testGetCommodity() throws Exception {
        parser.setHTML(TestFileUtils.read("etao.html"));
        Commodity commodity = parser.getCommodity();
        assertThat(commodity.getOriginalPrice()).isEqualTo(169.0);
        assertThat(commodity.getPrice()).isEqualTo(168.85);
        assertThat(commodity.getName()).isEqualTo("马克珍妮2014秋装宝宝迷彩夹克 儿童开衫外套1-5岁男童装15075");
    }
}
