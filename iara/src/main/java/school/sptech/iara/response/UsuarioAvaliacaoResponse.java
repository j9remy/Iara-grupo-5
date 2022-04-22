package school.sptech.iara.response;

import org.jetbrains.annotations.NotNull;
import school.sptech.iara.model.Usuario;

public class UsuarioAvaliacaoResponse {
    @NotNull
    private Usuario user;
    private Double avaliacao;

    public UsuarioAvaliacaoResponse(@NotNull Usuario user, Double avaliacao) {
        this.user = user;
        this.avaliacao = avaliacao;
    }

    public Usuario getUser() {
        return user;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }
}
