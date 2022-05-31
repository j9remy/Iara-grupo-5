package school.sptech.iara.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class SemanaRequest {

    @NotNull
    private LocalDate diaInicial;
    @NotNull
    private LocalDate diaFinal;
    @NotNull
    private Integer idPrestador;
    @NotNull
    private Integer[] codigoDia;
    @NotNull
    private LocalTime horarioInicial;
    @NotNull
    private LocalTime horarioFinal;
    private LocalTime inicioIntervalo;
    private LocalTime finalIntervalo;

    public LocalDate getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(LocalDate diaInicial) {
        this.diaInicial = diaInicial;
    }

    public LocalDate getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(LocalDate diaFinal) {
        this.diaFinal = diaFinal;
    }

    public Integer getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public Integer[] getCodigoDia() {
        return codigoDia;
    }

    public void setCodigoDia(Integer[] codigoDia) {
        this.codigoDia = codigoDia;
    }

    public LocalTime getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(LocalTime horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public LocalTime getInicioIntervalo() {
        return inicioIntervalo;
    }

    public void setInicioIntervalo(LocalTime inicioIntervalo) {
        this.inicioIntervalo = inicioIntervalo;
    }

    public LocalTime getFinalIntervalo() {
        return finalIntervalo;
    }

    public void setFinalIntervalo(LocalTime finalIntervalo) {
        this.finalIntervalo = finalIntervalo;
    }
}
