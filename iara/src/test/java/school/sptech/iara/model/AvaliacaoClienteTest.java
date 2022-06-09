package school.sptech.iara.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Timestamp;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoClienteTest {

    @Test
    @DisplayName("Buscar avaliação existente com sucesso")
    void getAvaliacaoBuscarAvaliacaoExistente() {
        Faker faker = new Faker();
        String nome = faker.name().firstName() ;
        String sobrenome = faker.name().lastName();
        String cpf = faker.random().toString();
        String email = faker.internet().emailAddress();
        String senha = faker.lorem().word();
        char sexo = faker.lorem().character();
        String telefone = String.valueOf(faker.phoneNumber());
        
        Cliente user = new Cliente(nome, sobrenome, cpf, LocalDate.now(), email, senha, sexo, telefone);
        AvaliacaoCliente avaliacao = new AvaliacaoCliente(6d, user);

        assertEquals(avaliacao, avaliacao);
    }

    @Test
    @DisplayName("Setar avaliação existente com sucesso")
    void setAvaliacaoNovaAvaliacaoComSucesso() {
        AvaliacaoCliente avaliacao = new AvaliacaoCliente();

        avaliacao.setAvaliacao(6d);

        assertEquals(6, avaliacao.getAvaliacao());
    }

}
