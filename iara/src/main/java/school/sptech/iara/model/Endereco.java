package school.sptech.iara.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "endereco")
public class Endereco {
//    Attributes
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
//    @Pattern(regexp = "[\\s]*[0-9]*[0-9]+",message="msg")
//    @Size(min = 8, max = 9)
    private String cep;

    @NotNull
    @Size(min = 2, max = 100)
    private String rua;

    @NotNull
    @Size(min = 1, max = 10)
    private String numero;

    @NotNull
    @Size(min = 2, max = 60)
    private String bairro;

    @NotNull
    @Size(min = 2, max = 45)
    private String cidade;

    @NotNull
    @Size(min = 2, max = 2)
    private String uf;

    @Size(min = 1, max = 60)
    private String complemento;

//    @OneToMany
//    private List<Cliente> usuarios;

//    Constructor
    public Endereco(){}
    public Endereco(String cep, String rua, String numero, String bairro, String cidade, String uf, String complemento) {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
//        usuarios = new ArrayList<>();
    }

    //    Getter and Setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

//    toString

    @Override
    public String toString() {
        return "Endereco{" +
                "id='" + id + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
