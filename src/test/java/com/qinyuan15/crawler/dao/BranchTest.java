package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Branch
 * Created by qinyuan on 15-2-21.
 */
public class BranchTest {
    @Test
    public void test() throws Exception {
        for (int i = 0; i < 50; i++) {
            @SuppressWarnings("unchecked")
            List<Branch> branches = HibernateUtil.getList("Branch");
            for (Branch branch : branches) {
                assertThat(branch).isNotNull();
            }
        }
    }
}
