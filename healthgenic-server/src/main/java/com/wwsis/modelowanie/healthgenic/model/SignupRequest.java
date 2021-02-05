package com.wwsis.modelowanie.healthgenic.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SignupRequest {
    @NotNull String username;
    @NotNull @Email String email;
    @NotNull String password;
    @NotNull Role role;
    @NotNull String name;
    @NotNull String surname;
}
