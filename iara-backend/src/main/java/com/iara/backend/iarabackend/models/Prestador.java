package com.iara.backend.iarabackend.models;

public class Prestador extends Usuario{
    private String resumo;

    public Prestador(String nome, String sobreNome, String cpf, String dataNasc, String email, String senha, char sexo,
            String telefone, Endereco endereco, String resumo) {
        super(nome, sobreNome, cpf, dataNasc, email, senha, sexo, telefone, endereco);
        this.resumo = resumo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public void addHabilidade(){
        //Your code Must be here
    }

    public void addServico(){
        //Your code must be here
    }

    @Override
    public void autenticar() {
        // TODO Auto-generated method stub
        
    }

    
}
