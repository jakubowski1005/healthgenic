package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.Measurement;

import java.util.List;

public interface MeasurementService {
    List<Measurement> findAllByUserId(String token, String id);
    Measurement insert(String token, Measurement measurement);
}
