package com.qinyuan15.crawler.core.html;

import sun.org.mozilla.javascript.internal.Context;
import sun.org.mozilla.javascript.internal.Scriptable;

/**
 * This class simulate the execution of JavaScript
 * Created by qinyuan on 14-12-29.
 */
public class JavaScriptParser {
    private Context context;
    private Scriptable scriptable;

    public JavaScriptParser() {
        this.context = Context.enter();
        this.scriptable = this.context.initStandardObjects();
    }

    /**
     * evaluate the execution of some javascript code, just as the eval() function of JavaScript
     *
     * @param javaScriptString a string of JavaScript code
     * @return result of latest command of javaScriptString
     */
    public Object eval(String javaScriptString) {
        return this.context.evaluateString(scriptable, javaScriptString, null, 0, null);
    }
}
