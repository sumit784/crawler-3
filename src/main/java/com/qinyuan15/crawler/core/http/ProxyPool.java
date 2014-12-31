package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.Proxy;
import com.qinyuan15.crawler.dao.ProxyDao;

import java.util.List;

/**
 * Pool to store Proxy
 * Created by qinyuan on 14-12-31.
 */
public class ProxyPool {

    public final static int DEFAULT_SIZE = 10;
    private int size = DEFAULT_SIZE;
    private ProxyDao dao = new ProxyDao();
    private List<Proxy> proxies;
    private int pointer = 0;

    public ProxyPool(int size) {
        this.size = size;
        this.load();
    }

    private void load() {
        this.proxies = this.dao.getTop(this.size);
        this.pointer = 0;
    }

    public void updateSpeed(Proxy proxy, int speed) {
        proxy.setSpeed(speed);
        dao.edit(proxy);
    }

    public Proxy next() {
        return proxies.get(pointer++);
    }
}
