package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Prestador prestador;

    @NotNull
    @NotBlank
    @Size(max = 500)
    private String mensagem;

    private LocalDateTime dataHora;
    private Boolean visualizado;

    @ManyToOne
    private Chat chat;

    private Boolean enviadoPeloPrestador;

    //constructor
    public Mensagem(Prestador prestador, Cliente cliente,String mensagem, Chat chat, Boolean enviadoPeloPrestador) {
        this.prestador = prestador;
        this.cliente = cliente;
        this.mensagem = mensagem;
        this.chat = chat;
        this.enviadoPeloPrestador = enviadoPeloPrestador;
        dataHora = LocalDateTime.now();
        visualizado = false;
    }
}
