package school.sptech.iara.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
public class ServicoAtribuido implements Avaliavel{
//    Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Cliente cliente;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String observacoes;
    private String status;
    private boolean finalizado;
    private Double avaliacao;
    @ManyToOne
    private Servico servico;
//    @OneToOne
//    private Chat chat;

//    Constructor
    public ServicoAtribuido(Servico servico,
                            LocalDateTime horaInicio,
                            Cliente cliente
                            ) {
        this.cliente = cliente;
        this.dataHoraInicio = horaInicio;
//        dataHoraFim = dataHoraInicio + servico.getDuracaoEstimada();
        this.status = "Agendado";
        this.finalizado = false;
        this.avaliacao = -1.0;
        this.servico = servico;
//        this.chat = chat;
    }
    public ServicoAtribuido(Servico servico,
                            LocalDateTime horaInicio,
                            Cliente cliente,
                            String observacoes) {
        this.cliente = cliente;
        this.dataHoraInicio = horaInicio;
//        dataHoraFim = dataHoraInicio + servico.getDuracaoEstimada();
        this.observacoes = observacoes;
        this.status = "Agendado";
        this.finalizado = false;
        this.avaliacao = -1.0;
        this.servico = servico;
//        this.chat = chat;
    }
    public ServicoAtribuido(){}

//    Getter and Setter
    public LocalDateTime getHoraInicio() {
        return dataHoraInicio;
    }
    public void setHoraInicio(LocalDateTime horaInicio) {
        this.dataHoraInicio = horaInicio;
    }
    public boolean isFinalizado() {
        return finalizado;
    }
    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    public LocalDateTime getHoraFim() {
        return dataHoraFim;
    }
    public void setHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
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
    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }
    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }
    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
    public Servico getServico() {
        return servico;
    }
    public void setServico(Servico servico) {
        this.servico = servico;
    }

    //    Methods
    // Simplesmente retorna o valor do atributo avaliacao
    @Override
    public double calcAvaliacao() {
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
            dataHoraFim = LocalDateTime.now();
        }
    }

//    toString
    @Override
    public String toString() {
        return "ServicoAtribuido{" +
                ", horaInicio=" + dataHoraInicio +
                ", horaFim=" + dataHoraFim +
                ", finalizado=" + finalizado +
                ", avaliacao=" + avaliacao +
                '}';
    }
}
