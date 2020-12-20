package com.wwsis.modelowanie.healthgenic.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measurement {
    String id;
    String ownerId;
    String type; // TODO create an enum
    Double value;
    String unit; // TODO create an enum
}
