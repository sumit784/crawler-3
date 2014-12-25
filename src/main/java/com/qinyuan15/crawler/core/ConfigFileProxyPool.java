package com.qinyuan15.crawler.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Read Proxies from configure file
 * Created by qinyuan on 14-12-24.
 */
public class ConfigFileProxyPool implements HttpProxyPool {
    private List<HttpProxy> proxies = new ArrayList<HttpProxy>();
    private int pointer;

    public ConfigFileProxyPool() {
        //proxies.add(new HttpProxy("124.42.127.221", 8086));
        //proxies.add(new HttpProxy("120.206.78.245", 8123));
        //proxies.add(new HttpProxy("218.207.172.236", 80));
        proxies.add(new HttpProxy("113.105.224.87", 80));
        proxies.add(new HttpProxy("36.250.74.87", 80));
        proxies.add(new HttpProxy("117.177.243.7", 80));
        proxies.add(new HttpProxy("121.42.146.187", 80));
        proxies.add(new HttpProxy("183.207.229.137", 9001));
        proxies.add(new HttpProxy("111.161.126.101", 80));
        proxies.add(new HttpProxy("222.87.129.218", 82));
        //proxies.add(new HttpProxy("183.211.117.222", 8123));
        //proxies.add(new HttpProxy("113.105.224.87", 80));
        //proxies.add(new HttpProxy("114.36.162.55", 9064));
        //proxies.add(new HttpProxy("111.1.3.38", 8000));
    }

    @Override
    public List<HttpProxy> getAll() {
        return proxies;
    }

    @Override
    public HttpProxy next() {
        if (proxies.size() == 0) {
            return null;
        }

        if (pointer >= proxies.size()) {
            pointer = 0;
        }
        return proxies.get(pointer++);
    }

    @Override
    public int size() {
        return proxies.size();
    }
}
