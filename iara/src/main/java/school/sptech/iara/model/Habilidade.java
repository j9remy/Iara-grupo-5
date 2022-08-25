package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @NotBlank
    @Size(min = 2, max = 30)
    private String habilidade;

    @NotBlank
    @Size(min = 2, max = 300)
    private String descricao;

//    Constructor
    public Habilidade(String habilidade, String descricao) {
        this.habilidade = habilidade;
        this.descricao = descricao;
    }

}
