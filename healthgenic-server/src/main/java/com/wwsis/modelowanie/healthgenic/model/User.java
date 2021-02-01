package com.wwsis.modelowanie.healthgenic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id String id;
    @Indexed(unique = true) String username;
    @JsonIgnore String password;
    @Indexed(unique = true) String email;
    Set<Role> roles;
    Set<String> relatedUserIds;
    UserData userData;
    @JsonIgnore boolean enabled;
    @JsonIgnore boolean accountNonExpired;
    @JsonIgnore boolean credentialsNonExpired;
    @JsonIgnore boolean accountNonLocked;
}
