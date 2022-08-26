package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(length = 50_000_000)
    private byte[] foto;
    @NotNull
    private LocalDate data;

    @ManyToOne
    private Portifolio portifolio;

    public Foto(byte[] foto, Portifolio portifolio) {
        this.foto = foto;
        this.portifolio = portifolio;
        this.data = LocalDate.now();
    }

}
