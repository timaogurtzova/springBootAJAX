package com.hellocat.springBootAJAX.service;

import com.hellocat.springBootAJAX.domen.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(Long id);

    User findUserByName(String name);

    boolean saveUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);
}
