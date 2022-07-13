package school.sptech.iara.response;

import java.time.LocalTime;

public class ServicoResponse {
    private Integer id;
    private Double valor;
    private String descricao;
    private String tipo;
    private LocalTime duracaoEstimada;
    private Integer prestador;
    private Double avaliacao;
    private Integer qtdServicosAvaliados;

    public ServicoResponse(Integer id, Double valor, String descricao, String tipo, LocalTime duracaoEstimada, Integer prestador, Double avaliacao, Integer qtdServicosAvaliados) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
        this.duracaoEstimada = duracaoEstimada;
        this.prestador = prestador;
        this.avaliacao = avaliacao;
        this.qtdServicosAvaliados = qtdServicosAvaliados;
    }

    public Integer getId() {
        return id;
    }
    public Double getValor() {
        return valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getTipo() {
        return tipo;
    }
    public LocalTime getDuracaoEstimada() {
        return duracaoEstimada;
    }
    public Integer getPrestador() {
        return prestador;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }
    public Integer getQtdServicosAvaliados() {
        return qtdServicosAvaliados;
    }
}
