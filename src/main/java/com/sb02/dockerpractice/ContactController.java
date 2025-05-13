package com.sb02.dockerpractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public Map<String, String> addContact(@RequestBody Map<String, String> contact) {
        log.info("Received contact add request");
        String name = contact.get("name");
        String phoneNumber = contact.get("phoneNumber");
        contactService.addContact(name, phoneNumber);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }

    @GetMapping("/{name}")
    public Map<String, List<String>> getContactsByName(@PathVariable String name) {
        log.info("Received request to get contacts by name: {}", name);
        List<String> phoneNumbers = contactService.getContactsByName(name);

        Map<String, List<String>> response = new HashMap<>();
        response.put(name, phoneNumbers);
        return response;
    }
}
