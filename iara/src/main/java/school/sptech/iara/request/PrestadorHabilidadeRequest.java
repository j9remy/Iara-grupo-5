package school.sptech.iara.request;

import school.sptech.iara.model.Habilidade;

import javax.validation.constraints.NotNull;

public class PrestadorHabilidadeRequest {

    @NotNull
    private Integer userId;

    @NotNull
    private Habilidade habilidade;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Habilidade getHabilidade() {
        return habilidade;
    }
    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }
}
