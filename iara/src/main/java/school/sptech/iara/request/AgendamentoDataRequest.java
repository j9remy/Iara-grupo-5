package school.sptech.iara.request;

import java.time.LocalDate;

public class AgendamentoRequest {
    private Integer idPrestador;
    private LocalDate data;

    public Integer getIdPrestador() {
        return idPrestador;
    }
    public LocalDate getData() {
        return data;
    }
}
