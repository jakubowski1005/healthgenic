package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.RelatedUserDto;
import com.wwsis.modelowanie.healthgenic.model.User;

import java.util.List;

public interface UserService {
    List<RelatedUserDto> findAllRelatedUsers(String token);
    User findCurrent(String token);
    String deleteAccount(String token);
}
