package school.sptech.iara.model;

import javax.persistence.*;

@Entity
public class AvaliacaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer avaliacao;

    public AvaliacaoCliente(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }
}
