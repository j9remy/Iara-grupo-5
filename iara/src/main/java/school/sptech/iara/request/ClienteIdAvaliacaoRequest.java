package school.sptech.iara.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteIdAvaliacaoRequest {
    @NotNull
    private Integer id;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer avaliacao;

    public Integer getId() {
        return id;
    }
    public Integer getAvaliacao() {
        return avaliacao;
    }
}
