package com.qinyuan15.crawler.core;

import com.qinyuan15.crawler.core.http.HttpProxyPool;
import com.qinyuan15.crawler.dao.Proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Read Proxies from configure file
 * Created by qinyuan on 14-12-24.
 */
public class ConfigFileProxyPool implements HttpProxyPool {
    private List<Proxy> proxies = new ArrayList<Proxy>();
    private int pointer;

    public ConfigFileProxyPool() {
        //proxies.add(createProxy("124.42.127.221", 8086));
        //proxies.add(createProxy("120.206.78.245", 8123));
        //proxies.add(createProxy("218.207.172.236", 80));
        proxies.add(createProxy("113.105.224.87", 80));
        proxies.add(createProxy("36.250.74.87", 80));
        proxies.add(createProxy("117.177.243.7", 80));
        proxies.add(createProxy("121.42.146.187", 80));
        proxies.add(createProxy("183.207.229.137", 9001));
        proxies.add(createProxy("111.161.126.101", 80));
        proxies.add(createProxy("222.87.129.218", 82));
        //proxies.add(createProxy("183.211.117.222", 8123));
        //proxies.add(createProxy("113.105.224.87", 80));
        //proxies.add(createProxy("114.36.162.55", 9064));
        //proxies.add(createProxy("111.1.3.38", 8000));
    }

    private Proxy createProxy(String host, int port) {
        Proxy proxy = new Proxy();
        proxy.setHost(host);
        proxy.setPort(port);
        return proxy;
    }

    @Override
    public List<Proxy> getAll() {
        return proxies;
    }

    @Override
    public Proxy next() {
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
