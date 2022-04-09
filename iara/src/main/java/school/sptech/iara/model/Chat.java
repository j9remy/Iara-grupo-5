package school.sptech.iara.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Chat {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private ServicoAtribuido servicoAtribuido;

    private Boolean finalizado;

    //Constructor
    public Chat(ServicoAtribuido servicoAtribuido) {
        this.servicoAtribuido = servicoAtribuido;
    }

    // Getter and Setter
    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }


    //Methods
    public void finalizar(){
        setFinalizado(true);
    }
}
