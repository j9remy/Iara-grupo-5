package school.sptech.iara.request;

import java.time.LocalDateTime;

public class ServicoAtribuidoRequest {
    private Integer idServico;
    private String dataInicio;

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
}
