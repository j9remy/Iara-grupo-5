package school.sptech.iara.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MensagemTest {

    @Test
    @DisplayName("getVisualizado com retorno true")
    void getVisualizadocomRetornoTrue() {
        Mensagem mensagem = new Mensagem();

        mensagem.setVisualizado(true);

        assertTrue(mensagem.getVisualizado());
    }

    @Test
    @DisplayName("getVisualizado com retorno false")
    void getVisualizadocomRetornoFalse() {
        Mensagem mensagem = new Mensagem();

        mensagem.setVisualizado(false);

        assertFalse(mensagem.getVisualizado());
    }

    @Test
    @DisplayName("setVisualizado setando false")
    void setVisualizadoSetandoFalse() {
        Mensagem mensagem = new Mensagem();

        mensagem.setVisualizado(false);

        assertFalse(mensagem.getVisualizado());
    }
    @Test
    @DisplayName("setVisualizado setando True")
    void setVisualizadoSetandoTrue() {
        Mensagem mensagem = new Mensagem();

        mensagem.setVisualizado(true);

        assertTrue(mensagem.getVisualizado());
    }

}