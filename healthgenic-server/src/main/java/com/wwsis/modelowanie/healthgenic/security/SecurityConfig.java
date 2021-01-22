package com.wwsis.modelowanie.healthgenic.security;


import com.wwsis.modelowanie.healthgenic.model.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EnableWebSecurity
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements AuthenticationManager {

    JwtProvider jwt;
//    UserDetailsService userDetailsService;
//    UnauthorizedResponseAuthenticationEntryPoint unauthorizedHandler;
//    UserSecurity userSecurity;

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter(provider, userDetailsService);
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        if (!jwt.validateToken(token)) {
            return null;
        }

        Claims claims = jwt.getAllClaimsFromToken(token);
        Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) claims.get("role", Set.class)
                .stream()
                .map(role -> new SimpleGrantedAuthority(((Role) role).name()))
                .collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

//    @Override
//    @SneakyThrows
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
//        authenticationManagerBuilder.
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    @SneakyThrows
//    public AuthenticationManager authenticationManagerBean() {
//        return super.authenticationManagerBean();
//    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {

        http
                .cors()
                .and()
                .csrf()
                    .disable()
                .headers()
                    .frameOptions()
                    .disable()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/")// TODO Wybierz  i dodaj sobie jakie + token to nazwa naszej apki
                        .permitAll()
                    .antMatchers("/auth/**")
                        .permitAll()
                .anyRequest()
                .authenticated();

//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
