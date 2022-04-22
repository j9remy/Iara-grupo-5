package school.sptech.iara.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AvaliacaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Cliente cliente;

    private Integer avaliacao;
    private LocalDateTime dataHora;

    public AvaliacaoCliente(){}
    public AvaliacaoCliente(Integer avaliacao, Cliente cliente) {
        this.avaliacao = avaliacao;
        dataHora = LocalDateTime.now();
        this.cliente = cliente;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }
}
