package school.sptech.iara.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer idRemetente;
    private Integer idDestinatario;

    @NotNull
    @NotBlank
    private String mensagem;

    private LocalDateTime dataHora;
    private Boolean visualizado;

    @ManyToOne
    private Chat chat;

    //constructor
    public Mensagem(Integer remetente, Integer destinatario, @NotNull String mensagem, Chat chat) {
        this.idRemetente = remetente;
        this.idDestinatario = destinatario;
        this.mensagem = mensagem;
        this.chat = chat;
        dataHora = LocalDateTime.now();
        visualizado = false;
    }

    // Getter and Setter
    public Boolean getVisualizado() {
        return visualizado;
    }
    public void setVisualizado(Boolean visualizado) {
        this.visualizado = visualizado;
    }
}
