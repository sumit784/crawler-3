package com.qinyuan15.crawler.core.html;

import org.junit.Test;
import sun.org.mozilla.javascript.internal.Undefined;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test case of JavaScriptParser
 * Created by qinyuan on 14-12-29.
 */
public class JavaScriptParserTest {

    private JavaScriptParser parser = new JavaScriptParser();

    @Test
    public void testEval() throws Exception {
        Object result = parser.eval("var a = 1; a");
        assertThat(result).isExactlyInstanceOf(Double.class);
        assertThat(result).isEqualTo(1.0);

        result = parser.eval("var b = 'HelloWorld'; b");
        assertThat(result).isExactlyInstanceOf(String.class);
        assertThat(result).isEqualTo("HelloWorld");

        result = parser.eval("var b = 'HelloWorld';");
        assertThat(result).isExactlyInstanceOf(Undefined.class);
        assertThat(result).isSameAs(Undefined.instance);
    }
}
