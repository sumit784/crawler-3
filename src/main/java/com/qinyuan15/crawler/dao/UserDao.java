package com.qinyuan15.crawler.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Dao object of User
 * Created by qinyuan on 15-3-5.
 */
public class UserDao {
    public User getInstance(Integer userId) {
        return HibernateUtil.get(User.class, userId);
    }

    public User getInstanceByName(String username) {
        Session session = HibernateUtil.getSession();
        List list = session.createQuery("FROM User WHERE username=:username")
                .setString("username", username).list();
        User user = list.size() == 0 ? null : (User) list.get(0);
        HibernateUtil.commit(session);
        return user;
    }

    public Integer getIdByName(String username) {
        User user = this.getInstanceByName(username);
        return user == null ? null : user.getId();
    }

    public String getNameById(Integer userId) {
        User user = getInstance(userId);
        return user == null ? null : user.getUsername();
    }
}
