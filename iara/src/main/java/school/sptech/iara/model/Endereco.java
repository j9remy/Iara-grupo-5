package school.sptech.iara.model;

public class Endereco {
//    Attributes
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private char uf;
    private String complemento;

//    Constructor
    public Endereco(String cep, String rua, String numero, String bairro, String cidade, char uf, String complemento) {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
    }

    //    Getter and Setter
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
    public char getUf() {
        return uf;
    }
    public void setUf(char uf) {
        this.uf = uf;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


}
