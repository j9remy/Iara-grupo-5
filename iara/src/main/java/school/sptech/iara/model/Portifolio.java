package school.sptech.iara.model;

import org.hibernate.validator.constraints.URL;
import school.sptech.iara.util.Pilha;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Stack;

@Entity
public class Portifolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Foto> fotos;


    @ManyToOne
    private Prestador prestador;

    // Constructor
    public Portifolio(Prestador prestador) {
        this.prestador = prestador;
    }
    public Portifolio(){}

    // Getter and Setter

    public List<Foto> getFotos() {
        return fotos;
    }
    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    // Método

    public void adicionarFoto(Foto foto){
        fotos.add(foto);
    }
}
