package school.sptech.iara.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String categoria;
    @JsonIgnore
    @Column(length = 50_000_000)
    private byte[] imagem;

    public Categoria(@NotNull String categoria, byte[] imagem) {
        this.categoria = categoria;
        this.imagem = imagem;
    }

    public Categoria(@NotNull String categoria) {
        this.categoria = categoria;
    }
}
