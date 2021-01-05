package com.book.dao;

import com.book.bean.User;

public interface UserDao {

    public User login(User user);

    public boolean checkUserName(String username);

    public int saveUser(User user);
}
