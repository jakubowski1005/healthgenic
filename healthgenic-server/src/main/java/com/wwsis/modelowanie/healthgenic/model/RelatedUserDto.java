package com.wwsis.modelowanie.healthgenic.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RelatedUserDto {
    String id;
    String username;
    String name;
    String surname;
    Integer age;
}
