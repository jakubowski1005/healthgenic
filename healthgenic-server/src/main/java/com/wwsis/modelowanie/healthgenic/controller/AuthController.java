package com.wwsis.modelowanie.healthgenic.controller;

import com.wwsis.modelowanie.healthgenic.model.SigninRequest;
import com.wwsis.modelowanie.healthgenic.model.SignupRequest;
import com.wwsis.modelowanie.healthgenic.service.AuthService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthService service;

    @PostMapping("/auth/register")
    public Map<String, String> register(@Valid @RequestBody SignupRequest request) {
        return service.register(request.getLogin(), request.getEmail(), request.getPassword(), request.getRole());
    }

    @PostMapping("/auth/login")
    public Map<String, String> login(@Valid @RequestBody SigninRequest request) {
        return service.login(request.getLogin(), request.getPassword());
    }


}
