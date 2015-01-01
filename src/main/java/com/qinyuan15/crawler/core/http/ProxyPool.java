package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.HibernateUtil;
import com.qinyuan15.crawler.dao.Proxy;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Pool to store Proxy
 * Created by qinyuan on 14-12-31.
 */
public class ProxyPool {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyPool.class);
    private int size;
    private List<Proxy> proxies;
    private int pointer = 0;

    public ProxyPool(int size, final int reloadInteval) {
        this.size = size;
        this.load();
        new ReloadThread(reloadInteval).start();
    }

    @SuppressWarnings("unchecked")
    private void load() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Proxy ORDER BY speed").
                setFirstResult(0).setMaxResults(size);
        this.proxies = query.list();
        this.pointer = 0;
    }

    public void updateSpeed(Proxy proxy, int speed) {
        proxy.setSpeed(speed);
        Session session = HibernateUtil.getSession();
        session.save(proxy);
        HibernateUtil.commit(session);
    }

    public Proxy next() {
        return proxies.get(pointer++);
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
