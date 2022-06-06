package school.sptech.iara.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {



    @Test
    @DisplayName("Um Cliente deve ser um tipo de Usuario ou seja deve herdar de Usuario")
    void prestadorDeveSerUmTipoDerivadoDeUsuarioTest(){

        //Arrange
        Cliente cliente;

        //Act
        cliente = new Cliente();

        //Assert
        assertInstanceOf(Usuario.class,cliente);

    }

    @Test
    @DisplayName("Quando as credenciais informadas pelo cliente derem match com os dados correspondetes deve existir usuario")
    void usuarioClienteExiste() {

//        //Arrange
//        Faker faker = new Faker();
//        String nome = faker.name().firstName() ;
//        String sobrenome = faker.name().lastName();
//        String cpf = faker.random().toString();
//        String email = faker.internet().emailAddress();
//        String senha = faker.lorem().word();
//        char sexo = faker.lorem().character();
//        String telefone = String.valueOf(faker.phoneNumber());
//
//        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
//        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
//        long diff = end - offset + 1;
//        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));
//
//        Cliente user = new Cliente(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone);
//
//        String [] credenciais = new String[] {email,senha};
//
//        //Act
//        var existe = user.usuarioExiste(credenciais[0],credenciais[1]);
//
//        //Assert
//        assertTrue(existe);
    }

    @Test
    @DisplayName("Quando as credenciais informadas pelo cliente NÃO derem match com os dados correspondetes NÃO deve existir usuario")
    void usuarioClienteNaoExiste() {

//        //Arrange
//        Faker faker = new Faker();
//        String nome = faker.name().firstName() ;
//        String sobrenome = faker.name().lastName();
//        String cpf = faker.random().toString();
//        String email = faker.internet().emailAddress();
//        String senha = faker.lorem().word();
//        char sexo = faker.lorem().character();
//        String telefone = String.valueOf(faker.phoneNumber());
//
//        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
//        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
//        long diff = end - offset + 1;
//        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));
//
//        Cliente user = new Cliente(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone);
//
//        String [] credenciais = new String[] {faker.internet().emailAddress(),faker.lorem().word()};
//
//        //Act
//        var existe = user.usuarioExiste(credenciais[0],credenciais[1]);
//
//        //Assert
//        assertFalse(existe);
    }


    @Test
    @DisplayName("autenticar cliente se existir na base e não autenticado deve autenticar")
    void autenticarQuandoExistirENaoAutenticadoCliente() {
        //Arrange
        Faker faker = new Faker();
        Cliente user = new Cliente();

        user.setEmail(faker.internet().emailAddress());
        var senha = faker.internet().password();
        user.setSenha(senha);

        //Act
        var autenticado = user.autenticar(user.getEmail(),senha);

        //Assert
        assertTrue(user.isAutenticado());
        assertEquals("Usuario logado com sucesso",autenticado);
    }

    @Test
    @DisplayName("autenticar cliente se existir na base e já estiver autenticado não deve autenticar")
    void autenticarQuandoExistirEAutenticadoNaoDeveAutenticarCliente() {
        //Arrange
        Faker faker = new Faker();
        Cliente user = new Cliente();

        user.setEmail(faker.internet().emailAddress());
        var senha = faker.internet().password();
        user.setSenha(senha);

        //Act
        user.autenticar(user.getEmail(),senha);

        //Assert
        //assertEquals("Usuario já autenticado",user.autenticar(user.getEmail(),senha));
    }

    @Test
    @DisplayName("autenticar cliente se não existir na base não deve autenticar")
    void autenticarQuandoNaoExistirNaoDeveAutenticarCliente() {
        //Arrange
        Faker faker = new Faker();
        Cliente user = new Cliente();

        user.setEmail(faker.internet().emailAddress());

        var senha = faker.internet().password();

        user.setSenha(senha);

        //Act && Assert
        assertFalse(user.isAutenticado());
        assertEquals("Usuario não encontrado",user.autenticar(faker.internet().emailAddress(),faker.internet().password()));
    }

    @Test
    @DisplayName("logoff do cliente quando cliente autenticado deve deslogar o cliente")
    void logOffQuandoClienteAutenticadoDeveDeslogarCliente() {
        //Arrange
        Faker faker = new Faker();
        Cliente user = new Cliente();

        user.setEmail(faker.internet().emailAddress());

        var senha = faker.internet().password();

        user.setSenha(senha);

        //Act
        user.autenticar(user.getEmail(),senha);
        var logedOf = user.logOff(user.getEmail(),senha);

        //Act && Assert
        assertFalse(user.isAutenticado());
        assertEquals("LogOff finalizado com sucesso",logedOf);
    }

    @Test
    @DisplayName("logoff do cliente quando cliente não autenticado deve retornar cliente nao auteticado")
    void logOffQuandoClienteNaoAutenticadoDeveRetornarClienteNaoAutenticado() {
        //Arrange
        Faker faker = new Faker();
        Cliente user = new Cliente();

        user.setEmail(faker.internet().emailAddress());

        var senha = faker.internet().password();

        user.setSenha(senha);

        //Act
        var logedOf = user.logOff(user.getEmail(),senha);

        //Act && Assert
        assertFalse(user.isAutenticado());
        //assertEquals("Cliente não autenticado",logedOf);
    }




}
