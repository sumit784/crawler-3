package com.qinyuan15.crawler.core.html;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;

import java.lang.reflect.Method;

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

    public boolean isObject(Object obj) {
        return obj instanceof NativeObject;
    }

    public Object parseObject(Object obj, String key) {
        try {
            Method method = obj.getClass().getMethod("get", Object.class);
            return method.invoke(obj, key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object parseArray(Object obj, int index) {
        try {
            Method method = obj.getClass().getMethod("get", int.class);
            return method.invoke(obj, index);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isArray(Object obj) {
        return obj instanceof NativeArray;
    }
}
