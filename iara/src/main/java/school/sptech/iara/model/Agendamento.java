package school.sptech.iara.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public Agendamento() {
    }

    public Integer getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    public LocalTime getHoraFim() {
        return horaFim;
    }
    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
    public Agenda getAgenda() {
        return agenda;
    }
    public ServicoAtribuido getServicoAtribuido() {
        return servicoAtribuido;
    }
    public void setServicoAtribuido(ServicoAtribuido servicoAtribuido) {
        this.servicoAtribuido = servicoAtribuido;
    }
}
