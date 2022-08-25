package school.sptech.iara.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoRequest {
    private Integer idPrestador;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public Integer getIdPrestador() {
        return idPrestador;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public LocalDate getData() {
        return data;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public LocalTime getHoraFim() {
        return horaFim;
    }
}
