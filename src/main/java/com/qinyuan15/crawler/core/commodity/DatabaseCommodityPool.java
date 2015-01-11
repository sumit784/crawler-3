package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Pool to store Commodity that get from database
 * Created by qinyuan on 15-1-9.
 */
public class DatabaseCommodityPool implements CommodityPool {

    private final static int PAGE_SIZE = 100;
    private int pointer = 0;
    private List<Commodity> commodities;

    @SuppressWarnings("unchecked")
    @Override
    public Commodity next() {
        Session session = HibernateUtil.getSession();

        long commodityCount = this.size();

        if (pointer >= commodityCount) {
            return null;
        }

        if (pointer % PAGE_SIZE == 0) {
            Query query = session.createQuery("FROM Commodity ORDER BY id")
                    .setFirstResult(pointer).setMaxResults(PAGE_SIZE);
            this.commodities = query.list();
        }

        Commodity commodity = this.commodities.get(pointer);
        pointer++;
        return commodity;
    }

    public long size() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM Commodity");
        long commodityCount = (Long) query.list().get(0);
        session.close();
        return commodityCount;
    }

    @Override
    public void reset() {
        this.pointer = 0;
    }
}
