package school.sptech.iara.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrestadorTest {

    @Test
    @DisplayName("Quando as credenciais informadas pelo prestador derem match com os dados correspondetes deve existir usuario")
    void usuarioPrestadorExiste() {

        //Arrange
        Faker faker = new Faker();
        String nome = faker.name().firstName() ;
        String sobrenome = faker.name().lastName();
        String cpf = faker.random().toString();
        String email = faker.internet().emailAddress();
        String senha = faker.lorem().word();
        char sexo = faker.lorem().character();
        String telefone = String.valueOf(faker.phoneNumber());
        String resumo = faker.lorem().paragraph(4);
        Boolean atendeDomicilio = faker.random().nextBoolean();

        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));

        Prestador user = new Prestador(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone,resumo,
            atendeDomicilio);

        String [] credenciais = new String[] {email,senha};

        //Act
        var existe = user.usuarioExiste(credenciais[0],credenciais[1]);

        //Assert
        assertTrue(existe);
    }

    @Test
    @DisplayName("Quando as credenciais informadas pelo prestador NÃO derem match com os dados correspondetes NÃO deve existir usuario")
    void usuarioPrestadorNaoExiste() {

        //Arrange
        Faker faker = new Faker();
        String nome = faker.name().firstName() ;
        String sobrenome = faker.name().lastName();
        String cpf = faker.random().toString();
        String email = faker.internet().emailAddress();
        String senha = faker.lorem().word();
        char sexo = faker.lorem().character();
        String telefone = String.valueOf(faker.phoneNumber());
        String resumo = faker.lorem().paragraph(4);
        Boolean atendeDomicilio = faker.random().nextBoolean();

        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));

        Prestador user = new Prestador(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone,resumo,
            atendeDomicilio);

        String [] credenciais = new String[] {faker.internet().emailAddress(),faker.lorem().word()};

        //Act
        var existe = user.usuarioExiste(credenciais[0],credenciais[1]);

        //Assert
        assertFalse(existe);
    }
}
