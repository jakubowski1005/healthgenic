package com.wwsis.modelowanie.healthgenic.dao;

import com.wwsis.modelowanie.healthgenic.model.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataUtils {

    PasswordEncoder encoder;
    UserRepository userRepository;
    MeasurementRepository measurementRepository;
    MessageRepository messageRepository;

    @SneakyThrows
    public void populateInitialData() {

        if (!userRepository.findByUsername("doctorwho").isEmpty()) {
            throw new Exception("Already populated!");
        }

        var doc1 = User.builder()
                .username("doctorwho")
                .email("doctor@who.com")
                .password(encoder.encode("Password123"))
                .roles(Set.of(Role.DOCTOR))
                .userData(new UserData("Doctor", "Who", 56))
                .build();

        var doc1db = userRepository.insert(doc1);

        var doc2 = User.builder()
                .username("doctordre")
                .email("doctor@dre.com")
                .password(encoder.encode("Password123"))
                .roles(Set.of(Role.DOCTOR))
                .userData(new UserData("Doctor", "Dre", 24))
                .build();

        var doc2db = userRepository.insert(doc2);

        var pat1 = User.builder()
                .username("patientzero")
                .email("patient@zero.com")
                .password(encoder.encode("Password123"))
                .roles(Set.of(Role.PATIENT))
                .userData(new UserData("Jane", "Doe", 99))
                .relatedUserIds(Set.of(doc1db.getId()))
                .build();

        var pat2 = User.builder()
                .username("testuser")
                .email("test@user.com")
                .password(encoder.encode("Password123"))
                .roles(Set.of(Role.PATIENT))
                .userData(new UserData("Jan", "Kowalski", 44))
                .relatedUserIds(Set.of(doc2db.getId()))
                .build();

        var pat3 = User.builder()
                .username("helloworld")
                .email("hello@world.com")
                .password(encoder.encode("Password123"))
                .roles(Set.of(Role.PATIENT))
                .userData(new UserData("Hans", "Schwarz", 32))
                .relatedUserIds(Set.of(doc1db.getId(), doc2db.getId()))
                .build();

        var pat1db = userRepository.insert(pat1);
        var pat2db = userRepository.insert(pat2);
        var pat3db = userRepository.insert(pat3);

        doc1.setRelatedUserIds(Set.of(pat1db.getId(), pat3db.getId()));
        doc2.setRelatedUserIds(Set.of(pat2db.getId(), pat3db.getId()));
        doc1db = userRepository.save(doc1);
        doc2db = userRepository.save(doc2);

        populateMeasurements(pat1db.getId());
        populateMeasurements(pat2db.getId());
        populateMeasurements(pat3db.getId());

        populateMessages(doc2db.getId(), pat3db.getId());

    }

    private void populateMessages(String from, String to) {
        String msg1 = "Good morning Dr. Dre.";
        String msg2 = "Good morning. How can I help you?";
        String msg3 = "I'd like to make an appointment for tomorrow.";
        String msg4 = "I will be available in my office at 9AM. How does it sound?";
        String msg5 = "Great. See you tomorrow.";
        String msg6 = "Till tomorrow";

        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder().from(from).to(to).content(msg1).sentAt(new Date()).build());
        messages.add(Message.builder().from(from).to(to).content(msg2).sentAt(new Date()).build());
        messages.add(Message.builder().from(from).to(to).content(msg3).sentAt(new Date()).build());
        messages.add(Message.builder().from(from).to(to).content(msg4).sentAt(new Date()).build());
        messages.add(Message.builder().from(from).to(to).content(msg5).sentAt(new Date()).build());
        messages.add(Message.builder().from(from).to(to).content(msg6).sentAt(new Date()).build());
        messageRepository.insert(messages);
    }

    private void populateMeasurements(String userId) {
        Random random = new Random();
        List<Measurement> measurements = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            var msr1 = Measurement.builder()
                    .unit("mg/dL")
                    .type("Blood Sugar Level")
                    .value(String.valueOf(70 + (99 - 70) * random.nextDouble()))
                    .ownerId(userId)
                    .createdAt(new Date())
                    .build();

            var msr2 = Measurement.builder()
                    .unit("mm Hg")
                    .type("Blood Pressure")
                    .value(String.valueOf(110 + (150 - 110) * random.nextDouble()) + '/' + (75 + (99 - 75) * random.nextDouble()))
                    .ownerId(userId)
                    .createdAt(new Date())
                    .build();

            var msr3 = Measurement.builder()
                    .unit("beats per minute")
                    .type("Heart Rate")
                    .value(String.valueOf(40 + (120 - 40) * random.nextDouble()))
                    .ownerId(userId)
                    .createdAt(new Date())
                    .build();

            measurements.add(msr1);
            measurements.add(msr2);
            measurements.add(msr3);
        }
        measurementRepository.insert(measurements);
    }
}
