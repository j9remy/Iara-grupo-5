package school.sptech.iara.response;

public class PrestadorAvaliacaoResponse {
    private Integer prestadorId;
    private Double avaliacao;

    public PrestadorAvaliacaoResponse(Integer prestadorId, Double avaliacao) {
        this.prestadorId = prestadorId;
        this.avaliacao = avaliacao;
    }

    public Integer getPrestadorId() {
        return prestadorId;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }
}
