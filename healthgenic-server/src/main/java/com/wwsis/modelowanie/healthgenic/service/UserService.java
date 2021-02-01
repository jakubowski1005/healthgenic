package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User update(String id, User user);
    void delete(String id);
}
