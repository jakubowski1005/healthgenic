package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.MeasurementRepository;
import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
import com.wwsis.modelowanie.healthgenic.model.Measurement;
import com.wwsis.modelowanie.healthgenic.model.User;
import com.wwsis.modelowanie.healthgenic.security.JwtProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MeasurementServiceImpl implements MeasurementService {

    MeasurementRepository repository;
    UserRepository userRepository;
    JwtProvider jwtProvider;

    @Override
    public List<Measurement> findAll(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return null;
        String id = user.getId();
        return repository.findAll()
                .stream()
                .filter(measurement -> measurement.getOwnerId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Measurement findAllByPatients(String doctorId) {
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
