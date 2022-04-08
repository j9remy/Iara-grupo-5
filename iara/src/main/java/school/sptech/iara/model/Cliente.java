package school.sptech.iara.model;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Usuario {
//    Attributes
    private List<Integer> avaliacoes;

//    Constructor
    public Cliente(String nome, String sobrenome,
                   String cpf, String dataNasc,
                   String email, String senha,
                   char sexo, String telefone,
                   Endereco endereco) {
        super(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone, endereco);
        //avaliacoes = new ArrayList<>();
    }

//    Methods
    // Adiciona na lista de avaliações um número entre 0 e 5
    public void addAvaliacao(Integer num) {
        if (num >= 0 && num <= 5){
            avaliacoes.add(num);
        }
    }
    @Override
    // Retourna a média de todas avaliações na lista
    public double getAvaliacao(){
        Double somaAvaliacoes = 0d;
        for (Integer avaliacao : avaliacoes) {
            somaAvaliacoes += avaliacao;
        }
        return somaAvaliacoes/avaliacoes.size();
    }

//    toString
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + getNome() + '\'' +
                ", sobrenome='" + getSobrenome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", dataNasc=" + getDataNasc() +
                ", email='" + getEmail() + '\'' +
                ", sexo=" + getSexo() +
                ", telefone='" + getTelefone() + '\'' +
                ", autenticado=" + isAutenticado() +
                ", endereco=" + getEndereco() +
                ", mediaAvaliacoes=" + getAvaliacao() +
                ", avaliacoes=" + avaliacoes +
                '}';
    }
}
