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
                    "Teste",
                    "abc@gmail.com",
                    "123456"
            );

            Contact contato2 = new Contact(
                    "SAGSAOGIJSAIOGASGAS",
                    "abc@gmail.com",
                    "12318294012941456"
            );

            Person diego = new Person(
                    "Diego",
                    "11111111111",
                    LocalDate.of(1997, FEBRUARY, 1)
            );

            diego.setContacts(List.of(contato, contato2));

            repository.saveAll(List.of(diego));

        };
    }
}