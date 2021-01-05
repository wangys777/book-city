package com.book.dao.impl;

import com.book.bean.User;
import com.book.dao.BaseDao;
import com.book.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User login(User user) {
        String sql = "select id,username,password,email from users where username = ? and password = ?";
        User u = this.getBean(sql, user.getUsername(), user.getPassword());
        return u;
    }

    @Override
    public boolean checkUserName(String username) {
        String sql = "select id,username,password,email from users where username =?";
        User user = this.getBean(sql, username);
        return user != null;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into users(username,password,email) value(?,?,?)";
        return this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }


}
