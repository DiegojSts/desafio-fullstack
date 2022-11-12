package com.example.demo.pessoa;
import com.example.demo.Errors.Errors;
import com.example.demo.validadores.DataNascimento;
import com.example.demo.validadores.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public void adicionarPessoa(@RequestBody Pessoa pessoa){
        Errors error = new Errors();
        boolean isValid = true;

        if(!ValidaCPF.isCPF(pessoa.getCpfPessoa())) {
            error.cpfError();
            isValid = false;
        };

        if(!!DataNascimento.isDateFuture(pessoa.getDataNascimentoPessoa().toString(), "yyyy-MM-dd")) {
            error.dataNascimentoError();
            isValid = false;
        };
//
        if (isValid){
            this._pessoaService.adicionarPessoa(pessoa);
        }

    }
}
