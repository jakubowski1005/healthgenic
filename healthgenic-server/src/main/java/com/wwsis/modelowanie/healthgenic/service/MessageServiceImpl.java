package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.MessageTempRepository;
import com.wwsis.modelowanie.healthgenic.model.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageServiceImpl implements MessageService {

    MessageTempRepository repository;

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Message findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Message send(Message message) {
        return repository.insert(message);
    }

    @Override
    public Message update(String id, Message message) {
        return repository.update(id, message);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
