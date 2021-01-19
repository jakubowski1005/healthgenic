package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
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

    UserRepository repository;

    @Override
    public String login(String login, String password) {
        return repository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .map(found -> "token")
                .orElse(null);
    }

    @Override
    @SneakyThrows
    public String register(String login, String email, String password) {
        var found = repository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(login) || user.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (found != null) throw new Exception("User with provided credentials exists.");

        repository.insert(User.builder()
                .email(email)
                .username(login)
                .password(password)
                .build());

        return "You can log in to HeathGenic now.";
    }
}
