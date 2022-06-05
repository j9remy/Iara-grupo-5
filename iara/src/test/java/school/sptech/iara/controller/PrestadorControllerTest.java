package school.sptech.iara.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import school.sptech.iara.model.Prestador;
import school.sptech.iara.repository.AgendaRepository;
import school.sptech.iara.repository.EnderecoRepository;
import school.sptech.iara.repository.HabilidadeRepository;
import school.sptech.iara.repository.PrestadorRepository;
import school.sptech.iara.repository.ServicoRepository;

@SpringBootTest(classes = {PrestadorController.class})
public class PrestadorControllerTest {
    @Autowired
    PrestadorController prestadorController;

    @MockBean
    private PrestadorRepository prestadorRepository;
    
    @MockBean
    private HabilidadeRepository habilidadeRepository;
    
    @MockBean
    private ServicoRepository servicoRepository;
    
    @MockBean
    private AgendaRepository agendaRepository;
    
    @MockBean
    private EnderecoRepository enderecoRepository;


    @Test
    @DisplayName("getListaPrestador deve Retornar status 200 e uma lista no corpo se houver prestadores")
    void getListaPrestadoresDeveRetornarStatus200(){
        //Arrange
        Prestador prestador01 = mock(Prestador.class);
        Prestador prestador02 = mock(Prestador.class);
        List<Prestador> listaMock = List.of(prestador01,prestador02);
        when(prestadorRepository.findAll()).thenReturn(listaMock);

        
        ResponseEntity<List<Prestador>> resposta = prestadorController.getListaPrestadores();

        // verificando se o status da resposta é 204
        assertEquals(200, resposta.getStatusCodeValue());

        // verificando se a resposta não tem corpo (corpo == null)
        assertEquals(listaMock,resposta.getBody());
    }
}
