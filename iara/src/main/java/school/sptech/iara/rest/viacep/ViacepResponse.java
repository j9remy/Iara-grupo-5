package school.sptech.iara.rest.viacep;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ViacepResponse {
//    @NotNull
//    @Pattern(regexp = "[\\s]*[0-9]*[0-9]+",message="msg")
//    @Size(min = 8, max = 9)
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public String getCep() {
        return cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public String getComplemento() {
        return complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public String getLocalidade() {
        return localidade;
    }
    public String getUf() {
        return uf;
    }
}
