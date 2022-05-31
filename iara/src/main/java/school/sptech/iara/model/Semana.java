package school.sptech.iara.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class Semana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate diaInicial;
    private LocalDate diaFinal;

    @ManyToOne
    private Agenda agenda;

    public Semana(LocalDate diaInicial, LocalDate diaFinal, Agenda agenda) {
        this.diaInicial = diaInicial;
        this.diaFinal = diaFinal;
        this.agenda = agenda;
    }

    public Semana() {
    }

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

}
