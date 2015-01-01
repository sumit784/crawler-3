package com.qinyuan15.crawler.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Get branches from database or by other way
 * Created by qinyuan on 14-12-27.
 */
public class BranchFactory /*extends AbstractDao<Branch>*/ {

    private Branch mockInstance(int id, String name) {
        Branch branch = new Branch();
        branch.setId(id);
        branch.setName(name);
        branch.setLogoUrl("resources/images/438179439.png");
        return branch;
    }

    public Branch getInstance(int id) {
        for (Branch branch : getInstances()) {
            if (branch.getId() == id) {
                return branch;
            }
        }
        return null;
    }

    public List<Branch> getInstances() {
        List<Branch> list = new ArrayList<Branch>();
        list.add(mockInstance(1, "美特斯邦威"));
        list.add(mockInstance(2, "欧莱雅"));
        list.add(mockInstance(3, "路易威登"));
        list.add(mockInstance(4, "真维斯"));
        list.add(mockInstance(5, "XXXX"));
        list.add(mockInstance(6, "YYYY"));
        list.add(mockInstance(7, "ZZZZ"));
        return list;
    }
}
