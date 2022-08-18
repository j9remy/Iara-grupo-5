package school.sptech.iara.request;

import java.time.LocalDate;

public class AgendamentoDataRequest {
    private Integer idPrestador;
    private LocalDate data;

    public Integer getIdPrestador() {
        return idPrestador;
    }
    public LocalDate getData() {
        return data;
    }
    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
}
