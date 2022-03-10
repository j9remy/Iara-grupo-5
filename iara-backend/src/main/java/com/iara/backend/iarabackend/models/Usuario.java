package com.iara.backend.iarabackend.models;

public abstract class Usuario {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String dataNasc;
    private String email;
    private String senha;
    private char sexo;
    private String telefone;
    private Endereco endereco;
    private Boolean autenticado;



    public Usuario(String nome, String sobreNome, String cpf, String dataNasc, String email, String senha, char sexo,
            String telefone, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobreNome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
        this.telefone = telefone;
        this.endereco = endereco;
        this.autenticado = false;
    }



    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getSobreNome() {
        return sobrenome;
    }



    public void setSobrenome(String sobreNome) {
        this.sobrenome = sobreNome;
    }



    public String getCpf() {
        return cpf;
    }



    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



    public String getDataNasc() {
        return dataNasc;
    }



    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getSenha() {
        return senha;
    }



    public void setSenha(String senha) {
        this.senha = senha;
    }



    public char getSexo() {
        return sexo;
    }



    public void setSexo(char sexo) {
        this.sexo = sexo;
    }



    public String getTelefone() {
        return telefone;
    }



    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }



    public Endereco getEndereco() {
        return endereco;
    }



    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public abstract void autenticar();
   

    
}
