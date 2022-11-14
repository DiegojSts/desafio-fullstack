package com.example.demo.Model.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
//    Optional<Pessoa> findPersonById(Integer personId);

}
