package school.sptech.iara.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prestador extends Usuario{
//    Attributes
    private String resumo;
    private List<Habilidade> habilidades;
    private List<Servico> servicos;
    private List<Integer> avaliacoes;

//    Constructor
    public Prestador(String nome, String sobrenome,
                     String cpf, LocalDate dataNasc,
                     String email, String senha,
                     char sexo, String telefone,
                     Endereco endereco, String resumo) {
        super(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone, endereco);
        this.resumo = resumo;
    }

//    Getter and Setter
    public String getDescricao() {
        return resumo;
    }
    public void setDescricao(String resumo) {
        this.resumo = resumo;
    }

//    Methods
    @Override
    // Soma o resultado de todos getAvaliacao() da classe Servico e divide pelo total de ServicosAtribuidos
    public double getAvaliacao(){
        Double somaAvaliacoes = 0d;
        Integer qtdAvaliacoes = 0;
        for (Servico ser: servicos) {
            somaAvaliacoes += ser.getAvaliacao();
            qtdAvaliacoes += ser.getQtdServicosAtribuidos();
        }
        return somaAvaliacoes/qtdAvaliacoes;
    }

    //Se habilidade não existir na lista, adiciona
    public void addHabilidade(Habilidade habilidade){
        for (Habilidade hab : habilidades) {
            if (hab.equals(habilidade)){
                return;
            }
        }
        habilidades.add(habilidade);
    }

    //Se servico não existir na lista, adiciona
    public void addServico(Servico servico){
        for (Servico ser : servicos) {
            if (ser.equals(servico)){
                return;
            }
        }
        servicos.add(servico);
    }

    //retorna todos serviços ativos
    public List<Servico> listarServicosAtivos(){
        List<Servico> servicosAtivos = new ArrayList<>();
        for (Servico serv: servicos) {
            if (serv.isAtivo()){
                servicosAtivos.add(serv);
            }
        }
        return servicosAtivos;
    }
}
