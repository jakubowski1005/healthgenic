package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    Message findById(String id);
    Message send(Message message);
    Message update(String id, Message message);
    void delete(String id);
}
