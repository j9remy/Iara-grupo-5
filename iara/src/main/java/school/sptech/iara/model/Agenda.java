package school.sptech.iara.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    Prestador prestador;

    public Agenda(Prestador prestador) {
        this.prestador = prestador;
    }
    public Agenda() {
    }

}
