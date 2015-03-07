package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.util.List;

/**
 * Test BranchDao
 * Created by qinyuan on 15-2-27.
 */
public class BranchDaoTest {
    private BranchDao dao = new BranchDao();

    @Test
    public void testGetInstancesByCategoryId() throws Exception {
        List<Branch> branches = dao.getInstancesByCategoryId(4);
        System.out.println(branches.size());
        for (Branch branch : branches) {
            System.out.println(branch.getId());
            System.out.println(branch.getName());
        }
    }

    @Test
    public void testIsUsed() throws Exception {
        System.out.println(dao.isUsed(6));
        System.out.println(dao.isUsed(7));
        System.out.println(dao.isUsed(8));
    }
}
