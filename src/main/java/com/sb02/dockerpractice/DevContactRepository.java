package com.sb02.dockerpractice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;

@Repository
@Profile("dev")
public class DevContactRepository implements ContactRepositoryInterface {

    private final Map<String, List<String>> contacts = new ConcurrentHashMap<>();

    public void saveContact(String name, String phoneNumber) {
        contacts.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNumber);
    }

    public List<String> findContactsByName(String name) {
        return contacts.getOrDefault(name, new ArrayList<>());
    }
}
