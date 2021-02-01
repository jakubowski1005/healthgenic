package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
import com.wwsis.modelowanie.healthgenic.model.Role;
import com.wwsis.modelowanie.healthgenic.model.User;
import com.wwsis.modelowanie.healthgenic.model.UserData;
import com.wwsis.modelowanie.healthgenic.security.JwtProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository repository;
    PasswordEncoder encoder;
    JwtProvider jwt;

    @Override
    public Map<String, String> login(String email, String password) {
        return repository.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(email) && encoder.matches(password, user.getPassword()))
                .findFirst()
                .map(found -> Map.of("token", jwt.generateToken(found)))
                .orElse(Map.of("message", "Wrong credentials."));
    }

    @Override
    @SneakyThrows
    public Map<String, String> register(String login, String email, String password, Role role,
                                        String name, String surname) {
        var found = repository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(login) || user.getEmail().equals(email))
                .findFirst();

        if (found.isPresent()) throw new CredentialException("User with provided credentials exists.");

        repository.insert(User.builder()
                .email(email)
                .username(login)
                .password(encoder.encode(password))
                .roles(Set.of(role))
                .userData(UserData.builder().name(name).surname(surname).build())
                .relatedUserIds(new HashSet<>())
                .build());

        return Map.of("message", "You can log in to HeathGenic now.");
    }
}
