package school.sptech.iara.request;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class ServicoAtribuidoRequest {
    @NotNull
    private Integer idServico;
    @NotNull
    private LocalDateTime dataInicio;
    private String observacoes;

    public Integer getIdServico() {
        return idServico;
    }
    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
