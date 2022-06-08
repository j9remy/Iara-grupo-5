package school.sptech.iara.request;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;

public class PrestadorUpdateRequest {
    @NotNull
    private Integer id;
    @NotNull
    @Size(min = 2, max = 30)
    private String nome;
    @NotNull
    @Size(min = 2, max = 50)
    private String sobrenome;
    @NotNull
    @Past
    private LocalDate dataNasc;
    @NotNull
    @Size(min = 5, max = 20)
    private String senha;
    @NotNull
    private char genero;
    @NotNull
    @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})" , message = "Informe um telefone válido com ou sem DDD")
    private String telefone;
    @NotNull
    private Boolean atendeDomicilio;
    private String resumo;
    @NotNull
    private Boolean atendeEstabelecimento;
    private Double distancia;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public LocalDate getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public char getGenero() {
        return genero;
    }
    public void setGenero(char genero) {
        this.genero = genero;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Boolean getAtendeDomicilio() {
        return atendeDomicilio;
    }
    public void setAtendeDomicilio(Boolean atendeDomicilio) {
        this.atendeDomicilio = atendeDomicilio;
    }
    public String getResumo() {
        return resumo;
    }
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    public Boolean getAtendeEstabelecimento() {
        return atendeEstabelecimento;
    }
    public void setAtendeEstabelecimento(Boolean atendeEstabelecimento) {
        this.atendeEstabelecimento = atendeEstabelecimento;
    }
    public Double getDistancia() {
        return distancia;
    }
    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
}
