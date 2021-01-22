package com.wwsis.modelowanie.healthgenic.security;

import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
import com.wwsis.modelowanie.healthgenic.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserSecurity {

    UserRepository userRepository;

    public boolean isUserAuthorized(Authentication authentication, String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User with this id not found.")
        );
        return authentication.getName().equals(user.getUsername());
    }

    public boolean isUserAuthorizedToGetUserByUsername(Authentication authentication, String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("Username not found.")
        );
        return authentication.getName().equals(user.getUsername());
    }
}

