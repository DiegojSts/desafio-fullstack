package com.example.demo.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Errors {
    public static void cpfError(){
        throw new RuntimeException("CPF deve ser válido!");
    }

    public static void emailError(){
        throw new RuntimeException("Email deve ser válido!");
    }

    public static void birthDate(){
        throw new RuntimeException("Data de nascimento não pode ser futura!");
    }

    public static void findByIdError(int personId){

        throw new RuntimeException("O ID " + personId + " não existe!");

    }

}
