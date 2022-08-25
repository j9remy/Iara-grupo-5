//package school.sptech.iara.model;
//
//import com.github.javafaker.Faker;
//import com.sun.istack.NotNull;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//
//import java.lang.reflect.Field;
//
//import java.time.LocalDate;
//
//import javax.persistence.OneToMany;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PrestadorTest {
//
//    @Test
//    @DisplayName("Um Prestador deve ser um tipo de Usuario ou seja deve herdar de Usuario")
//    void prestadorDeveSerUmTipoDerivadoDeUsuarioTest(){
//        //Arrange
//        Prestador prestador;
//
//        //Act
//        prestador = new Prestador();
//
//        //Assert
//        assertInstanceOf(Usuario.class,prestador);
//
//    }
//
//    @Test
//    @DisplayName("CalcAvaliacao deve ser NAN caso não tenha avaliacoes")
//    void calcAvaliacaoDeveSerNaN(){
//        //Arrange
//
//        Prestador prestador = new Prestador(
//            new Faker().name().firstName(), new Faker().name().lastName(),
//            new Faker().lorem().word(),LocalDate.now(),
//            new Faker().internet().emailAddress(),new Faker().internet().password(),
//            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
//            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
//            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
//        );
//        Servico servico = new Servico(1,new Faker().lorem().word(),new Faker().lorem().word(),new Faker().random().nextDouble() );
//
//        //Act
//        double avaliacao = prestador.calcAvaliacao();
//        prestador.addServico(servico);
//
//        //Assert
//        assertEquals(Double.NaN,avaliacao);
//
//    }
//
//    @Test
//    @DisplayName("CalcAvaliacao deve ser soma das Avaliacoes divididas pela quantidade de avaliacoes")
//    void calcAvaliacaoDeveSomarAvaliacoesEDividirEsseValorPelaQuantidadeDeAvaliacoes(){
//        //Arrange
//
//        Prestador prestador = new Prestador(
//            new Faker().name().firstName(), new Faker().name().lastName(),
//            new Faker().lorem().word(),LocalDate.now(),
//            new Faker().internet().emailAddress(),new Faker().internet().password(),
//            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
//            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
//            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
//        );
//
//        Servico servico = new Servico(1,new Faker().lorem().word(),new Faker().lorem().word(),new Faker().random().nextDouble() );
//
//        ServicoAtribuido servicoAtribuido = new ServicoAtribuido();
//        //Act
//        double avaliacao = prestador.calcAvaliacao();
//        servicoAtribuido.addAvaliacao(5);
//
//        servico.addServicoAtribuido(servicoAtribuido);
//        prestador.addServico(servico);
//
//        double somaDasAvaliacoes = 0;
//        double somaServicoesAvaliados = 0;
//        for (Servico s : prestador.getServicosAtivos()) {
//            somaDasAvaliacoes += s.getAvaliacao();
//            somaServicoesAvaliados = s.getQtdServicosAvaliados();
//
//        }
//
//        double avaliacaoEsperada = somaDasAvaliacoes/somaServicoesAvaliados;
//
//
//        //Assert
//        assertEquals(avaliacaoEsperada,avaliacao);
//
//    }
//
//
//    @Test
//    @DisplayName("CalcAvaliacao deve lançar um erro caso tenha como divisor valor zero")
//    void calcAvaliacaoDeveLancarUmaExcecaoQuandoHouverDivisorZero(){
//        //Arrange
//        Prestador prestador = new Prestador();
//
//        //Act
//        var thrown = Assertions.assertThrows(ArithmeticException.class,
//        () -> {
//            prestador.calcAvaliacao();
//        });
//
//        //Assert
//        assertThrows(ArithmeticException.class , (Executable) thrown);
//
//    }
//
//
//    @Test
//    @DisplayName("Quando habilidade adicionada deve haver uma habilidade correspondente na lista de habilidade do prestador")
//    void addHabilidadeDeveAdicionarHabilidadeNaListaDeHabilidadesDoPrestador(){
//
//        //Arrange
//
//
//        Prestador prestador = new Prestador(
//            new Faker().name().firstName(), new Faker().name().lastName(),
//            new Faker().lorem().word(),LocalDate.now(),
//            new Faker().internet().emailAddress(),new Faker().internet().password(),
//            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
//            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
//            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
//        );
//
//        Habilidade [] habilidades = new Habilidade[]{
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote())
//        };
//
//        //Act
//        for (Habilidade hab : habilidades)
//            prestador.addHabilidade(hab);
//
//        //Assert
//        for (Habilidade hab : habilidades)
//            assertTrue(prestador.getHabilidades().contains(hab));
//    }
//
//    @Test
//    @DisplayName("Quando habilidade adicionada deve haver uma habilidade correspondente na lista de habilidade do prestador")
//    void quandoHabilidadeAdicionadaNaListaDeHabilidadeDeveExistirHabilidadeNaListaDeHabilidadesDoPrestador(){
//
//        //Arrange
//        Prestador prestador = new Prestador(
//            new Faker().name().firstName(), new Faker().name().lastName(),
//            new Faker().lorem().word(),LocalDate.now(),new Faker().internet().emailAddress(),
//            new Faker().internet().password(),
//            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
//            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
//            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
//        );
//
//        Habilidade [] habilidades = new Habilidade[]{
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote())
//        };
//
//        Habilidade [] habilidadesQueNaoSeraoAdicionadas = new Habilidade[]{
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote())
//        };
//
//        //Act
//        for (Habilidade hab : habilidades)
//            prestador.addHabilidade(hab);
//
//        //Assert
//        for (Habilidade hab : habilidades)
//            assertTrue(prestador.habilidadeExiste(hab));
//
//        for (Habilidade hab : habilidadesQueNaoSeraoAdicionadas)
//            assertFalse(prestador.habilidadeExiste(hab));
//    }
//
//
//    @Test
//    @DisplayName("Quando servico adicionado deve haver um servico correspondente na lista de servicos do prestador")
//    void addServicoDeveAdicionarServicoNaListaDeServicosDoPrestador(){
//
//        //Arrange
//        Prestador prestador = new Prestador(
//            new Faker().name().firstName(), new Faker().name().lastName(),
//            new Faker().lorem().word(),LocalDate.now(),
//            new Faker().internet().password(), new Faker().internet().emailAddress(),
//            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
//            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
//            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
//        );
//
//        Servico [] servicos = new Servico[]{
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble())
//        };
//
//        //Act
//        for (Servico servico : servicos)
//            prestador.addServico(servico);
//
//        //Assert
//        for (Servico servico : servicos)
//            assertTrue(prestador.getServicosAtivos().contains(servico));
//    }
//
//
//    @Test
//    @DisplayName("Quando habilidade adicionada deve haver uma habilidade correspondente na lista de habilidade do prestador")
//    void quandoServicoAdicionadoNaListaDeServicosDeveExistirServicoCorrespondenteNaListaDeServicosDoPrestador(){
//
//        //Arrange
//        Prestador prestador = new Prestador(
//            new Faker().name().firstName(), new Faker().name().lastName(),
//            new Faker().lorem().word(),LocalDate.now(),
//            new Faker().internet().password(), new Faker().internet().emailAddress(),
//            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
//            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
//            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
//        );
//
//        Habilidade [] habilidades = new Habilidade[]{
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote()),
//            new Habilidade( new Faker().lorem().word(),new Faker().gameOfThrones().quote())
//        };
//
//        Servico [] servicos = new Servico[]{
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble())
//        };
//
//        Servico [] servicosQueNaoSeraoAdicionados = new Servico[]{
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble()),
//            new Servico( new Faker().random().nextDouble(),new Faker().lorem().word(),new Faker().gameOfThrones().quote(), new Faker().random().nextDouble())
//        };
//
//
//        //Act
//        for (Servico servico : servicos)
//            prestador.addServico(servico);
//
//        //Assert
//        for (Servico servico : servicos)
//            assertTrue(prestador.servicoExiste(servico));
//
//        for (Servico servico : servicosQueNaoSeraoAdicionados)
//            assertFalse(prestador.servicoExiste(servico));
//    }
//
//    @Test
//    @DisplayName("Os campos devem estar com as anotacoes do spring corretas")
//    void camposComAnotacoesDoSpringDevemEstarCorretos() throws NoSuchFieldException, SecurityException{
//
//        Class<Prestador> classe =  Prestador.class;
//
//        Field [] campo = new Field[]{
//            classe.getDeclaredField("atendeDomicilio"),
//            classe.getDeclaredField("atendeEstabelecimento"),
//            classe.getDeclaredField("habilidades"),
//            classe.getDeclaredField("servicos")
//        };
//
//        assertTrue(campo[0].isAnnotationPresent(NotNull.class));
//        assertTrue(campo[1].isAnnotationPresent(NotNull.class));
//        assertTrue(campo[2].isAnnotationPresent(OneToMany.class));
//        assertTrue(campo[3].isAnnotationPresent(OneToMany.class));
//
//
//    }
//
//}
