package school.sptech.iara.request;

import org.jetbrains.annotations.NotNull;
import school.sptech.iara.model.Agendamento;
import school.sptech.iara.model.Servico;
import school.sptech.iara.model.ServicoAtribuido;

import java.time.LocalDate;

public class HorariosDisponiveisRequest {
    @NotNull
    private Integer idPrestador;
    @NotNull
    private LocalDate data;
    @NotNull
    private Integer servicoId;

    public Integer getIdPrestador() {
        return idPrestador;
    }
    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getservicoId() {
        return servicoId;
    }

    public void setservicoId(Integer agendamentoDesejadoId) {
        this.servicoId = agendamentoDesejadoId;
    }
}
