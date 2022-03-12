package school.sptech.iara.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prestador extends Usuario{
//    Attributes
    private String resumo;
    private List<Habilidade> habilidades;
    private List<Servico> servicos;

//    Constructor
    public Prestador(String nome, String sobrenome,
                     String cpf, LocalDate dataNasc,
                     String email, String senha,
                     char sexo, String telefone,
                     Endereco endereco, String resumo) {
        super(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone, endereco);
        this.resumo = resumo;
        habilidades = new ArrayList<>();
        servicos = new ArrayList<>();
    }

//    Getter and Setter
    public String getResumo() {
        return resumo;
    }
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    public List<Habilidade> getHabilidades() {
        return habilidades;
    }
    public List<Servico> getServicos() {
        return servicos;
    }

    //    Methods
    @Override
    // Soma o resultado de todos getAvaliacao() da classe Servico e divide pelo total de ServicosAtribuidos
    public double getAvaliacao(){
        Double somaAvaliacoes = 0d;
        Integer qtdAvaliacoes = 0;

        for (Servico serv: getServicos()) {
            for (ServicoAtribuido servAttr : serv.getServicoAtribuidos()){
                if (Objects.nonNull(servAttr.getAvaliacao())){
                    somaAvaliacoes += serv.getAvaliacao();
                }
            }
            qtdAvaliacoes += serv.getQtdServicosAvaliados();
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
    public List<Servico> getServicosAtivos(){
        List<Servico> servicosAtivos = new ArrayList<>();
        for (Servico serv: servicos) {
            if (serv.isAtivo()){
                servicosAtivos.add(serv);
            }
        }
        return servicosAtivos;
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
                ", resumo=" + getResumo() +
                ", habilidades=" + habilidades +
                ", serviços=" + servicos +
                ", mediaAvaliacao=" + getAvaliacao() +
                '}';
    }
}
