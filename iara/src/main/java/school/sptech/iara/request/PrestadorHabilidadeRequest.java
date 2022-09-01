package school.sptech.iara.request;

import lombok.Data;
import school.sptech.iara.model.Habilidade;

import javax.validation.constraints.NotNull;

@Data
public class PrestadorHabilidadeRequest {

    @NotNull
    private Integer idPrestador;
    @NotNull
    private String Categoria;
    @NotNull
    private String Descricao;
}
