package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Data access object of Shoppe
 * Created by qinyuan on 15-3-1.
 */
public class ShoppeDao {

    public List<Shoppe> getInstances(int branchId) {
        return HibernateUtil.getList(Shoppe.class, "WHERE branchId=" + branchId);
    }
}
