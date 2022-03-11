package com.iara.backend.iarabackend.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prestador extends Usuario{
    private String resumo;
    private List<Habilidade> habilidades;
    private List<Servico> servicos;

    public Prestador(String nome, String sobreNome, String cpf, LocalDate dataNasc, String email, String senha, char sexo,
                     String telefone, Endereco endereco, String resumo) {
        super(nome, sobreNome, cpf, dataNasc, email, senha, sexo, telefone, endereco);
        this.resumo = resumo;
        habilidades = new ArrayList<>();
        servicos = new ArrayList<>();
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public void addHabilidade(Habilidade habilidade){
        for(Habilidade hab : habilidades){
            if (habilidades.equals(hab)){
                return;
            }
        }
        habilidades.add(habilidade);
    }

    public void addServico(Servico servico){
        for(Servico ser : servicos){
            if (servicos.equals(ser)){
                return;
            }
        }
        servicos.add(servico);
    }

    @Override
    public boolean autenticar(String email, String senha) {
        if (getEmail().equals(email) && this.senha.equals(senha)){
            return true;
        }
        return false;
    }

    
}
