package com.example.demo.Model.pessoa;

import com.example.demo.Model.contact.Models.Contact;
import com.example.demo.Model.contact.Repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class PessoaConfig {

    @Bean
    CommandLineRunner commandLineRunner(PessoaRepository repository, ContactRepository contactRepository) {
        return args -> {

            Contact contato = new Contact(
                    "Teste",
                    "abc@gmail.com",
                    "123456"
            );
            Contact contato2 = new Contact(
                    "Teste2",
                    "abcdefg@gmail.com",
                    "123456090990909"
            );

            Pessoa diego = new Pessoa(
                    "Diego",
                    "11111111111",
                    LocalDate.of(1997, FEBRUARY, 1)
            );

            diego.setContacts(List.of(contato, contato2));

            repository.saveAll(List.of(diego));

        };
    }
}