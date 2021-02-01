package com.wwsis.modelowanie.healthgenic.dao;

import com.wwsis.modelowanie.healthgenic.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
//    public static Map<String, Message> db;
//
//    public List<Message> findAll() {
//        return new ArrayList<>(db.values());
//    }
//
//    public Message findById(String id) {
//        return db.get(id);
//    }
//
//    public Message insert(Message message) {
//        db.put(message.getId(), message);
//        return message;
//    }
//
//    public Message update(String id, Message message) {
//        db.put(id, message);
//        return message;
//    }
//
//    public void delete(String id) {
//        db.remove(id);
//    }
}
