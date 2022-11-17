package com.example.demo.Controllers;
import com.example.demo.Model.person.Pessoa;

import com.example.demo.Services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/pessoa")
public class PessoaController {
    private final PessoaService personService;

    @Autowired
    public PessoaController(PessoaService personService){
        this.personService = personService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> getAll(){
       return this.personService.getAll();
    }

    @GetMapping(path = "{personID}")
    public Optional<Pessoa> getPersonById(@PathVariable("personID")Integer personID){
        return this.personService.getPersonById(personID);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/save")
    public ResponseEntity<Pessoa> addPerson(@Valid @RequestBody Pessoa pessoa){
            var response = this.personService.addPerson(pessoa);
    return new ResponseEntity<Pessoa>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "{personID}")
    public void updatePerson(
            @PathVariable("personID") Integer personID,
            @RequestBody Pessoa pessoa)
    {

        this.personService.updatePerson(personID, pessoa);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "delete/{personID}")
    public void deletePersonById(@PathVariable("personID") Integer personID){
        this.personService.deletePersonById(personID);
    }
}
