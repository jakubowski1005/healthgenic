package com.wwsis.modelowanie.healthgenic.security;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wwsis.modelowanie.healthgenic.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class JwtUserPrincipal implements UserDetails {

    private static final long serialVersionUID = 2353642365874363728L;

    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

    public static JwtUserPrincipal create(User user) {
        return new JwtUserPrincipal(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
