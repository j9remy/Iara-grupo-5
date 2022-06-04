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

    private Double avaliacao;
    private LocalDateTime dataHora;

    public AvaliacaoCliente(){}
    public AvaliacaoCliente(Double avaliacao, Cliente cliente) {
        this.avaliacao = avaliacao;
        dataHora = LocalDateTime.now();
        this.cliente = cliente;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
