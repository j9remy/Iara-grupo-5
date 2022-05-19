package school.sptech.iara.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EnderecoSimplesRequest {
    @NotNull
    @Pattern(regexp = "[\\s]*[0-9]*[0-9]+",message="cep em formato inválido")
    @Size(min = 8, max = 9)
    private String cep;

    @NotNull
    @Size(min = 1, max = 10)
    private String numero;

    @Size(min = 1, max = 60)
    private String complemento;

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
