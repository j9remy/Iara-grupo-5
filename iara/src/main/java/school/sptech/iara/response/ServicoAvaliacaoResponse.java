package school.sptech.iara.response;

public class ServicoAvaliacaoResponse {
    private Integer servicoId;
    private Double avaliacao;

    public ServicoAvaliacaoResponse(Integer servicoId, Double avaliacao) {
        this.servicoId = servicoId;
        this.avaliacao = avaliacao;
    }

    public Integer getServicoId() {
        return servicoId;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }
}
