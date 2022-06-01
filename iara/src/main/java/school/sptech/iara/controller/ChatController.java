package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.*;
import school.sptech.iara.repository.*;
import school.sptech.iara.request.MensagemRequest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
@CrossOrigin
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MensagemRepository mensagemRepository;
    @Autowired
    private ServicoAtribuidoRepository servicoAtribuidoRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private PrestadorRepository prestadorRepository;

    @GetMapping("/servicoAtribuido/{idServico}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista com todas mensagens enviadas no " +
                    "serviço escolhido"),
            @ApiResponse(responseCode = "204", description = "Retorna uma lista vazia"),
            @ApiResponse(responseCode = "400", description = "Serviço não encontrado")
    })
    public ResponseEntity<List<Mensagem>> getMensagens(@PathVariable Integer idServico){
        Boolean servicoExiste = servicoAtribuidoRepository.existsById(idServico);
        if (servicoExiste){
            List<Mensagem> mensagens = mensagemRepository.findAllByChat_ServicoAtribuidoId(idServico);
            if (!mensagens.isEmpty()){
                return ResponseEntity.status(200).body(mensagens);
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mensagem criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Chat não encontrado")
    })
    public ResponseEntity<Void> postMensagem(@RequestBody MensagemRequest req){
        Optional<Chat> chatOptional = chatRepository.findById(req.getIdChat());
        if (chatOptional.isPresent()){
            Chat chat = chatOptional.get();
            ServicoAtribuido servicoAtribuido = servicoAtribuidoRepository.findById(chat.getServicoAtribuido().getId()).get();
            Servico servico = servicoRepository.findById(servicoAtribuido.getServico().getId()).get();
            Prestador prestador = prestadorRepository.findByServicosContains(servico);
            Mensagem msg = new Mensagem(prestador,
                    servicoAtribuido.getCliente(),
                    req.getMensagem(),
                    chat,
                    req.getEnviadoPeloPrestador());
            mensagemRepository.save(msg);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

}
