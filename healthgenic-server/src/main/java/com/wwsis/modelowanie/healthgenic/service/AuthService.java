package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.Role;

import javax.validation.constraints.NotNull;
import java.util.Map;

public interface AuthService {
    Map<String, String> login(String login, String password);
    Map<String, String> register(String login, String email, String password, @NotNull Role role);
}
