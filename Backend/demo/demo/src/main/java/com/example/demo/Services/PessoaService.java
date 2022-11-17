package com.example.demo.Services;

import com.example.demo.Errors.Errors;
import com.example.demo.Model.contact.Contact;
import com.example.demo.Model.person.Pessoa;
import com.example.demo.Repository.PessoaRepository;
import com.example.demo.Validators.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository personRepository;
    private boolean exists = false;


    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.personRepository = pessoaRepository;
    }

    public List<Pessoa> getAll() {
//
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

    @Transactional
    public void updatePerson(Integer personID, Pessoa personFromPutRequest) {
        this.exists = personRepository.existsById(personID);


        Pessoa personFoundInRepository = personRepository.findById(personID)
                .orElseThrow(() -> new IllegalStateException("Pessoa de id" + personID + "não existe!"));

        if (personFromPutRequest.getNomePessoa() != null &&
                personFromPutRequest.getNomePessoa().length() > 0 &&
                !Objects.equals(personFromPutRequest.getNomePessoa(), personFoundInRepository.getNomePessoa())
        ) {
            personFoundInRepository.setNomePessoa(personFromPutRequest.getNomePessoa());
        }
//
        if (Utils.isCPF(personFromPutRequest.getCpfPessoa()) &&
                !Objects.equals(personFoundInRepository.getCpfPessoa(), personFromPutRequest.getCpfPessoa()))
        {
            personFoundInRepository.setCpfPessoa(personFromPutRequest.getCpfPessoa());
        }
//
        if (!Utils.isDateFuture(personFromPutRequest.getDataNascimentoPessoa().toString(), "yyyy-MM-dd") &&
                !Objects.equals(personFoundInRepository.getDataNascimentoPessoa(), personFromPutRequest.getDataNascimentoPessoa())
        ) {
            personFoundInRepository
                    .setDataNascimentoPessoa(LocalDate.parse(personFromPutRequest
                            .getDataNascimentoPessoa()
                            .toString()));
        }

//        List<Pessoa> contactsFromPerson = new ArrayList<>();
        for (int index = 0; index < personFoundInRepository.getContacts().size(); index++) {
            if(personFromPutRequest.getContacts().get(index).getContactName() != null){

                personFoundInRepository.getContacts().set(index, new Contact(
                        personFromPutRequest.getContacts().get(index).getContactName(),
                        "EmailModificadoNaMao",
                        "PhoneNaMao"));

            }

        }

    }
}
