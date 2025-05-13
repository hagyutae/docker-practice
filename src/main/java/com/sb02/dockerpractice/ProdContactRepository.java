package com.sb02.dockerpractice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("prod")
@RequiredArgsConstructor
public class ProdContactRepository implements ContactRepositoryInterface {

    private final JpaContactRepository jpaContactRepository;

    @Override
    public void saveContact(String name, String phoneNumber) {
        Contact contact = new Contact(name, phoneNumber);
        jpaContactRepository.save(contact);
    }

    @Override
    public List<String> findContactsByName(String name) {
        return jpaContactRepository.findByName(name)
                .stream()
                .map(Contact::getPhoneNumber)
                .toList();
    }
}
