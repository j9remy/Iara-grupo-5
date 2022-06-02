package school.sptech.iara.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(length = 50_000_000)
    private byte[] foto;
    @NotNull
    private LocalDate data;

    public Foto(byte[] foto, LocalDate data) {
        this.foto = foto;
        this.data = data;
    }
    public Foto(){}

    public Integer getId() {
        return id;
    }
    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
}
