package com.wwsis.modelowanie.healthgenic.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserData {
    String name;
    String surname;
    Integer age;
}
