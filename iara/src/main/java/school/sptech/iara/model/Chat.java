package school.sptech.iara.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Table(name="Chat",  uniqueConstraints={
        @UniqueConstraint(columnNames={"id"})
})
public class Chat {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ServicoAtribuido servicoAtribuido;

    private Boolean finalizado;

    public Chat() {
    }

    //Constructor
    public Chat(ServicoAtribuido servicoAtribuido) {
        this.servicoAtribuido = servicoAtribuido;
        finalizado = false;
    }


    // Getter and Setter
    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServicoAtribuido getServicoAtribuido() {
        return servicoAtribuido;
    }

    public void setServicoAtribuido(ServicoAtribuido servicoAtribuido) {
        this.servicoAtribuido = servicoAtribuido;
    }

    //Methods
    public void finalizar(){
        setFinalizado(true);
    }
}
