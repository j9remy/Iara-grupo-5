package school.sptech.iara.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class PrestadorTest {

    @Test
    @DisplayName("Quando as credenciais informadas pelo prestador derem match com os dados correspondetes deve existir usuario")
    void usuarioPrestadorExisteTest() {

        //Arrange
//        Faker faker = new Faker();
//        String nome = faker.name().firstName() ;
//        String sobrenome = faker.name().lastName();
//        String cpf = faker.random().toString();
//        String email = faker.internet().emailAddress();
//        String senha = faker.lorem().word();
//        char sexo = faker.lorem().character();
//        String telefone = String.valueOf(faker.phoneNumber());
//        String resumo = faker.lorem().paragraph(4);
//        Boolean atendeDomicilio = faker.random().nextBoolean();
//        Boolean atendeEstabelecimento = faker.random().nextBoolean();
//        Double distancia = faker.random().nextDouble();
//
//        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
//        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
//        long diff = end - offset + 1;
//        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));
//
//        Prestador user = new Prestador(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone,resumo,
//            atendeDomicilio,atendeEstabelecimento, distancia);
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
    @DisplayName("Quando as credenciais informadas pelo prestador NÃO derem match com os dados correspondetes NÃO deve existir usuario")
    void usuarioPrestadorNaoExisteTest() {
//
//        //Arrange
//        Faker faker = new Faker();
//        String nome = faker.name().firstName() ;
//        String sobrenome = faker.name().lastName();
//        String cpf = faker.random().toString();
//        String email = faker.internet().emailAddress();
//        String senha = faker.lorem().word();
//        char sexo = faker.lorem().character();
//        String telefone = String.valueOf(faker.phoneNumber());
//        String resumo = faker.lorem().paragraph(4);
//        Boolean atendeDomicilio = faker.random().nextBoolean();
//        Boolean atendeEstabelecimento = faker.random().nextBoolean();
//        Double distancia = faker.random().nextDouble();
//
//        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
//        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
//        long diff = end - offset + 1;
//        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));
//
//        Prestador user = new Prestador(nome, sobrenome, cpf, dataNasc, email, senha, sexo, telefone,resumo,
//            atendeDomicilio,atendeEstabelecimento,distancia);
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
    @DisplayName("Um Prestador deve ser um tipo de Usuario ou seja deve herdar de Usuario")
    void prestadorDeveSerUmTipoDerivadoDeUsuarioTest(){
        //Arrange
        Prestador prestador;

        //Act
        prestador = new Prestador();

        //Assert
        assertInstanceOf(Usuario.class,prestador);

    }

    @Test
    @DisplayName("CalcAvaliacao deve ser NAN caso não tenha avaliacoes")
    void calcAvaliacaoDeveSerNaN(){
        //Arrange
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));

        Prestador prestador = new Prestador(
            new Faker().name().firstName(), new Faker().name().lastName(),
            new Faker().lorem().word(),dataNasc,
            new Faker().internet().emailAddress(),new Faker().internet().password(),
            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
        );
        Servico servico = new Servico(1,new Faker().lorem().word(),new Faker().lorem().word(),new Faker().random().nextDouble() );

        //Act
        double avaliacao = prestador.calcAvaliacao();
        prestador.addServico(servico);

        //Assert
        assertEquals(Double.NaN,avaliacao);

    }

    @Test
    @DisplayName("CalcAvaliacao deve ser soma das Avaliacoes divididas pela quantidade de avaliacoes")
    void calcAvaliacaoDeveSomarAvaliacoesEDividirEsseValorPelaQuantidadeDeAvaliacoes(){
        //Arrange
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));

        Prestador prestador = new Prestador(
            new Faker().name().firstName(), new Faker().name().lastName(),
            new Faker().lorem().word(),dataNasc,
            new Faker().internet().emailAddress(),new Faker().internet().password(),
            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
        );

        Servico servico = new Servico(1,new Faker().lorem().word(),new Faker().lorem().word(),new Faker().random().nextDouble() );

        ServicoAtribuido servicoAtribuido = new ServicoAtribuido();
        //Act
        double avaliacao = prestador.calcAvaliacao();
        servicoAtribuido.addAvaliacao(5);

        servico.addServicoAtribuido(servicoAtribuido);
        prestador.addServico(servico);

        double somaDasAvaliacoes = 0;
        double somaServicoesAvaliados = 0;
        for (Servico s : prestador.getServicosAtivos()) {
            somaDasAvaliacoes += s.getAvaliacao();
            somaServicoesAvaliados = s.getQtdServicosAvaliados();

        }

        double avaliacaoEsperada = somaDasAvaliacoes/somaServicoesAvaliados;


        //Assert
        assertEquals(avaliacaoEsperada,avaliacao);

    }


    @Test
    @DisplayName("CalcAvaliacao deve lançar um erro caso tenha como divisor valor zero")
    void calcAvaliacaoDeveLancarUmaExcecaoQuandoHouverDivisorZero(){
        //Arrange
        Prestador prestador = new Prestador();

        //Act
        var thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
            prestador.calcAvaliacao();
        });

        //Assert
        assertThrows(ArithmeticException.class , (Executable) thrown);

    }


    @Test
    @DisplayName("Quando habilidade adicionada deve haver uma habilidade correspondente na lista de habilidade do prestador")
    void addHabilidadeDeveAdicionarHabilidadeNaListaDeHabilidadesDoPrestador(){

        //Arrange
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));

        Prestador prestador = new Prestador(
            new Faker().name().firstName(), new Faker().name().lastName(),
            new Faker().lorem().word(),dataNasc,
            new Faker().internet().emailAddress(),new Faker().internet().password(),
            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
        );

        Habilidade [] habilidades = new Habilidade[]{
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote())
        };

        //Act
        for (Habilidade hab : habilidades)
            prestador.addHabilidade(hab);

        //Assert
        for (Habilidade hab : habilidades)
            assertTrue(prestador.getHabilidades().contains(hab));
    }

    @Test
    @DisplayName("Quando habilidade adicionada deve haver uma habilidade correspondente na lista de habilidade do prestador")
    void quandoHabilidadeAdicionadaNaListaDeHabilidadeDeveExistirHabilidadeNaListaDeHabilidadesDoPrestador(){

        //Arrange
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));

        Prestador prestador = new Prestador(
            new Faker().name().firstName(), new Faker().name().lastName(),
            new Faker().lorem().word(),dataNasc,
            new Faker().internet().emailAddress(),new Faker().internet().password(),
            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
        );

        Habilidade [] habilidades = new Habilidade[]{
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote())
        };

        Habilidade [] habilidadesQueNaoSeraoAdicionadas = new Habilidade[]{
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote())
        };

        //Act
        for (Habilidade hab : habilidades)
            prestador.addHabilidade(hab);

        //Assert
        for (Habilidade hab : habilidades)
            assertTrue(prestador.habilidadeExiste(hab));

        for (Habilidade hab : habilidadesQueNaoSeraoAdicionadas)
            assertFalse(prestador.habilidadeExiste(hab));
    }


    @Test
    @DisplayName("Quando servico adicionado deve haver um servico correspondente na lista de servicos do prestador")
    void addServicoDeveAdicionarServicoNaListaDeServicosDoPrestador(){

        //Arrange
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));

        Prestador prestador = new Prestador(
            new Faker().name().firstName(), new Faker().name().lastName(),
            new Faker().lorem().word(),dataNasc,
            new Faker().internet().emailAddress(),new Faker().internet().password(),
            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
        );

        Servico [] servicos = new Servico[]{
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble())
        };

        //Act
        for (Servico servico : servicos)
            prestador.addServico(servico);

        //Assert
        for (Servico servico : servicos)
            assertTrue(prestador.getServicosAtivos().contains(servico));
    }


    @Test
    @DisplayName("Quando habilidade adicionada deve haver uma habilidade correspondente na lista de habilidade do prestador")
    void quandoServicoAdicionadoNaListaDeServicosDeveExistirServicoCorrespondenteNaListaDeServicosDoPrestador(){

        //Arrange
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp dataNasc = new Timestamp(offset + (long)(Math.random() * diff));

        Prestador prestador = new Prestador(
            new Faker().name().firstName(), new Faker().name().lastName(),
            new Faker().lorem().word(),dataNasc,
            new Faker().internet().emailAddress(),new Faker().internet().password(),
            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
        );

        Habilidade [] habilidades = new Habilidade[]{
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote())
        };

        Servico [] servicos = new Servico[]{
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble())
        };

        Servico [] servicosQueNaoSeraoAdicionados = new Servico[]{
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble())
        };


        //Act
        for (Servico servico : servicos)
            prestador.addServico(servico);

        //Assert
        for (Servico servico : servicos)
            assertTrue(prestador.servicoExiste(servico));

        for (Servico servico : servicosQueNaoSeraoAdicionados)
            assertFalse(prestador.servicoExiste(servico));
    }


}
