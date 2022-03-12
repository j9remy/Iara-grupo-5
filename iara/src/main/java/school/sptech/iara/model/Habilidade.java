package school.sptech.iara.model;

public class Habilidade {
//    Attribute
    private String habilidade;
    private String descricao;

//    Constructor
    public Habilidade(String habilidade, String descricao) {
        this.habilidade = habilidade;
        this.descricao = descricao;
    }

//    Getter and Setter
    public String getHabilidade() {
        return habilidade;
    }
    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

//    toString
    @Override
    public String toString() {
        return "Habilidade{" +
                "habilidade='" + habilidade + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
