package school.sptech.iara.model;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Usuario {
//    Attributes
    private List<Integer> avaliacoes;

//    Constructor
    public Cliente(String nome, String sobrenome,
                   String cpf, LocalDate dataNasc,
                   String email, String senha,
                   char sexo, String telefone,
                   Endereco endereco) {
        super(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone, endereco);
    }

//    Methods
    public void addAvaliacao(Integer num) {
        avaliacoes.add(num);
    }

    @Override
    public double getAvaliacao(){
        Double somaAvaliacoes = 0d;
        for (Integer avaliacao : avaliacoes) {
            somaAvaliacoes += avaliacao;
        }
        return somaAvaliacoes/avaliacoes.size();
    }
}
