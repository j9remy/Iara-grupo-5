package school.sptech.iara.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoDataHoraRequest {
    private Integer idPrestador;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;

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
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    public LocalTime getHoraFim() {
        return horaFim;
    }
    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
}
