package com.example.demo.contato;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table()
public class Contato {
    private int idContato;
    private int idPessoa;
    private String nomeContato;
    private String telefone;
    private String email;

    public Contato() {}


}
