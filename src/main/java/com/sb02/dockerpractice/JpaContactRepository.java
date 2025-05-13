package com.sb02.dockerpractice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByName(String name);
}
