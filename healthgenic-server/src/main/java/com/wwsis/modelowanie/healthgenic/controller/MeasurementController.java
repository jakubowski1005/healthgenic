package com.wwsis.modelowanie.healthgenic.controller;

import com.wwsis.modelowanie.healthgenic.model.Measurement;
import com.wwsis.modelowanie.healthgenic.service.MeasurementService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MeasurementController {

    MeasurementService service;

    @GetMapping("/measurements")
    public List<Measurement> findAll() {
        return service.findAll();
    }

    @GetMapping("/measurements/{id}")
    public Measurement findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/measurements")
    public Measurement insert(@RequestBody Measurement measurement) {
        return service.insert(measurement);
    }

    @PutMapping("/measurements/{id}")
    public Measurement update(@PathVariable String id, @RequestBody Measurement measurement) {
        return service.update(id, measurement);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
