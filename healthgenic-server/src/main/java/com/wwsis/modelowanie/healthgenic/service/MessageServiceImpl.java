package com.wwsis.modelowanie.healthgenic.service;

import com.wwsis.modelowanie.healthgenic.dao.MessageRepository;
import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
import com.wwsis.modelowanie.healthgenic.model.Message;
import com.wwsis.modelowanie.healthgenic.model.User;
import com.wwsis.modelowanie.healthgenic.security.JwtProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageServiceImpl implements MessageService {

    MessageRepository repository;
    UserRepository userRepository;
    JwtProvider jwtProvider;

    @Override
    public List<Message> findAll(String token) {
        var user = findByToken(token);
        return repository.findAll().stream()
                .filter(message -> message.getFrom().equals(user.getId()) ||
                        message.getTo().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Message send(String token, Message message) {
        var user = findByToken(token);
        var toInsert = Message.builder()
                .content(message.getContent())
                .sentAt(message.getSentAt())
                .to(message.getTo())
                .from(user.getUsername())
                .build();

        return repository.insert(toInsert);
    }

    private User findByToken(String token) {
        var username = jwtProvider.getUsernameFromToken(token);
        var optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("Token error"));
    }
}
