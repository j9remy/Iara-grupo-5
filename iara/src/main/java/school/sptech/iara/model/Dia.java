package school.sptech.iara.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Dia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Min(1)
    @Max(7)
    private Integer codigoDia;
    @Size(min = 3, max = 3)
    private String siglaDia;
    @Size(max = 20)
    private String nomeDia;
    @NotNull
    private LocalTime horarioInicial;
    @NotNull
    private LocalTime horarioFinal;
    private LocalTime inicioIntervalo;
    private LocalTime finalIntervalo;

    @ManyToOne
    private Semana semana;

    public Dia(Integer codigoDia, LocalTime horarioInicial, LocalTime horarioFinal, Semana semana) {
        this.codigoDia = codigoDia;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.semana = semana;
        atribuicaoDia(codigoDia);
    }

    public Dia() {
    }

    public Dia(Integer codigoDia, LocalTime horarioInicial, LocalTime horarioFinal,
               LocalTime inicioIntervalo, LocalTime finalIntervalo, Semana semana) {
        this.codigoDia = codigoDia;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.inicioIntervalo = inicioIntervalo;
        this.finalIntervalo = finalIntervalo;
        this.semana = semana;
        atribuicaoDia(codigoDia);
    }

    public void atribuicaoDia(Integer codigoDia){
        switch (codigoDia){
            case 1:
                nomeDia = "Domingo";
                siglaDia = "DOM";
                break;
            case 2:
                nomeDia = "Segunda";
                siglaDia = "SEG";
                break;
            case 3:
                nomeDia = "Terça";
                siglaDia = "TER";
                break;
            case 4:
                nomeDia = "Quarta";
                siglaDia = "QUA";
                break;
            case 5:
                nomeDia = "Quinta";
                siglaDia = "QUI";
                break;
            case 6:
                nomeDia = "Sexta";
                siglaDia = "SEX";
                break;
            case 7:
                nomeDia = "Sábado";
                siglaDia = "SAB";
                break;
            default:
                System.out.println("Código invalido!: Digite um valor entre 1 e 7!");
                break;
        }

    }

    public Integer getCodigoDia() {
        return codigoDia;
    }

    public void setCodigoDia(Integer codigoDia) {
        this.codigoDia = codigoDia;
    }

    public String getSiglaDia() {
        return siglaDia;
    }

    public void setSiglaDia(String siglaDia) {
        this.siglaDia = siglaDia;
    }

    public String getNomeDia() {
        return nomeDia;
    }

    public void setNomeDia(String nomeDia) {
        this.nomeDia = nomeDia;
    }

    public Integer getId() {
        return id;
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

    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }
}
