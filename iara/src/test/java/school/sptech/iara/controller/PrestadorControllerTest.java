package school.sptech.iara.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.repository.*;
import school.sptech.iara.request.UsuarioEmailSenhaRequest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = {PrestadorController.class})
public class PrestadorControllerTest {

    @Autowired
    PrestadorController prestadorController;

    @MockBean
    private PrestadorRepository prestadorRepo;

    @MockBean
    private HabilidadeRepository habilidadeRepository;
    @MockBean
    private ServicoRepository servicoRepository;
    @MockBean
    private ServicoAtribuidoRepository servicoAtribuidoRepository;
    @MockBean
    private AgendaRepository agendaRepository;
    @MockBean
    private EnderecoRepository enderecoRepository;
    @MockBean
    private PortifolioRepository portifolioRepository;
    @MockBean
    private ServicoController servicoController;




    @Test
    @DisplayName("getListaPrestador deve Retornar status 200 e uma lista não vazia no corpo se houver prestadores")
    public void getListaPrestadoresDeveRetornarStatus200UmaListaNaoVaziaNoBody(){

        //Arrange
        Prestador prestador1 = mock(Prestador.class);
        Prestador prestador2 = mock(Prestador.class);
        List<Prestador> listaMock = List.of(prestador1,prestador2);


        //Act
        when(prestadorRepo.findAll()).thenReturn(listaMock);

        ResponseEntity<List<Prestador>> resposta = prestadorController.getListaPrestadores();


        // Assert
        //verify(prestadorRepo, times(1)).findAll();

        assertEquals(200, resposta.getStatusCodeValue());
        assertFalse(resposta.getBody().isEmpty());
        assertEquals(listaMock,resposta.getBody());

    }


    @Test
    @DisplayName("getListaPrestador deve Retornar status 204 e uma lista vazia no body")
    public void getListaPrestadoresDeveRetornarStatus204EListaVaziaNoBody(){
        //Arrange
        List<Prestador> listaMock = new ArrayList<>() ;
        when(prestadorRepo.findAll()).thenReturn(listaMock);

        // Act
        ResponseEntity<List<Prestador>> resposta = prestadorController.getListaPrestadores();

        // Assert
        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }


    @Test
    @DisplayName("getPrestadorPorId deve Retornar status 200 e um prestador no body")
    public void getPrestadorPorIDDeveRetornarStatus200NoBodyUmPrestador(){

        //Arrange
        Prestador prestadorMock = mock(Prestador.class);

        when(prestadorRepo.findById(anyInt())).thenReturn(Optional.of(prestadorMock));

        // Act
        ResponseEntity<Prestador> resposta = prestadorController.getPrestadorPorId(1);

        // Assert
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(prestadorMock,resposta.getBody());

    }


    @Test
    @DisplayName("getPrestadorPorId deve Retornar status 404 e um body vazio")
    public void getPrestadorPorIDDeveRetornarStatus404NoBodyNada(){

        //Arrange
        Prestador prestadorMock = mock(Prestador.class);

        when(prestadorRepo.findById(1)).thenReturn(Optional.of(prestadorMock));

        // Act
        ResponseEntity<Prestador> resposta = prestadorController.getPrestadorPorId(2);

        // Assert
        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }


    @Test
    @DisplayName("postCadastrarPrestador deve Retornar status 201 e um body vazio")
    public void postCadastrarPrestadorDeveRetornarStatus201NoBodyNada(){

        //Arrange
        Prestador prestadorMock = new Prestador(
            new Faker().name().firstName(), new Faker().name().lastName(),
            new Faker().lorem().word(),LocalDate.now(),new Faker().internet().emailAddress(),
            new Faker().internet().password(),
            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
        );

        List<Prestador> listaMock = new ArrayList<>();

        when(prestadorRepo.validarCadastro(anyString(),anyString(),anyString())).thenReturn(listaMock);

        // Act
        ResponseEntity<Void> resposta = prestadorController.postCadastrarPrestador(prestadorMock);

        // Assert
        assertTrue(listaMock.isEmpty());
        assertEquals(201, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }


    @Test
    @DisplayName("postCadastrarPrestador deve Retornar status 400 e um body vazio")
    public void postCadastrarPrestadorDeveRetornarStatus400NoBodyNada(){

        //Arrange
        Prestador prestadorMock = mock(Prestador.class);
        Prestador prestadorMock2 = mock(Prestador.class);
        Prestador prestadorMock3 = mock(Prestador.class);

        List<Prestador> listaMock = List.of(prestadorMock,prestadorMock2,prestadorMock3);

        when(prestadorRepo.validarCadastro(anyString(),anyString(),anyString())).thenReturn(listaMock);

        // Act
        ResponseEntity<Void> resposta = prestadorController.postCadastrarPrestador(prestadorMock);

        // Assert
        assertFalse(listaMock.isEmpty());
        assertEquals(400, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }

    @Test
    @DisplayName("postAutenticarPrestador deve Retornar status 200 e um body vazio")
    public void postAutenticarPrestadorDeveRetornarStatus200NoBodyNada(){
        //Arrange
        Prestador prestadorMock = new Prestador(
            new Faker().name().firstName(), new Faker().name().lastName(),
            new Faker().lorem().word(),new Faker().internet().emailAddress(),
            new Faker().internet().password(), LocalDate.now(),
            new Faker().lorem().character(), new Faker().phoneNumber().phoneNumber(),
            new Faker().shakespeare().kingRichardIIIQuote(), new Faker().random().nextBoolean(),
            new Faker().random().nextBoolean(), new Faker().random().nextDouble()
        );
        var optional = Optional.of(prestadorMock);
        UsuarioEmailSenhaRequest req = mock(UsuarioEmailSenhaRequest.class);

        when(prestadorRepo.findByEmailAndSenha(anyString(),anyString())).thenReturn(optional);

        // Act
        ResponseEntity<Void> resposta = prestadorController.postAutenticarPrestador(req);

        // Assert
        assertTrue(optional.isPresent());
        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }

    @Test
    @DisplayName("postAutenticarPrestador deve Retornar status 404 e um body vazio")
    public void postAutenticarPrestadorDeveRetornarStatus404NoBodyNada(){

        //Arrange
        Prestador prestadorMock = mock(Prestador.class);
        Optional<Prestador> optional;
        UsuarioEmailSenhaRequest req = mock(UsuarioEmailSenhaRequest.class);

        optional = Optional.empty();
        when(prestadorRepo.findByEmailAndSenha(anyString(),anyString())).thenReturn(optional);

        // Act
        ResponseEntity<Void> resposta = prestadorController.postAutenticarPrestador(req);

        // Assert
        assertFalse(optional.isPresent());
        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }



}
