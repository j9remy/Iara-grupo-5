package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Entity
public class AvaliacaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Cliente cliente;

    private Double avaliacao;
    private LocalDateTime dataHora;

    public AvaliacaoCliente(Double avaliacao, Cliente cliente) {
        this.avaliacao = avaliacao;
        dataHora = LocalDateTime.now();
        this.cliente = cliente;
    }

}
