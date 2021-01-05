package com.book.service.Impl;

import com.book.bean.User;
import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public boolean checkUserName(String username) {
        return userDao.checkUserName(username);
    }

    @Override
    public int saveUser(User user) {
        return userDao.saveUser(user);
    }
}
