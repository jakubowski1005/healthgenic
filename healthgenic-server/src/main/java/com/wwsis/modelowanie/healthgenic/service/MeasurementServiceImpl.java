package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.MeasurementRepository;
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

    MeasurementRepository repository;

    @Override
    public List<Measurement> findAll() {
        return repository.findAll();
    }

    @Override
    public Measurement findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Measurement insert(Measurement measurement) {
        return repository.insert(measurement);
    }

    @Override
    public Measurement update(String id, Measurement measurement) {
        measurement.setId(id);
        return repository.save(measurement);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
