//package school.sptech.iara.model;
//
//import com.github.javafaker.Faker;
//import org.junit.Ignore;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class ServicoTest {
//
//    @Ignore("Falta implementar coisa no servico, Please ignore")
//    @Test
//    @DisplayName("getQtdServicosAvaliados deve retornar a quatidade de servicos avaliados")
//    void getQtdServicosAvaliadosDeveRetornarUmNumeroInteiroQueRepresenteAQauntidadeDeServicosAvaliados(){
//        //Arrange
//        Servico servico = new Servico(
//            new Faker().random().nextDouble(),
//            new Faker().lorem().sentence(),
//            new Faker().lorem().word(),
//            new Faker().random().nextDouble()
//        );
//
//        // Create LocalDate object
//        // using LocalDate.of() method
//        LocalDate date
//            = LocalDate.of(2020, 5, 13);
//
//        // Create LocalTime object
//        // using LocalTime.of() method
//        LocalTime time = LocalTime.of(6, 30);
//
//        // Create LocalDateTime object
//        LocalDateTime localdatetime
//            = LocalDateTime.of(date, time);
//
//        ServicoAtribuido [] servicosAtribuidos = new ServicoAtribuido[]{
//            new ServicoAtribuido(
//                new Servico(
//                    new Faker().random().nextDouble(),
//                    new Faker().lorem().sentence(),
//                    new Faker().lorem().word(),
//                    new Faker().random().nextDouble()
//                ),
//                localdatetime,
//                new Cliente(),
//                new Faker().lorem().sentence()),
//            new ServicoAtribuido(
//                new Servico(
//                    new Faker().random().nextDouble(),
//                    new Faker().lorem().sentence(),
//                    new Faker().lorem().word(),
//                    new Faker().random().nextDouble()
//                ),
//                localdatetime,
//                new Cliente(),
//                new Faker().lorem().sentence()),
//            new ServicoAtribuido(
//                new Servico(
//                    new Faker().random().nextDouble(),
//                    new Faker().lorem().sentence(),
//                    new Faker().lorem().word(),
//                    new Faker().random().nextDouble()
//                ),
//                localdatetime,
//                new Cliente(),
//                new Faker().lorem().sentence()),
//            new ServicoAtribuido(
//                new Servico(
//                    new Faker().random().nextDouble(),
//                    new Faker().lorem().sentence(),
//                    new Faker().lorem().word(),
//                    new Faker().random().nextDouble()
//                ),
//                localdatetime,
//                new Cliente(),
//                new Faker().lorem().sentence())
//        };
//
//        for(int i = 0 ; i < servicosAtribuidos.length/2 ; i++)
//            servicosAtribuidos[i].setAvaliacao(new Faker().random().nextDouble());
//
//        int avaliacoes = 0;
//
//        //Act
//        for(ServicoAtribuido servicoAtribuido : servicosAtribuidos)
//            servico.addServicoAtribuido(servicoAtribuido);
//
//        for (ServicoAtribuido servicoAtribuido : servico.getServicosAtribuidos())
//            if (servicoAtribuido.getAvaliacao() != -1)
//                avaliacoes++;
//
//        //Assert
//        assertEquals(avaliacoes,servico.getQtdServicosAvaliados());
//        assertNotEquals(servicosAtribuidos.length , servico.getQtdServicosAvaliados());
//
//    }
//
//
//}
