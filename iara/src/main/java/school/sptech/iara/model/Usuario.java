package school.sptech.iara.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@MappedSuperclass
public abstract class Usuario implements Avaliavel{
//    Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    
    private String nome;
    private String sobrenome;
    private String cpf;
    private String dataNasc;
    private String email;
    private String senha;
    private char sexo;
    private String telefone;
    private boolean autenticado;
    private Endereco endereco;

//    Constructor
    public Usuario(String nome, String sobrenome,
                   String cpf, String dataNasc,
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

    public boolean usuarioExiste(String email, String senha){
        if (email.equalsIgnoreCase(getEmail()) &&
                senha.equals(this.senha)){
            return true;
        }
        return false;
    }

    public String autenticar(String email, String senha){
        if(usuarioExiste(email,senha)){
            autenticado = true;
            return "Usuario logado com sucesso";
        }else {
            return "Usuario não encontrado";
        }
    }

    public String logOff(String email, String senha){
        if(usuarioExiste(email,senha)){
            autenticado = false;
            return "LogOff finalizado com sucesso";
        }else {
            return "Usuario não encontrado";
        }
    }

    //toString

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNasc=" + dataNasc +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", sexo=" + sexo +
                ", telefone='" + telefone + '\'' +
                ", autenticado=" + autenticado +
                ", endereco=" + endereco +
                '}';
    }
}
