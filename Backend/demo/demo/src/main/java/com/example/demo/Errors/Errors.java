package com.example.demo.Errors;

public class Errors {
    public void cpfError(){
        throw new RuntimeException("CPF deve ser válido!");
    }

    public void emailError(){
        throw new RuntimeException("Email deve ser válido!");
    }

    public void dataNascimentoError(){
        throw new RuntimeException("Data de nascimento não pode ser futura!");
    }
}
