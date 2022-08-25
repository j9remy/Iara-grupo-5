package school.sptech.iara.request;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoIntervalosRequest {
    private Integer idPrestador;
    private DayOfWeek[] diasDaSemana;
    private LocalTime horaInicioTrabalho;
    private LocalTime horaFimTrabalho;
    private LocalTime horaInicioPausa;
    private LocalTime horaFimPausa;

    public Integer getIdPrestador() {
        return idPrestador;
    }
    public DayOfWeek[] getDiasDaSemana() {
        return diasDaSemana;
    }
    public LocalTime getHoraInicioTrabalho() {
        return horaInicioTrabalho;
    }
    public LocalTime getHoraFimTrabalho() {
        return horaFimTrabalho;
    }
    public LocalTime getHoraInicioPausa() {
        return horaInicioPausa;
    }
    public LocalTime getHoraFimPausa() {
        return horaFimPausa;
    }
}
