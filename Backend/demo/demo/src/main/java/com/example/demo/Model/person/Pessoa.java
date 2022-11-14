package com.example.demo.Model.person;

import com.example.demo.Model.contact.Contact;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @SequenceGenerator(name = "pessoa_sequence", sequenceName = "pessoa_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_sequence")
    private int idPessoa;

    @NotBlank(message = "Nome obrigatório")
    private String nomePessoa;
    @NotBlank(message = "CPF obrigatório")
    private String cpfPessoa;
    @NotNull
    private LocalDate dataNascimentoPessoa;

    @OneToMany(targetEntity = Contact.class, cascade = CascadeType.ALL)
    @Size(min = 1, message = "Obrigatório ao menos 1 contato")
    private List<Contact> contacts = new ArrayList<>();

    public Pessoa (){}
    public Pessoa(String nomePessoa,
                  String cpfPessoa,
                  LocalDate dataNascimentoPessoa) {
        this.nomePessoa = nomePessoa;
        this.cpfPessoa = cpfPessoa;
        this.dataNascimentoPessoa = dataNascimentoPessoa;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public LocalDate getDataNascimentoPessoa() {
        return dataNascimentoPessoa;
    }

    public void setDataNascimentoPessoa(LocalDate dataNascimentoPessoa) {
        this.dataNascimentoPessoa = dataNascimentoPessoa;
    }
}


