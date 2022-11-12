package com.example.demo.pessoa;

import com.example.demo.validadores.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
private final PessoaRepository _pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository){
        this._pessoaRepository = pessoaRepository;
    }
    public List<Pessoa> getPessoas() {
//
        return _pessoaRepository.findAll();
    }

    public void adicionarPessoa(Pessoa pessoa) {
        boolean isValid;

        isValid = Utils
                .executeCheck(pessoa.getCpfPessoa(),
                        pessoa.getDataNascimentoPessoa().toString(), "yyyy-MM-dd");
        if(isValid){
            _pessoaRepository.save(pessoa);

        }
    }
}