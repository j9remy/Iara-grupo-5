package school.sptech.iara.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.Objects;


public class ServicoAtribuido implements Avaliavel{
//    Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Cliente cliente;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private boolean finalizado;
    private Double avaliacao;

//    Constructor
    public ServicoAtribuido(Cliente cliente) {
        this.cliente = cliente;
        this.horaInicio = LocalTime.now();
        horaFim = horaInicio;
        finalizado = false;
        avaliacao = -1d;
    }

//    Getter and Setter
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    public boolean isFinalizado() {
        return finalizado;
    }
    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    public LocalTime getHoraFim() {
        return horaFim;
    }
    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
//    Methods
    // Simplesmente retorna o valor do atributo avaliacao
    @Override
    public double getAvaliacao() {
        return avaliacao;
    }

    // Adiciona avaliação caso ainda não esteja avaliado
    public void addAvaliacao(double avaliacao) {
        if(Objects.isNull(this.avaliacao) && avaliacao >= 0 && avaliacao <= 5 && finalizado){
            this.avaliacao = avaliacao;
        }
    }

    // Caso serviço não esteja finalizado, finaliza-o alterando 'finalizado' para true
    public void finalizarServico(){
        if (!isFinalizado()){
            setFinalizado(true);
            horaFim = LocalTime.now();
        }
    }

//    toString
    @Override
    public String toString() {
        return "ServicoAtribuido{" +
                "cliente=" + cliente +
                ", horaInicio=" + horaInicio +
                ", horaFim=" + horaFim +
                ", finalizado=" + finalizado +
                ", avaliacao=" + avaliacao +
                '}';
    }
}
