package com.wwsis.modelowanie.healthgenic.controller;

import com.wwsis.modelowanie.healthgenic.model.Message;
import com.wwsis.modelowanie.healthgenic.service.MessageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageController {

    MessageService service;

    @GetMapping("/messages")
    public List<Message> findAll(@RequestHeader Map<String, String> headers) {
        return service.findAll(headers.get("authorization").substring(7));
    }

    @PostMapping("/messages")
    public Message send(@RequestHeader Map<String, String> headers, @RequestBody Message message) {
        return service.send(headers.get("authorization").substring(7), message);
    }
}
