package com.sb02.dockerpractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void addContact(String name, String phoneNumber) {
        log.info("Adding contact: name={}, phoneNumber={}", name, phoneNumber);
        contactRepository.saveContact(name, phoneNumber);
        log.info("Contact added successfully");
    }

    public List<String> getContactsByName(String name) {
        log.info("Getting contacts for name: {}", name);
        List<String> result = contactRepository.findContactsByName(name);
        log.info("Found {} contacts for name: {}", result.size(), name);
        return result;
    }
}