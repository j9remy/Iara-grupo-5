package com.iara.backend.iarabackend.models;

import java.time.LocalDate;

public class Cliente extends Usuario {

    public Cliente(String nome, String sobreNome, String cpf, LocalDate dataNasc, String email, String senha, char sexo,
                   String telefone, Endereco endereco) {
        super(nome, sobreNome, cpf, dataNasc, email, senha, sexo, telefone, endereco);
        
    }

    @Override
    public boolean autenticar(String email, String senha) {
        return true;
        
    }

    
    
}
