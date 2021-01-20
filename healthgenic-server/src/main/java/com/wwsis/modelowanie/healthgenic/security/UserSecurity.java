package com.wwsis.modelowanie.healthgenic.security;

import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
import com.wwsis.modelowanie.healthgenic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {


    @Autowired
    UserRepository userRepository;

    public boolean isUserAuthorized(Authentication authentication, String userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new Exception();
        );

        String current = authentication.getName();
        return current.equals(user.getUsername());
    }

    public boolean isUserAuthorizedToGetUserByUsername(Authentication authentication, String usernameOrEmail) {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail).orElseThrow(
                () -> new Exception();
        );

        String current = authentication.getName();
        return current.equals(user.getUsername());
    }
}

