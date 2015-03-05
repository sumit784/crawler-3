package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test UserDao
 * Created by qinyuan on 15-3-5.
 */
public class UserDaoTest {
    @Test
    public void testGetInstanceByName() throws Exception {
        UserDao dao = new UserDao();
        User user = dao.getInstanceByName("admin");
        if (user != null) {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println(user.getRole());
        }
    }
}
