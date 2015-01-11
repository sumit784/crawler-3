package com.qinyuan15.crawler.core.http.proxy;

import com.qinyuan15.crawler.dao.HibernateUtil;
import com.qinyuan15.crawler.dao.Proxy;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Pool to store Proxy that get from database
 * Created by qinyuan on 14-12-31.
 */
public class DatabaseProxyPool implements ProxyPool {

    /**
     * default pool size is 10
     */
    public final static int DEFAULT_SIZE = 10;

    /**
     * default reload interval is 1 minute
     */
    public final static int DEFAULT_RELOAD_INTERVAL = 60000;

    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseProxyPool.class);

    private int size = DEFAULT_SIZE;
    private int reloadInterval = DEFAULT_RELOAD_INTERVAL;
    private List<Proxy> proxies;
    private int pointer = 0;

    public void setSize(int size) {
        this.size = size;
    }

    public void setReloadInterval(int reloadInterval) {
        this.reloadInterval = reloadInterval;
    }

    public void init() {
        this.load();
        new ReloadThread(this.reloadInterval).start();
    }

    @SuppressWarnings("unchecked")
    private void load() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Proxy ORDER BY speed asc, id desc").
                setFirstResult(0).setMaxResults(size);
        this.proxies = query.list();
        this.pointer = 0;
    }

    public void updateSpeed(Proxy proxy) {
        Session session = HibernateUtil.getSession();
        session.update(proxy);
        HibernateUtil.commit(session);
    }

    public Proxy next() {
        if (pointer >= proxies.size()) {
            pointer = 0;
        }
        return proxies.get(pointer++);
    }

    @Override
    public int size() {
        return proxies.size();
    }

    private class ReloadThread extends Thread {
        int reloadInterval;

        ReloadThread(final int reloadInterval) {
            this.reloadInterval = reloadInterval;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(this.reloadInterval);
                    load();
                    LOGGER.info("Reload {} Proxies.", size);
                } catch (Exception e) {
                    LOGGER.error("Error in reloading {}", e.getMessage());
                }
            }
        }
    }
}