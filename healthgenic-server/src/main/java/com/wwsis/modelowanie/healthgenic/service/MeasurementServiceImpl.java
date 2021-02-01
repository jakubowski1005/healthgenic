package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.MeasurementRepository;
import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
import com.wwsis.modelowanie.healthgenic.model.Measurement;
import com.wwsis.modelowanie.healthgenic.model.Role;
import com.wwsis.modelowanie.healthgenic.model.User;
import com.wwsis.modelowanie.healthgenic.security.JwtProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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
    public List<Measurement> findAllByUserId(String token, String id) {
        var user = findByToken(token);
        if (user.getRoles().contains(Role.PATIENT)) {
            return repository.findAll()
                    .stream()
                    .filter(measurement -> measurement.getOwnerId().equals(user.getId()))
                    .collect(Collectors.toList());
        }
        if (user.getRoles().contains(Role.DOCTOR) && user.getRelatedUserIds().contains(id)) {
            return repository.findAll()
                    .stream()
                    .filter(measurement -> measurement.getOwnerId().equals(id))
                    .collect(Collectors.toList());
        }
        throw new ResourceAccessException("User must be a doctor or a patient!");
    }

    @Override
    @SneakyThrows
    public Measurement insert(String token, Measurement measurement) {
        var user = findByToken(token);
        if(!user.getRoles().contains(Role.PATIENT)) {
            throw new IllegalAccessException("Only patient can add a measurement!");
        }
        return repository.insert(Measurement.builder()
                .type(measurement.getType())
                .unit(measurement.getUnit())
                .value(measurement.getValue())
                .ownerId(user.getId())
                .build());
    }

    private User findByToken(String token) {
        var username = jwtProvider.getUsernameFromToken(token);
        var optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("Token error"));
    }
}
