package school.sptech.iara.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Prestador extends Usuario{
//    Attributes
    private String resumo;

    @NotNull
    private Boolean atendeDomicilio;

    @NotNull
    private Boolean atendeEstabelecimento;

    private Double distancia;

    @OneToMany
    private List<Habilidade> habilidades;

    @OneToMany
    private List<Servico> servicos;

//    Constructor
    public Prestador(String nome, String sobrenome,
                     String cpf, LocalDate dataNasc,
                     String email, String senha,
                     char genero, String telefone,
                     String resumo,
                     Boolean atendeDomicilio,
                     Boolean atendeEstabelecimento,
                     Double distancia) {
        super(nome, sobrenome, cpf, dataNasc, email, senha, genero, telefone);
        this.resumo = resumo;
        this.atendeDomicilio = atendeDomicilio;
        this.atendeEstabelecimento = atendeEstabelecimento;
        this.distancia = distancia;
        habilidades = new ArrayList<>();
        servicos = new ArrayList<>();
    }

    public Prestador(String nome, String sobrenome,
                     String cpf, LocalDate dataNasc,
                     String email, String senha, char genero,
                     String telefone,
                     Boolean atendeDomicilio,
                     Boolean atendeEstabelecimento,
                     Double distancia) {
        super(nome, sobrenome, cpf, dataNasc, email, senha, genero, telefone);
        this.atendeDomicilio = atendeDomicilio;
        this.atendeEstabelecimento = atendeEstabelecimento;
        this.distancia = distancia;
        habilidades = new ArrayList<>();
//        servicos = new ArrayList<>();
    }


    //    Methods
    @Override
    // Soma o resultado de todos getAvaliacao() da classe Servico e divide pelo total de ServicosAtribuidos
    public double calcAvaliacao(){
        Double somaAvaliacoes = 0d;
        Integer qtdAvaliacoes = 0;

//        for (Servico serv: getServicos()) {
//            for (ServicoAtribuido servAttr : serv.getServicoAtribuidos()){
//                if (Objects.nonNull(servAttr.getAvaliacao())){
//                    somaAvaliacoes += serv.getAvaliacao();
//                }
//            }
//            qtdAvaliacoes += serv.getQtdServicosAvaliados();
//        }

        return somaAvaliacoes/qtdAvaliacoes;
    }

//  Adiciona nova habilidade
    public void addHabilidade(Habilidade habilidade){
        habilidades.add(habilidade);
    }

    //Remove habilidade
    public void removeHabilidade(Habilidade habilidade) {
        habilidades.remove(habilidade);
    }

    public Boolean habilidadeExiste(Habilidade habilidade){
        for (int i = 0; i < habilidades.size(); i++) {
            if (habilidades.get(i).getHabilidade().equals(habilidade.getHabilidade()) &&
                    habilidades.get(i).getDescricao().equals(habilidade.getDescricao())){
                return true;
            }
        }
        return false;
    }

    //Se servico não existir na lista, adiciona
    public void addServico(Servico servico){
        servicos.add(servico);
    }

    public Boolean servicoExiste(Servico servico){
        for (Servico ser : servicos) {
            if (ser.getDescricao().equals(servico.getDescricao()) &&
                    ser.getTipo().equals(servico.getTipo())){
                return true;
            }
        }
        return false;
    }

    //retorna todos serviços ativos
    public List<Servico> getServicosAtivos(){
        List<Servico> servicosAtivos = new ArrayList<>();
        for (Servico serv: servicos) {
            if (serv.isAtivo()){
                servicosAtivos.add(serv);
            }
        }
        return servicosAtivos;
    }
}
