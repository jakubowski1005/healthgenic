package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
import com.wwsis.modelowanie.healthgenic.model.RelatedUserDto;
import com.wwsis.modelowanie.healthgenic.model.User;
import com.wwsis.modelowanie.healthgenic.security.JwtProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    JwtProvider jwtProvider;

    @Override
    public List<RelatedUserDto> findAllRelatedUsers(String token) {
        var user = findByToken(token);
        List<RelatedUserDto> response = new ArrayList<>();

        user.getRelatedUserIds().forEach(id -> {
            var found = userRepository.findById(id);
            if (found.isEmpty()) return;
            response.add(RelatedUserDto.builder()
                    .id(id)
                    .username(found.get().getUsername())
                    .name(found.get().getUserData().getName())
                    .surname(found.get().getUserData().getSurname())
                    .age(found.get().getUserData().getAge())
                    .build());
            });
        return response;
    }

    @Override
    public User findCurrent(String token) {
        return findByToken(token);
    }

    @Override
    public String deleteAccount(String token) {
        var user = findByToken(token);
        userRepository.deleteById(user.getId());
        return "Account deleted successfully";
    }

    private User findByToken(String token) {
        var username = jwtProvider.getUsernameFromToken(token);
        var optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("Token error"));
    }
}
