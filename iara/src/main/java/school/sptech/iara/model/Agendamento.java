package school.sptech.iara.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    @NotNull
    private LocalDate data;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFim;
    @ManyToOne
    @JsonIgnore
    @NotNull
    private Agenda agenda;
    @ManyToOne
    private ServicoAtribuido servicoAtribuido;

    public Agendamento(String titulo, String descricao, @NotNull LocalDate data,
                       @NotNull LocalTime horaInicio, @NotNull LocalTime horaFim, @NotNull Agenda agenda) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.agenda = agenda;
    }

    public Agendamento(String titulo, @NotNull LocalDate data, @NotNull LocalTime horaInicio,
                       @NotNull LocalTime horaFim, @NotNull Agenda agenda) {
        this.titulo = titulo;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.agenda = agenda;
    }

}
