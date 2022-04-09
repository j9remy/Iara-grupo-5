package school.sptech.iara.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Portifolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @URL
    private String url;

    private LocalDate dataPostagem;

    @ManyToOne
    private Servico servico;

    // Constructor
    public Portifolio(String url) {
        this.url = url;
        this.dataPostagem = LocalDate.now();
    }

    // Getter and Setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
