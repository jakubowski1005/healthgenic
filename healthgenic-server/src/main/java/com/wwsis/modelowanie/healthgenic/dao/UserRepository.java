package com.wwsis.modelowanie.healthgenic.dao;

import com.wwsis.modelowanie.healthgenic.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    default Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        Optional<User> foundByUsername = findByUsername(usernameOrEmail);
        if (foundByUsername.isPresent()) {
            return foundByUsername;
        }
        return findByEmail(usernameOrEmail);
    }

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
