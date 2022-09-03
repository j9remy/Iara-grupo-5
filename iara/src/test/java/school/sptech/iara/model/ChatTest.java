package school.sptech.iara.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    @Test
    @DisplayName("getFinalizado com retorno de busca sendo true")
    void getFinalizadoComRetornoDeBuscaSendoTrue() {
        Chat chat = new Chat();
        chat.setFinalizado(true);
        assertTrue(chat.getFinalizado());
    }

    @Test
    @DisplayName("getFinalizado com o retorno false")
    void getFinalizadoComRetornoDeBuscaSendoFalse() {
        Chat chat = new Chat();
        chat.setFinalizado(false);
        assertFalse(chat.getFinalizado());
    }

    @Test
    @DisplayName("setFinalizado setando true")
    void setFinalizadoComSetandoTrue() {
        Chat chat = new Chat();
        chat.setFinalizado(true);
        assertEquals(true,chat.getFinalizado());
    }

    @Test
    @DisplayName("setFinalizado com retorno false")
    void setFinalizadoComSentandoFalse() {
        Chat chat = new Chat();
        chat.setFinalizado(false);
        assertEquals(false,chat.getFinalizado());
    }

//    @Test
//    void finalizar() {
//        Chat chat = new Chat();
//
//        chat.finalizar();
//        assertEquals(true, chat.getFinalizado());
//    }
}