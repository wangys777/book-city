package com.book.service;

import com.book.bean.User;

public interface UserService {

    public User login(User user);

    public boolean checkUserName(String username);

    public int saveUser(User user);
}
