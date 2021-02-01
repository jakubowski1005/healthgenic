package com.wwsis.modelowanie.healthgenic.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measurement {
    @Id
    String id;
    String ownerId;
    String type;
    String value;
    String unit;
}
