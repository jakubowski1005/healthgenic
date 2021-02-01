package com.wwsis.modelowanie.healthgenic.controller;

import com.wwsis.modelowanie.healthgenic.dao.DataUtils;
import com.wwsis.modelowanie.healthgenic.model.RelatedUserDto;
import com.wwsis.modelowanie.healthgenic.model.User;
import com.wwsis.modelowanie.healthgenic.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserService service;
    DataUtils dataUtils;

    @GetMapping("/related")
    public List<RelatedUserDto> findAllRelatedUsers(@RequestHeader Map<String, String> headers) {
        return service.findAllRelatedUsers(headers.get("authorization").substring(7));
    }

    @GetMapping("/current")
    public User findCurrent(@RequestHeader Map<String, String> headers) {
        return service.findCurrent(headers.get("authorization").substring(7));
    }

    @GetMapping("/populate")
    public void populateData() {
        dataUtils.populateInitialData();
    }

    @DeleteMapping("/users")
    public void delete(@RequestHeader Map<String, String> headers) {
        service.deleteAccount(headers.get("authorization").substring(7));
    }
}
