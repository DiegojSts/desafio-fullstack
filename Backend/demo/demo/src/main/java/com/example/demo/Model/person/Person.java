package com.example.demo.Model.person;

import com.example.demo.Model.contact.Contact;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    private int id;

    @NotBlank(message = "Nome obrigatório")
    private String name;
    @NotBlank(message = "CPF obrigatório")
    private String cpf;
    @NotNull
    private LocalDate birthDate;

    @OneToMany(targetEntity = Contact.class, cascade = CascadeType.ALL)
    @Size(min = 1, message = "Obrigatório ao menos 1 contato")
    private List<Contact> contacts = new ArrayList<>();

    public Person(){}

    public Person(Person person, int id){

        this.id = id;
        this.name = person.getName();
        this.cpf = person.getCpf();
        this.birthDate = person.getBirthDate();
        this.contacts = person.getContacts();

    }
    public Person(String name,
                  String cpf,
                  LocalDate birthDate) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}


