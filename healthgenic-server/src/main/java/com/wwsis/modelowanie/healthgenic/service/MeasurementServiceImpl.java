package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.MeasurementTempRepository;
import com.wwsis.modelowanie.healthgenic.model.Measurement;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MeasurementServiceImpl implements MeasurementService {

    MeasurementTempRepository repository;

    @Override
    public List<Measurement> findAll() {
        return repository.findAll();
    }

    @Override
    public Measurement findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Measurement insert(Measurement measurement) {
        return repository.insert(measurement);
    }

    @Override
    public Measurement update(String id, Measurement measurement) {
        return repository.update(id, measurement);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
