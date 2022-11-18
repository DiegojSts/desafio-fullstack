package com.example.demo.Services;

import com.example.demo.Errors.Errors;
import com.example.demo.Model.contact.Contact;
import com.example.demo.Model.person.Person;
import com.example.demo.Repository.ContactRepository;
import com.example.demo.Repository.PersonRepository;
import com.example.demo.Validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;
    private boolean exists = false;


    @Autowired
    public PersonService(PersonRepository personRepository, ContactRepository contactRepository) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;

    }

    public List<Person> getAll() {

        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        boolean isValid;

        isValid = PersonValidator
                .executeCheck(person.getCpf(),
                        person.getBirthDate().toString(), "yyyy-MM-dd");
        if (!isValid) {
            throw new IllegalStateException("");
        }

        return personRepository.save(person);
    }

    public Optional<Person> getPersonById(int personID) {
        this.exists = personRepository.existsById(personID);

        if (!this.exists) {
            Errors.findByIdError(personID);
        }
        return personRepository.findById(personID);
    }

    public void deletePersonById(Integer personID) {
        this.exists = personRepository.existsById(personID);

        if (!this.exists) {
            Errors.findByIdError(personID);
        }

        this.personRepository.deleteById(personID);

    }
    public void updatePerson(Integer personID, Person personFromPutRequest) {

        personRepository.findById(personID).orElseThrow(() -> new IllegalStateException("Pessoa de id" + personID + "não existe!"));


        if (!PersonValidator.isCPF(personFromPutRequest.getCpf())) {
            throw new IllegalStateException("CPF deve ser válido!");
        }

        if (!PersonValidator.isDateFuture(personFromPutRequest.getBirthDate().toString(), "yyyy-MM-dd")) {
            throw new IllegalStateException("Data não pode ser futura!");
        }

        Person personFoundInRepository;

        var listIds = personFromPutRequest.getContacts().stream().map(Contact::getContactId).collect(Collectors.toList());
        personFoundInRepository = new Person(personFromPutRequest, personID);

        personRepository.save(personFoundInRepository);
        contactRepository.deleteAllById(listIds);



    }
}
