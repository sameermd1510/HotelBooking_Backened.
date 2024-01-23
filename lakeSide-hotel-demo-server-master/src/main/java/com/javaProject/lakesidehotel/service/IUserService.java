package com.javaProject.lakesidehotel.service;

import com.javaProject.lakesidehotel.model.User;

import java.util.List;

/**
 * @author Mohammad Sameer
 */

public interface IUserService {
    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);
}
