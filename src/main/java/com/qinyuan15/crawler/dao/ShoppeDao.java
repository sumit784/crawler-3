package com.qinyuan15.crawler.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Data access object of Shoppe
 * Created by qinyuan on 15-3-1.
 */
public class ShoppeDao {

    public List<Shoppe> getInstances(int branchId) {
        return HibernateUtil.getList(Shoppe.class, "WHERE branchId=" + branchId);
    }

    public void clear(int branchId) {
        HibernateUtil.delete(Shoppe.class, "branchId=" + branchId);
    }

    public void save(List<Shoppe> shoppes) {
        Session session = HibernateUtil.getSession();
        for (Shoppe shoppe : shoppes) {
            session.save(shoppe);
        }
        HibernateUtil.commit(session);
    }
}
