package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final ConcurrentHashMap<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    public UserRepository() {
        save(new User(1L, "Alice", "alice@example.com"));
        save(new User(2L, "Bob", "bob@example.com"));
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(sequence.getAndIncrement());
        }
        store.put(user.getId(), user);
        return user;
    }
}
