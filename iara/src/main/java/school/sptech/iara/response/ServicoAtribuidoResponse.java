package school.sptech.iara.response;

import com.github.javafaker.Bool;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServicoAtribuidoResponse {
    private Integer id;
    private Integer clienteId;
    private Integer servicoId;
    private LocalDateTime dataHoraFim;
    private String observacoes;
    private String status;
    private Boolean finalizado;
    private Double avaliacao;

    public ServicoAtribuidoResponse(Integer id, Integer clienteId,
                                    Integer servicoId,
                                    LocalDateTime dataHoraFim, String observacoes,
                                    String status, Boolean finalizado,
                                    Double avaliacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.dataHoraFim = dataHoraFim;
        this.observacoes = observacoes;
        this.status = status;
        this.finalizado = finalizado;
        this.avaliacao = avaliacao;
    }

    public Integer getId() {
        return id;
    }
    public Integer getClienteId() {
        return clienteId;
    }
    public Integer getServicoId() {
        return servicoId;
    }
    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public String getStatus() {
        return status;
    }
    public Boolean getFinalizado() {
        return finalizado;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }
}
