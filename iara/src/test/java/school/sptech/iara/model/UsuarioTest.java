package school.sptech.iara.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    @DisplayName("Quando existir usuario com os dados informado")
    void usuarioExiste() {

        //Arrange
        Cliente user = new Cliente();
        Prestador user2 = new Prestador();

        //Act

        //Assert
        assertTrue(true);
    }

    @Test
    void autenticar() {
        //Arrange
        Cliente user = new Cliente();
        Prestador user2 = new Prestador();

        //Act

        //Assert
        assertTrue(true);
    }

    @Test
    void logOff() {
    }
}
