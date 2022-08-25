package school.sptech.iara.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Usuario implements Avaliavel{
//    Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @Column(length = 50_000_000)
    private byte[] foto;

    @NotNull
    @Size(min = 2, max = 30)
    private String nome;

    @NotNull
    @Size(min = 2, max = 50)
    private String sobrenome;

    @NotNull
//    @CPF
    private String cpf;

    @NotNull
    @Past
    private LocalDate dataNasc;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String senha;

    @NotNull
    private char genero;

    @NotNull
    @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})" , message = "Informe um telefone válido com ou sem DDD")
    private String telefone;

    private boolean autenticado;

    @OneToMany
    private List<Endereco> enderecos;


//    Constructor
    public Usuario(String nome, String sobrenome,
                   String cpf, LocalDate dataNasc,
                   String email, String senha,
                   char genero, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.telefone = telefone;
        this.autenticado = false;
        enderecos = new ArrayList<>();
    }

    //    Methods

    private boolean usuarioExiste(String email, String senha){
        return email.equalsIgnoreCase(getEmail()) &&
                senha.equals(this.senha);
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

    public void addEndereco(Endereco endereco){
        enderecos.add(endereco);
    }

}
