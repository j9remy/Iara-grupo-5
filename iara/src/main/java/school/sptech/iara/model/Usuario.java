package school.sptech.iara.model;

import java.time.LocalDate;

public abstract class Usuario implements Avaliavel{
//    Attributes
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNasc;
    private String email;
    private String senha;
    private char sexo;
    private String telefone;
    private boolean autenticado;
    private Endereco endereco;

//    Constructor
    public Usuario(String nome, String sobrenome,
                   String cpf, LocalDate dataNasc,
                   String email, String senha,
                   char sexo, String telefone,
                   Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
        this.telefone = telefone;
        this.autenticado = false;
        this.endereco = endereco;
    }

//    Getter and Setter
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public LocalDate getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public boolean isAutenticado() {
        return autenticado;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

//    Methods
    public void autenticar(String email, String senha){
        if (email.equalsIgnoreCase(getEmail()) &&
            senha.equals(this.senha)){
            if (autenticado){
                return;
            }else{
                autenticado = true;
            }
        }
    }

    public void logOff(String email, String senha){
        if (email.equalsIgnoreCase(getEmail()) &&
                senha.equals(this.senha)){
            if (!autenticado){
                return;
            }else{
                autenticado = true;
            }
        }
    }
}
