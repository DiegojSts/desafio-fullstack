package com.example.demo.Services;

import com.example.demo.Errors.Errors;
import com.example.demo.Model.contact.Contact;
import com.example.demo.Model.person.Pessoa;
import com.example.demo.Repository.ContactRepository;
import com.example.demo.Repository.PessoaRepository;
import com.example.demo.Validators.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    private final PessoaRepository personRepository;
    private final ContactRepository contactRepository;
    private boolean exists = false;


    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, ContactRepository contactRepository) {
        this.personRepository = pessoaRepository;
        this.contactRepository = contactRepository;

    }

    public List<Pessoa> getAll() {

        return personRepository.findAll();
    }

    public Pessoa addPerson(Pessoa pessoa) {
        boolean isValid;

        isValid = Utils
                .executeCheck(pessoa.getCpfPessoa(),
                        pessoa.getDataNascimentoPessoa().toString(), "yyyy-MM-dd");
        if (!isValid) {
            throw new IllegalStateException("");
        }

        return personRepository.save(pessoa);
    }

    public Optional<Pessoa> getPersonById(int personID) {
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
    public void updatePerson(Integer personID, Pessoa personFromPutRequest) {

        personRepository.findById(personID).orElseThrow(() -> new IllegalStateException("Pessoa de id" + personID + "não existe!"));


        if (!Utils.isCPF(personFromPutRequest.getCpfPessoa())) {
            throw new IllegalStateException("CPF deve ser válido!");
        }

        if (!Utils.isDateFuture(personFromPutRequest.getDataNascimentoPessoa().toString(), "yyyy-MM-dd")) {
            throw new IllegalStateException("Data não pode ser futura!");
        }

        Pessoa personFoundInRepository;

        var listIds = personFromPutRequest.getContacts().stream().map(Contact::getContactId).collect(Collectors.toList());
        personFoundInRepository = new Pessoa(personFromPutRequest, personID);

        personRepository.save(personFoundInRepository);
        contactRepository.deleteAllById(listIds);



    }
}
