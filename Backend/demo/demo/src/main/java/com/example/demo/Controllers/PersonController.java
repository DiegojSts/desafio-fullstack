package com.example.demo.Controllers;

import com.example.demo.Model.person.Person;
import com.example.demo.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAll() {
        return this.personService.getAll();
    }

    @GetMapping(path = "{personID}")
    public Optional<Person> getPersonById(@PathVariable("personID") Integer personID) {
        return this.personService.getPersonById(personID);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/save")
    public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person) {
        var response = this.personService.addPerson(person);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(path = "{personID}")
    public void updatePerson(@PathVariable("personID") Integer personID, @Valid @RequestBody Person person) {

        this.personService.updatePerson(personID, person);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "delete/{personID}")
    public void deletePersonById(@PathVariable("personID") Integer personID) {
        this.personService.deletePersonById(personID);
    }
}
