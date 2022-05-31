package school.sptech.iara.request;

import school.sptech.iara.model.Semana;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class DiaRequest {

    @NotNull
    @Min(1)
    @Max(7)
    private Integer codigoDia;
    @NotNull
    private LocalTime horarioInicial;
    @NotNull
    private LocalTime horarioFinal;
    @NotNull
    private Integer idSemana;
    @NotNull
    private Integer idPrestador;
    private LocalTime inicioIntervalo;
    private LocalTime finalIntervalo;



    public Integer getCodigoDia() {
        return codigoDia;
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
    public Integer getIdSemana() {
        return idSemana;
    }
    public Integer getIdPrestador() {
        return idPrestador;
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
