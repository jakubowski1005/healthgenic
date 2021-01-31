package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.Measurement;

import java.util.List;

public interface MeasurementService {
    List<Measurement> findAll(String username);
    Measurement findById(String id);
    Measurement insert(Measurement measurement);
    Measurement update(String id, Measurement measurement);
    void delete(String id);
}
