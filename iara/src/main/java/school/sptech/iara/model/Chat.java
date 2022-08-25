package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Data
@NoArgsConstructor
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

    //Constructor
    public Chat(ServicoAtribuido servicoAtribuido) {
        this.servicoAtribuido = servicoAtribuido;
        finalizado = false;
    }
}
