package school.sptech.iara.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HabilidadeTest {

//    @Test
//    @DisplayName("buscar habilidade existente com sucesso")
//    void getHabilidadeBuscarHabilidadeExistenteComRetornoExistente() {
//        Faker faker = new Faker();
//        Habilidade corte = new Habilidade("Corte", "Corte uni-sex");
//
//        assertEquals(corte, corte);
//    }
//
//    @Test
//    @DisplayName("setar nova habilidade com sucesso")
//    void setHabilidadeNovaHabilidadeComSucesso() {
//        Habilidade habilidade = new Habilidade();
//
//        habilidade.setHabilidade("Corte");
//
//        assertEquals("Corte", habilidade.getHabilidade());
//    }


//    @Test
//    @DisplayName("buscar descrição existente com sucesso")
//    void getDescricaoBuscarDescricaoExistenteComRetornoExistente() {
//        Habilidade habilidade = new Habilidade("Corte", "Corte uni-sex");
//
//        assertEquals("Corte uni-sex", habilidade.getDescricao());
//    }


    @Test
    @DisplayName("setar nova descrição com sucesso")
    void setDescricao() {
        Habilidade habilidade = new Habilidade();

        habilidade.setDescricao("Corte uni-sex");

        assertEquals("Corte uni-sex", habilidade.getDescricao());
    }
}