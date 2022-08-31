package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String categoria;
    private byte[] imagem;

    public Categoria(@NotNull String categoria, byte[] imagem) {
        this.categoria = categoria;
        this.imagem = imagem;
    }

    public Categoria(@NotNull String categoria) {
        this.categoria = categoria;
    }
}
