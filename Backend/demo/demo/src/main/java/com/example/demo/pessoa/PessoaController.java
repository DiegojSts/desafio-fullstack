package com.example.demo.pessoa;
import com.example.demo.validadores.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = "api/pessoa")
public class PessoaController {
    private final PessoaService _pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService){
        this._pessoaService = pessoaService;
    }

    @GetMapping
    public List<Pessoa> getPessoas(){
       return this._pessoaService.getPessoas();
    }

    @PostMapping
    public void adicionarPessoa(@Valid @RequestBody @NotBlank Pessoa pessoa){

            this._pessoaService.adicionarPessoa(pessoa);

    }
}
