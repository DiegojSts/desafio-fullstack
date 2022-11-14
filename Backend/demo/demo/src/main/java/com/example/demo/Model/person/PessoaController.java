//package com.example.demo.Model.person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "api/pessoa")
//public class PessoaController {
//    private final PessoaService _pessoaService;
//
//    @Autowired
//    public PessoaController(PessoaService pessoaService){
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
//    public ResponseEntity<Pessoa> adicionarPessoa(@Valid @RequestBody Pessoa pessoa){
//            var response = this._pessoaService.adicionarPessoa(pessoa);
//    return new ResponseEntity<Pessoa>(response, HttpStatus.CREATED);
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
