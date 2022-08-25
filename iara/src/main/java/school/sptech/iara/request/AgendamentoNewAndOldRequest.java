package school.sptech.iara.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoNewAndOldRequest {
    private Integer idPrestador;
    private LocalDate dataAntiga;
    private LocalTime horaInicioAntiga;
    private LocalTime horaFimAntiga;
    private String titulo;
    private String descricao;
    private LocalDate dataNova;
    private LocalTime horaInicioNova;
    private LocalTime horaFimNova;

    public Integer getIdPrestador() {
        return idPrestador;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public LocalDate getDataAntiga() {
        return dataAntiga;
    }
    public LocalTime getHoraInicioAntiga() {
        return horaInicioAntiga;
    }
    public LocalTime getHoraFimAntiga() {
        return horaFimAntiga;
    }
    public LocalDate getDataNova() {
        return dataNova;
    }
    public LocalTime getHoraInicioNova() {
        return horaInicioNova;
    }
    public LocalTime getHoraFimNova() {
        return horaFimNova;
    }
}
