package com.iara.backend.iarabackend.models;

public class Cliente extends Usuario {

    public Cliente(String nome, String sobreNome, String cpf, String dataNasc, String email, String senha, char sexo,
            String telefone, Endereco endereco) {
        super(nome, sobreNome, cpf, dataNasc, email, senha, sexo, telefone, endereco);
        
    }

    @Override
    public void autenticar() {
        
        
    }

    
    
}
