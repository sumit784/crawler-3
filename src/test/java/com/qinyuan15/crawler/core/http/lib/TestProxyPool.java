package com.qinyuan15.crawler.core.http.lib;

import com.qinyuan15.crawler.core.http.proxy.ProxyPool;
import com.qinyuan15.crawler.dao.Proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * A ProxyPool for testing
 * Created by qinyuan on 15-1-2.
 */
public class TestProxyPool implements ProxyPool {

    private List<Proxy> proxies = new ArrayList<Proxy>();
    private int pointer;

    public TestProxyPool() {
        //proxies.add(createProxy("218.5.74.174", 80));
        proxies.add(createProxy("219.246.65.143", 3128));
        /*
        proxies.add(createProxy("124.42.127.221", 8086));
        proxies.add(createProxy("120.206.78.245", 8123));
        proxies.add(createProxy("218.207.172.236", 80));
        proxies.add(createProxy("113.105.224.87", 80));
        proxies.add(createProxy("36.250.74.87", 80));
        proxies.add(createProxy("117.177.243.7", 80));
        proxies.add(createProxy("121.42.146.187", 80));
        proxies.add(createProxy("183.207.229.137", 9001));
        proxies.add(createProxy("111.161.126.101", 80));
        proxies.add(createProxy("222.87.129.218", 82));
        proxies.add(createProxy("183.211.117.222", 8123));
        proxies.add(createProxy("113.105.224.87", 80));
        proxies.add(createProxy("114.36.162.55", 9064));
        proxies.add(createProxy("111.1.3.38", 8000));
        */
    }

    public int size() {
        return proxies.size();
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

    private Proxy createProxy(String host, int port) {
        Proxy proxy = new Proxy();
        proxy.setHost(host);
        proxy.setPort(port);
        return proxy;
    }

    @Override
    public void updateSpeed(Proxy proxy) {
        // nothing to do
    }

    @Override
    public void updateSpeed(Proxy proxy, int speed) {
        // nothing to do
    }
}
