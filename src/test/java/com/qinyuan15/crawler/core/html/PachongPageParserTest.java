package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.core.http.HttpProxy;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import sun.org.mozilla.javascript.internal.Context;
import sun.org.mozilla.javascript.internal.Scriptable;

import java.io.File;
import java.util.List;

/**
 * Created by qinyuan on 14-12-29.
 */
public class PachongPageParserTest {
    private PachongPageParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new PachongPageParser();
        String html = FileUtils.readFileToString(new File("src/test/resources/pachong.html"));
        parser.setHTML(html);
    }

    @Test
    public void testGetProxies() throws Exception {
        //List<HttpProxy> proxies = parser.getProxies();
        Context ctx = Context.enter();
        Scriptable scriptable = ctx.initStandardObjects();
        String jsStr = "100*12/10";
        System.out.println(ctx.evaluateString(scriptable, jsStr, null, 0, null));
    }

    @Test
    public void testGetSubLinks() throws Exception {

    }
}
