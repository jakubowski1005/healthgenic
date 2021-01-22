//package com.wwsis.modelowanie.healthgenic.security;
//import com.wwsis.modelowanie.healthgenic.model.User;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Data
//@Builder
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class JwtUserPrincipal implements UserDetails {
//
//    String id;
//    String username;
//    String email;
//    String password;
//    Collection<? extends GrantedAuthority> authorities;
//
//    public static JwtUserPrincipal create(User user) {
//        return JwtUserPrincipal.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .authorities(user.getRoles()
//                        .stream()
//                        .map(role -> new SimpleGrantedAuthority(role.name()))
//                        .collect(Collectors.toList()))
//                .build();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
