package school.sptech.iara.request;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class ServicoAtribuidoRequest {
    @NotNull
    private Integer idServico;
    @NotNull
    private String dataInicio;
    private String observacoes;

    public Integer getIdServico() {
        return idServico;
    }
    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
