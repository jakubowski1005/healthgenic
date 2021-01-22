//package com.wwsis.modelowanie.healthgenic.security;
//
//import com.wwsis.modelowanie.healthgenic.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//public class UserDetailsImpl extends User implements UserDetails {
//
//    public UserDetailsImpl(User user) {
//        super(user);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return super.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority(role.name()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return super.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return super.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return super.isEnabled();
//    }
//}
