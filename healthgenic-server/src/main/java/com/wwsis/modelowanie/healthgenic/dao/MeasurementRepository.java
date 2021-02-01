package com.wwsis.modelowanie.healthgenic.dao;

import com.wwsis.modelowanie.healthgenic.model.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeasurementRepository extends MongoRepository<Measurement, String> {
}
