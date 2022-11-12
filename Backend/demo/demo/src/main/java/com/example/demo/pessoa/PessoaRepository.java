package com.example.demo.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
//    Optional<Pessoa> findPersonById(Integer personId);

}
