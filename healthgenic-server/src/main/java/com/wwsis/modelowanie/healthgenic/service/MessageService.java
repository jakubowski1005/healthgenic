package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll(String token);
    Message send(String token, Message message);
}
