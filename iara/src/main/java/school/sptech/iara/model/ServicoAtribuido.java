package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.iara.response.ServicoAtribuidoResponse;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class ServicoAtribuido implements Avaliavel{
//    Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Cliente cliente;
    private LocalDateTime dataHoraFim;
    private String observacoes;
    private String status;
    private boolean finalizado;
    private Double avaliacao;
    @ManyToOne
    private Servico servico;
//    @OneToOne
//    private Chat chat;

//    Constructor
    public ServicoAtribuido(Servico servico,
                            Cliente cliente) {
        this.cliente = cliente;
        this.status = "Agendado";
        this.finalizado = false;
        this.avaliacao = -1.0;
        this.servico = servico;
//        this.chat = chat;
    }
    public ServicoAtribuido(Servico servico,
                            Cliente cliente,
                            String observacoes) {
        this.cliente = cliente;
        this.observacoes = observacoes;
        this.status = "Agendado";
        this.finalizado = false;
        this.avaliacao = -1.0;
        this.servico = servico;
//        this.chat = chat;
    }

    //    Methods
    // Simplesmente retorna o valor do atributo avaliacao
    @Override
    public double calcAvaliacao() {
        return avaliacao;
    }

    // Adiciona avaliação caso ainda não esteja avaliado
    public void addAvaliacao(double avaliacao) {
        if(Objects.isNull(this.avaliacao) && avaliacao >= 0 && avaliacao <= 5 && finalizado){
            this.avaliacao = avaliacao;
        }
    }

    // Caso serviço não esteja finalizado, finaliza-o alterando 'finalizado' para true
    public void finalizarServico(){
        if (!isFinalizado()){
            setFinalizado(true);
            dataHoraFim = LocalDateTime.now();
        }
    }

    public ServicoAtribuidoResponse formatarResposta(){
        ServicoAtribuidoResponse servico = new ServicoAtribuidoResponse(
                getId(),
                getCliente().getId(),
                getServico().getId(),
                getDataHoraFim(),
                getObservacoes(),
                getStatus(),
                isFinalizado(),
                getAvaliacao());
        return servico;
    }
}
