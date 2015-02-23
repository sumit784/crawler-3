package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test EtaoCommodityPageParser
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParserTest {
    private EtaoCommodityPageParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new EtaoCommodityPageParser(new HttpClientPool());
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
        Map<Date, Double> priceHistory = parser.getPriceHistory();
        assertThat(priceHistory).hasSize(12);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-08-24"), 169.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-09-09"), 139.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-09-12"), 169.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-11-09"), 399.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-11-11"), 139.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-11-12"), 169.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-11-27"), 139.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-12-01"), 169.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-12-12"), 139.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-12-13"), 139.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2014-12-15"), 169.0);
        assertThat(priceHistory).containsEntry(DateUtils.newDate("2015-01-02"), 169.02);
    }

    @Test
    public void testGetPriceHistory2() throws Exception {
        parser.setHTML(TestFileUtils.read("etao2.html"));
        Map<Date, Double> priceHistory = parser.getPriceHistory();
        System.out.println(priceHistory);
    }

    @Test
    public void testGetImageUrls() throws Exception {
        List<String> imageUrls = parser.getImageUrls();
        assertThat(imageUrls).containsExactly(
                "http://img05.taobaocdn.com/bao/uploaded/i1/TB1aUBgGXXXXXX7XVXXXXXXXXXX_!!0-item_pic.jpg",
                "http://img04.taobaocdn.com/bao/uploaded/i4/479940663/TB2mjoCaXXXXXaVXXXXXXXXXXXX-479940663.jpg",
                "http://img02.taobaocdn.com/bao/uploaded/i2/479940663/TB2ps3zaXXXXXcOXXXXXXXXXXXX-479940663.jpg",
                "http://img06.taobaocdn.com/bao/uploaded/i2/479940663/TB2.uTcaFXXXXaBXXXXXXXXXXXX_!!479940663.jpg",
                "http://img05.taobaocdn.com/bao/uploaded/i4/479940663/TB2gegzaXXXXXbCXpXXXXXXXXXX-479940663.jpg"
        );
    }

    @Test
    public void testGetDetailImageUrls() throws Exception {
        List<String> imageUrls = parser.getDetailImagesUrls();
        assertThat(imageUrls).hasSize(21);
    }

    @Test
    public void testParseDetailImageUrls() throws Exception {
        Method method = Whitebox.getMethod(parser.getClass(), "parseDetailImageUrls", String.class);
        @SuppressWarnings("unchecked")
        List<String> urls = (List) method.invoke(parser, TestFileUtils.read("etao-detail.html"));
        for (String url : urls) {
            assertThat(url).contains(".jpg").contains("http://")
                    .contains("taobaocdn.com/imgextra");
        }
    }
}
