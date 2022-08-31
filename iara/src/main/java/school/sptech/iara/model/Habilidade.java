package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Habilidade {
//    Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Categoria categoria;

    @NotBlank
    @Size(min = 2, max = 300)
    private String descricao;

//    Constructor
    public Habilidade(Categoria categoria, String descricao) {
        this.categoria = categoria;
        this.descricao = descricao;
    }

}
