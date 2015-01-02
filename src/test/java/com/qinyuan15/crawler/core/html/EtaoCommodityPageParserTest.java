package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParserTest {
    private EtaoCommodityPageParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new EtaoCommodityPageParser();
        parser.setHTML(TestFileUtils.read("etao.html"));
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(parser.getName()).isEqualTo("马克珍妮2014秋装宝宝迷彩夹克 儿童开衫外套1-5岁男童装15075");
    }

    @Test
    public void testGetOriginalPrice() throws Exception {
        assertThat(parser.getOriginalPrice()).isEqualTo(169.0);
    }

    @Test
    public void testGetPrice() throws Exception {
        assertThat(parser.getPrice()).isEqualTo(168.85);
    }

    @Test
    public void testGetPriceHistory() throws Exception {
        Map<String, Double> priceHistory = parser.getPriceHistory();
        assertThat(priceHistory).hasSize(12);
        assertThat(priceHistory).containsEntry("2014-08-24", 169.0);
        assertThat(priceHistory).containsEntry("2014-09-09", 139.0);
        assertThat(priceHistory).containsEntry("2014-09-12", 169.0);
        assertThat(priceHistory).containsEntry("2014-11-09", 399.0);
        assertThat(priceHistory).containsEntry("2014-11-11", 139.0);
        assertThat(priceHistory).containsEntry("2014-11-12", 169.0);
        assertThat(priceHistory).containsEntry("2014-11-27", 139.0);
        assertThat(priceHistory).containsEntry("2014-12-01", 169.0);
        assertThat(priceHistory).containsEntry("2014-12-12", 139.0);
        assertThat(priceHistory).containsEntry("2014-12-13", 139.0);
        assertThat(priceHistory).containsEntry("2014-12-15", 169.0);
        assertThat(priceHistory).containsEntry("2015-01-02", 169.02);
    }
}
