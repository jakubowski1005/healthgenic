package com.wwsis.modelowanie.healthgenic.dao;

import com.wwsis.modelowanie.healthgenic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserTempRepository {
    public static Map<String, User> db;

    public List<User> findAll() {
        return new ArrayList<>(db.values());
    }

    public User findById(String id) {
        return db.get(id);
    }

    public User insert(User user) {
        db.put(user.getId(), user);
        return user;
    }

    public User update(String id, User user) {
        db.put(id, user);
        return user;
    }

    public void delete(String id) {
        db.remove(id);
    }
}
