package com.book.test;

import com.book.bean.User;
import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @org.junit.jupiter.api.Test
    void login() {
        User user = userDao.login(new User(null, "admin", "admin", "admin@123"));
        System.out.println(user);
    }
}