package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.User;

public interface AuthService {
    User login(String login, String password);
    User register(String login, String email, String password);
}
