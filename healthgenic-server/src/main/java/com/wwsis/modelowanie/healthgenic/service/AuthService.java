package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.User;

public interface AuthService {
    String login(String login, String password);
    String register(String login, String email, String password);
}
