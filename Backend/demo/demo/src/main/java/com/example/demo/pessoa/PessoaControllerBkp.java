//package com.example.demo.pessoa;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "api/pessoa")
//public class PessoaControllerBkp {
//    private final PessoaService _pessoaService;
//
//    @Autowired
//    public PessoaControllerBkp(PessoaService pessoaService){
//        this._pessoaService = pessoaService;
//    }
//
//    @GetMapping
//    public List<Pessoa> getPessoas(){
//       return this._pessoaService.getPessoas();
//    }
//
//    @GetMapping(path = "{pessoaId}")
//    public Optional<Pessoa> getPessoaById(@PathVariable("pessoaId")Integer pessoaId){
//        return this._pessoaService.getPessoaById(pessoaId);
//    }
//
//    @PostMapping
//    public void adicionarPessoa(@Valid @RequestBody @NotBlank Pessoa pessoa){
//            this._pessoaService.adicionarPessoa(pessoa);
//
//    }
//
//    @PutMapping(path = "{pessoaId}")
//    public void updatePessoa(
//            @PathVariable("pessoaId") Integer pessoaId,
//            @RequestParam(required = false) String nomePessoa,
//            @RequestParam(required = false) String cpfPessoa,
//            @RequestParam(required = false) String dataNascimentoPessoa){
//
//        this._pessoaService.updatePerson(pessoaId, nomePessoa, cpfPessoa, dataNascimentoPessoa);
//    }
//
//    @DeleteMapping(path = "{pessoaId}")
//    public void deletePessoa(@PathVariable("pessoaId") Integer pessoaId){
//        this._pessoaService.deletePessoaById(pessoaId);
//    }
//}
