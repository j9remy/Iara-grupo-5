package school.sptech.iara.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EnderecoRequest extends EnderecoSimplesRequest{
    @NotNull
    @Size(min = 2, max = 100)
    private String rua;

    @NotNull
    @Size(min = 2, max = 60)
    private String bairro;

    @NotNull
    @Size(min = 2, max = 45)
    private String cidade;

    @NotNull
    @Size(min = 2, max = 2)
    private String uf;

    public String getRua() {
        return rua;
    }
    public String getBairro() {
        return bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public String getUf() {
        return uf;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
}
