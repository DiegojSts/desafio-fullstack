package com.example.demo.pessoa;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Pessoa {
    @Id
    @SequenceGenerator(
            name = "pessoa_sequence",
            sequenceName = "pessoa_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pessoa_sequence"
    )
    private int idPessoa;
    private String nomePessoa;
    private String cpfPessoa;
    private LocalDate dataNascimentoPessoa;

    public Pessoa (){}
    public Pessoa(String nomePessoa,
                  String cpfPessoa,
                  LocalDate dataNascimentoPessoa) {
        this.nomePessoa = nomePessoa;
        this.cpfPessoa = cpfPessoa;
        this.dataNascimentoPessoa = dataNascimentoPessoa;
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


