package com.qinyuan15.crawler.core.http.proxy;

import com.qinyuan15.crawler.core.http.proxy.ProxyTester;
import org.junit.Test;

/**
 * Created by qinyuan on 15-1-2.
 */
public class ProxyTesterTest {
    @Test
    public void testRun() throws Exception {
        ProxyTester tester = new ProxyTester();
        tester.run();
    }
}
