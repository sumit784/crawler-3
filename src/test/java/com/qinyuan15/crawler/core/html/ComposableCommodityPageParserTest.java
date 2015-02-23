package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import com.qinyuan15.crawler.core.http.HttpClientWrapper;
import com.qinyuan15.crawler.lib.TestFileUtils;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

/**
 * Test CompposableCommodityPageParser
 * Created by qinyuan on 15-1-11.
 */
public class ComposableCommodityPageParserTest {

    private ComposableCommodityPageParser parser;

    @Before
    public void setUp() throws Exception {
        parser = mockComposableCommodityPageParser();
    }

    @Test
    public void test() throws Exception {
        parser.setHTML(TestFileUtils.read("etao.html"));
        parser.setWebUrl("http://s.etao.com/detail/40780735321.html?tbpm=20150110");

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

    public static ComposableCommodityPageParser mockComposableCommodityPageParser() {
        CommodityPageParser etaoCommodityPageParser = new EtaoCommodityPageParser(new HttpClientPool());
        ComposableCommodityPageParser composableCommodityPageParser =
                new ComposableCommodityPageParser();

        Map<String, CommodityPageParser> parserMap = new HashMap<String, CommodityPageParser>();
        parserMap.put("etao", etaoCommodityPageParser);
        composableCommodityPageParser.setParsers(parserMap);
        return composableCommodityPageParser;
    }
}
