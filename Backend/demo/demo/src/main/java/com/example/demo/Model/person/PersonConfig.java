package com.example.demo.Model.person;
import com.example.demo.Model.contact.Contact;
import com.example.demo.Repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return args -> {


            Contact contato = new Contact(
                    "NomeContato1",
                    "NomeContato1@gmail.com",
                    "71900000000"
            );

            Contact contato2 = new Contact(
                    "NomeContato2",
                    "NomeContato1@gmail.com",
                    "11900000000"
            );

            Person diego = new Person(
                    "Diego",
                    "50715438000",
                    LocalDate.of(1997, FEBRUARY, 1)
            );

            diego.setContacts(List.of(contato, contato2));

            repository.saveAll(List.of(diego));

        };
    }
}