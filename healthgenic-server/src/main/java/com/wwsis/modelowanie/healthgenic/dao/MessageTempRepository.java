package com.wwsis.modelowanie.healthgenic.dao;

import com.wwsis.modelowanie.healthgenic.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageTempRepository {
    public static Map<String, Message> db;

    public List<Message> findAll() {
        return new ArrayList<>(db.values());
    }

    public Message findById(String id) {
        return db.get(id);
    }

    public Message insert(Message message) {
        db.put(message.getId(), message);
        return message;
    }

    public Message update(String id, Message message) {
        db.put(id, message);
        return message;
    }

    public void delete(String id) {
        db.remove(id);
    }
}
