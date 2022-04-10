package school.sptech.iara.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Servico {
//    Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double valor;
    private String descricao;
    private String tipo;
    private boolean ativo;

    @OneToMany
    private List<ServicoAtribuido> servicoAtribuidos;

    @ManyToOne
    private Prestador prestador;

//    Constructor
    public Servico(double valor, String descricao, String tipo) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
        ativo = true;
        servicoAtribuidos = new ArrayList<>();
    }

//    Getter and Setter
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public void setAtivo() {
        setAtivo(!isAtivo());
    }
    public List<ServicoAtribuido> getServicoAtribuidos() {
        return servicoAtribuidos;
    }

//    Methods
    //Retorna a quantidade de serviços atribuidos a este serviço
    public int getQtdServicosAtribuidos(){
        return servicoAtribuidos.size();
    }

    // Retorna a quantidade de serviços avaliados
    public int getQtdServicosAvaliados(){
        int soma = 0;
        for (ServicoAtribuido serv: servicoAtribuidos) {
            if (Objects.nonNull(serv.getAvaliacao())){
                soma++;
            }
        }
        return soma;
    }

    //Retorna média das avaliações dos servicos atribuidos a este
    public double getAvaliacao(){
        Double somaAvaliacoes = 0d;
        for (ServicoAtribuido serv: servicoAtribuidos) {
            Double avaliacao = serv.getAvaliacao();
            if(!avaliacao.isNaN()){
                somaAvaliacoes += serv.getAvaliacao();
            }
        }
        if(!somaAvaliacoes.isNaN()){
            return somaAvaliacoes/servicoAtribuidos.size();
        }else{
            return 0d;
        }
    }

    //retorna a lista de todos os servicos atribuidos serviço
    public List<ServicoAtribuido> listarServicosAtribuidos(){
        return servicoAtribuidos;
    }

    //Se servicoAtribuido não existir na lista, adiciona
    public void addServicoAtribuido(ServicoAtribuido servicoAtribuido){
        for (ServicoAtribuido serv: servicoAtribuidos) {
            if (serv.equals(servicoAtribuido)){
                return;
            }
        }
        servicoAtribuidos.add(servicoAtribuido);
    }

    // Deleta o registro de ServicoAtribuido da lista somente se ele não estiver finalizado ainda
    public void cancelarServicoAtribuido(@NotNull ServicoAtribuido servico){
        if (!servico.isFinalizado()){
            for (ServicoAtribuido serv: servicoAtribuidos) {
                if (servico.equals(serv)){
                    servicoAtribuidos.remove(serv);
                }
            }
        }
    }

    public void desativarServico(){
        boolean existeServicosAtribuidosNaoFinalizados = false;
        for (ServicoAtribuido servAttr: servicoAtribuidos) {
            if (!servAttr.isFinalizado()){
                existeServicosAtribuidosNaoFinalizados = true;
            }
        }
        if (!existeServicosAtribuidosNaoFinalizados){
            setAtivo(false);
        }
    }

    // Altera os valores dos atributos da classe pelos passados por parâmetro, menos o ativo
    public void atualizarServico(double valor, String descricao, String tipo){
        setValor(valor);
        setDescricao(descricao);
        setTipo(tipo);
    }

//    toString
    @Override
    public String toString() {
        return "Servico{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ativo=" + ativo +
                ", servicoAtribuidos=" + servicoAtribuidos +
                '}';
    }
}

