package com.wwsis.modelowanie.healthgenic.controller;

import com.wwsis.modelowanie.healthgenic.model.Message;
import com.wwsis.modelowanie.healthgenic.service.MessageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageController {

    MessageService service;

    @GetMapping("/messages")
    public List<Message> findAll() {
        return service.findAll();
    }

    @GetMapping("/messages/{id}")
    public Message findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/messages")
    public Message send(@RequestBody Message message) {
        return service.send(message);
    }

    @GetMapping("/messages/{id}")
    public Message update(@PathVariable String id, @RequestBody Message message) {
        return service.update(id, message);
    }

    @GetMapping("/messages/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
