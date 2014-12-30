package com.qinyuan15.crawler.core.html;

import sun.org.mozilla.javascript.internal.Context;
import sun.org.mozilla.javascript.internal.Scriptable;

/**
 * Created by qinyuan on 14-12-29.
 */
public class JavaScriptParser {
    private Context context;
    private Scriptable scriptable;

    public JavaScriptParser() {
        this.context = Context.enter();
        this.scriptable = this.context.initStandardObjects();
    }

    public Object eval(String javaScriptString) {
        return this.context.evaluateString(scriptable, javaScriptString, null, 0, null);
    }
}
