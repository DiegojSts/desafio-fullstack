package com.example.demo.pessoa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class PessoaConfig {

    @Bean
    CommandLineRunner commandLineRunner(PessoaRepository repository) {
        return args -> {
            Pessoa diego = new Pessoa(
                    "Diego",
                    "11111111111",
                    LocalDate.of(1997, FEBRUARY, 1)
            );

            Pessoa maria = new Pessoa(
                    "Maria",
                    "11111111111",
                    LocalDate.of(1967, FEBRUARY, 1)
            );

            repository.saveAll(
                    List.of(diego, maria)
            );

        };
    }
}