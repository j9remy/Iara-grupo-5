package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
public class Portifolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToMany
//    private List<Foto> fotos;

    @ManyToOne
    private Prestador prestador;

    // Constructor
    public Portifolio(Prestador prestador) {
        this.prestador = prestador;
    }

}
