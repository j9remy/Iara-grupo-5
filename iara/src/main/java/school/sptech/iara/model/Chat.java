package school.sptech.iara.model;

import javax.persistence.*;

@Entity
public class Chat {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
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


    //Methods
    public void finalizar(){
        setFinalizado(true);
    }
}
