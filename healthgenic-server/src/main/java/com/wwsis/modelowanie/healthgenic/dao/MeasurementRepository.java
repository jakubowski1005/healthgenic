package com.wwsis.modelowanie.healthgenic.dao;

import com.wwsis.modelowanie.healthgenic.model.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> {

//    public static Map<String, Measurement> db;
//
//    public List<Measurement> findAll() {
//        return new ArrayList<>(db.values());
//    }
//
//    public Measurement findById(String id) {
//        return db.get(id);
//    }
//
//    public Measurement insert(Measurement measurement) {
//        db.put(measurement.getId(), measurement);
//        return measurement;
//    }
//
//    public Measurement update(String id, Measurement measurement) {
//        db.put(id, measurement);
//        return measurement;
//    }
//
//    public void delete(String id) {
//        db.remove(id);
//    }
}
