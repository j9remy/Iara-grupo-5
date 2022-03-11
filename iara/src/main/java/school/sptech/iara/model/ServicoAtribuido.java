package school.sptech.iara.model;

import java.time.LocalTime;

public class ServicoAtribuido implements Avaliavel{
//    Attributes
    private Cliente cliente;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private boolean finalizado;
    private double avaliacao;

//    Constructor
    public ServicoAtribuido(Cliente cliente) {
        this.cliente = cliente;
        this.horaInicio = LocalTime.now();
        horaFim = horaInicio;
        finalizado = false;
        avaliacao = 0d;
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
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    //    Methods
    @Override
    public double getAvaliacao() {
        return avaliacao;
    }

    public void finalizarServico(){
        if (!isFinalizado()){
            setFinalizado(true);
            horaFim = LocalTime.now();
        }
    }


}
