package com.iara.backend.iarabackend.models;

import java.time.LocalTime;

public class ServicoAtribuido {
    private Cliente cliente;
    private Servico servico;
    private LocalTime horaInicio;
    private Boolean finalizado;

    

    public ServicoAtribuido(Cliente cliente, Servico servico, LocalTime horaInicio) {
        this.cliente = cliente;
        this.servico = servico;
        this.horaInicio = horaInicio;
        this.finalizado = false;
    }

    public void finalizarServico(){
        //Code must be here
    }

    public void cancelarServico(){
        //ur code must be here
    }

    public void atualizarServico(){
        // code must be here
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Servico getServico() {
        return servico;
    }
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    public Boolean getFinalizado() {
        return finalizado;
    }
    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    
}
