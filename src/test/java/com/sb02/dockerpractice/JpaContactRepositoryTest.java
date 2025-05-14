package com.sb02.dockerpractice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class JpaContactRepositoryTest extends AbstractContainerBaseTest{

    @Autowired
    private JpaContactRepository jpaContactRepository;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", POSTGRES_CONTAINER::getDriverClassName);
    }

    @AfterAll
    static void tearDown() {
        POSTGRES_CONTAINER.close();
    }

    @Test
    void saveAndFindByName() {
        Contact contact = new Contact("John Doe", "123456789");
        jpaContactRepository.save(contact);

        List<Contact> found = jpaContactRepository.findByName(contact.getName());

        assertEquals(1, found.size());
        assertEquals(found.get(0).getName(), contact.getName());
        assertEquals(found.get(0).getPhoneNumber(), contact.getPhoneNumber());
    }
}
