package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.util.List;

/**
 * Test BranchDao
 * Created by qinyuan on 15-2-27.
 */
public class BranchDaoTest {
    @Test
    public void testGetInstancesByCategoryId() throws Exception {
        List<Branch> branches = new BranchDao().getInstancesByCategoryId(4);
        System.out.println(branches.size());
        for (Branch branch : branches) {
            System.out.println(branch.getId());
            System.out.println(branch.getName());
        }
    }
}
