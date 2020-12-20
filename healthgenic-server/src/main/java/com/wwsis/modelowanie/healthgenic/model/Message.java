package com.wwsis.modelowanie.healthgenic.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    String id;
    String from;
    String to;
    Date sentAt;
    String subject;
    String content;
}
