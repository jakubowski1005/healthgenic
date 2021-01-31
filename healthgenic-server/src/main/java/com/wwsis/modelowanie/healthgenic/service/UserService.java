package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.User;
import com.wwsis.modelowanie.healthgenic.model.UserData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User update(String id, User user);

    ResponseEntity<?> addRelatedUser()
    ResponseEntity<?> updateUserData(UserData data);
    ResponseEntity<?> deleteRelatedUser(String id);
    ResponseEntity<?> deleteAccount();
}
