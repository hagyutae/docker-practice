package com.sb02.dockerpractice;

import java.util.List;

public interface ContactRepositoryInterface {
    void saveContact(String name, String phoneNumber);
    List<String> findContactsByName(String name);
}
