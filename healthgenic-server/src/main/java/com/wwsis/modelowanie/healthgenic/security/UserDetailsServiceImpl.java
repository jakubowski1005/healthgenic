//package com.wwsis.modelowanie.healthgenic.security;
//
//import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
//import com.wwsis.modelowanie.healthgenic.model.User;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.experimental.FieldDefaults;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@AllArgsConstructor
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional
//    @SneakyThrows
//    public UserDetails loadUserByUsername(String usernameOrEmail) {
//        User user = userRepository.findByUsernameOrEmail(usernameOrEmail).orElseThrow(
//                () -> new UsernameNotFoundException("User with those credentials not found: " + usernameOrEmail));
//        return JwtUserPrincipal.create(user);
//    }
//}