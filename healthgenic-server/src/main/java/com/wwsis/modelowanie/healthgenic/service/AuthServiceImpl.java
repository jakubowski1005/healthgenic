package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.UserTempRepository;
import com.wwsis.modelowanie.healthgenic.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserTempRepository repository;

    @Override
    public User login(String login, String password) {
        return repository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    @SneakyThrows
    public User register(String login, String email, String password) {
        var found = repository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(login) || user.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (found != null) throw new Exception("Err");

        User created = User.builder()
                .email(email)
                .username(login)
                .password(password)
                .build();

        return repository.insert(created);
    }
}
