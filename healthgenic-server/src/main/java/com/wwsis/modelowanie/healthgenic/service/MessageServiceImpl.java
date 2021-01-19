package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.MessageRepository;
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

    MessageRepository repository;

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Message findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Message send(Message message) {
        return repository.insert(message);
    }

    @Override
    public Message update(String id, Message message) {
        message.setId(id);
        return repository.save(message);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
