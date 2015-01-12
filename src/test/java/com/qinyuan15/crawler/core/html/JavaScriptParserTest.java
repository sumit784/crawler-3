package com.qinyuan15.crawler.core.html;

import org.junit.Test;
import org.mozilla.javascript.Undefined;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testParseObject() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", "value");
        assertThat(parser.parseObject(map, "key")).isEqualTo("value");
    }

    @Test
    public void testParseArray() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("Hello");
        list.add("World");
        assertThat(parser.parseArray(list, 0)).isEqualTo("Hello");
        assertThat(parser.parseArray(list, 1)).isEqualTo("World");
    }
}
