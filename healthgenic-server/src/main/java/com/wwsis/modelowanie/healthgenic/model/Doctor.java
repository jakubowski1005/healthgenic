package com.wwsis.modelowanie.healthgenic.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor extends User {
    UserData data;
    String specialization;
    Set<Patient> patients;
}
