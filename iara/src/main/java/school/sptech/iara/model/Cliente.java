package school.sptech.iara.model;

import school.sptech.iara.util.Lista;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Usuario {
//    Attributes
    @OneToMany
    private List<AvaliacaoCliente> avaliacoes;
//    Constructor
    public Cliente(){
        super();
    }
    public Cliente(String nome, String sobrenome,
                   String cpf, Timestamp dataNasc,
                   String email, String senha,
                   char sexo, String telefone) {
        super(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone);
        avaliacoes = new ArrayList<>();
    }

    //    Methods
    // Adiciona na lista de avaliações um número entre 0 e 5
    public void addAvaliacao(Integer num) {
        if (num >= 0 && num <= 5){
            avaliacoes.add(new AvaliacaoCliente(num));
        }
    }
    @Override
    // Retourna a média de todas avaliações na lista
    public double calcAvaliacao(){
        Double somaAvaliacoes = 0d;
        for (AvaliacaoCliente avaliacao : avaliacoes) {
            somaAvaliacoes += avaliacao.getAvaliacao();
        }
//        for (int i = 0; i > avaliacoes.size(); i++){
//            somaAvaliacoes += avaliacoes.get(i).getAvaliacao();
//        }
        return somaAvaliacoes/avaliacoes.size();
    }

//    toString

}
