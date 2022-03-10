package com.iara.backend.iarabackend.models;

public class Habilidade {
   private String habilidade; 
   private String descricao;
   
    public Habilidade(String habilidade, String descricao) {
        this.habilidade = habilidade;
        this.descricao = descricao;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
   
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Habilidade:"+getHabilidade()+"\nDescrição:"+getDescricao();
    }
   
}
