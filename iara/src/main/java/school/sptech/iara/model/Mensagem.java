package school.sptech.iara.model;

import org.hibernate.annotations.Comment;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    //constructor
    public Mensagem(){}

    // Getter and Setter
    public Boolean getVisualizado() {
        return visualizado;
    }
    public void setVisualizado(Boolean visualizado) {
        this.visualizado = visualizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Boolean getEnviadoPeloPrestador() {
        return enviadoPeloPrestador;
    }

    public void setEnviadoPeloPrestador(Boolean enviadoPeloPrestador) {
        this.enviadoPeloPrestador = enviadoPeloPrestador;
    }
}
