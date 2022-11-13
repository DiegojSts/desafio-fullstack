package com.example.demo.pessoa;
import com.example.demo.validadores.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "{pessoaId}")
    public Optional<Pessoa> getPessoaById(@PathVariable("pessoaId")Integer pessoaId){
        return this._pessoaService.getPessoaById(pessoaId);
    }

    @PostMapping
    public void adicionarPessoa(@Valid @RequestBody @NotBlank Pessoa pessoa){
            this._pessoaService.adicionarPessoa(pessoa);

    }

    @DeleteMapping(path = "{pessoaId}")
    public void deletePessoa(@PathVariable("pessoaId") Integer pessoaId){
        this._pessoaService.deletePessoaById(pessoaId);
    }
}
