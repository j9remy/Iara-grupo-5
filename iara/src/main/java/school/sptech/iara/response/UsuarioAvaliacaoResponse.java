package school.sptech.iara.response;

import org.jetbrains.annotations.NotNull;
import school.sptech.iara.model.Usuario;

public class UsuarioAvaliacaoResponse {
    @NotNull
    private Integer idCliente;
    private Double avaliacao;

    public UsuarioAvaliacaoResponse(@NotNull Integer idCliente, Double avaliacao) {
        this.idCliente = idCliente;
        this.avaliacao = avaliacao;
    }

    public Integer getUser() {
        return idCliente;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }
}
