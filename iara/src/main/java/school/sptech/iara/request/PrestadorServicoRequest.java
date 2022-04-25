package school.sptech.iara.request;

import org.jetbrains.annotations.NotNull;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;

public class PrestadorServicoRequest {
    @NotNull
    private Integer idPrestador;

    @NotNull
    private Servico servico;

    public Integer getIdPrestador() {
        return idPrestador;
    }
    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }
    public Servico getServico() {
        return servico;
    }
    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
