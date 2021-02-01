package com.wwsis.modelowanie.healthgenic.controller;

import com.wwsis.modelowanie.healthgenic.model.Measurement;
import com.wwsis.modelowanie.healthgenic.service.MeasurementService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MeasurementController {

    MeasurementService service;

    @GetMapping("/measurements/{id}")
    public List<Measurement> findAllByUserId(@RequestHeader Map<String, String> headers, @PathVariable String id) {
        return service.findAllByUserId(headers.get("authorization").substring(7), id);
    }

    @GetMapping("/measurements")
    public List<Measurement> findAllByOwner(@RequestHeader Map<String, String> headers) {
        return service.findAllByUserId(headers.get("authorization").substring(7), "null");
    }

    @PostMapping("/measurements")
    public Measurement insert(@RequestHeader Map<String, String> headers, @RequestBody Measurement measurement) {
        return service.insert(headers.get("authorization").substring(7), measurement);
    }
}
