package school.sptech.iara.controller;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Chat;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Servico;
import school.sptech.iara.model.ServicoAtribuido;
import school.sptech.iara.repository.ChatRepository;
import school.sptech.iara.repository.ClienteRepository;
import school.sptech.iara.repository.ServicoAtribuidoRepository;
import school.sptech.iara.repository.ServicoRepository;
import school.sptech.iara.request.ServicoAtribuidoRequest;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico-atribuido")
public class ServicoAtribuidoController {

    @Autowired
    private ServicoAtribuidoRepository servicoAtribuidoRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChatRepository chatRepository;

    @GetMapping("/{idServico}")
    public ResponseEntity<List<ServicoAtribuido>> getServicosAtribuidosPorServico(){
        List<ServicoAtribuido> servicosAtribuidos = servicoAtribuidoRepository.findAll();
        if (servicosAtribuidos.isEmpty())
            return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(servicosAtribuidos);
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<Void> postServicoAttrServico(@PathVariable Integer idUser,
                                                @RequestBody ServicoAtribuidoRequest req){
        Optional<Servico> servicoOptional = servicoRepository.findById(req.getIdServico());
        Optional<Cliente> clienteOptional = clienteRepository.findById(idUser);
        if (servicoOptional.isPresent() && clienteOptional.isPresent()){
            Servico servico = servicoOptional.get();
            Cliente cliente = clienteOptional.get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            ServicoAtribuido servicoAtribuido;
            if (req.getObservacoes().isBlank()){
                servicoAtribuido = new ServicoAtribuido(
                        servico,
                        LocalDateTime.parse(req.getDataInicio(), formatter),
                        cliente);
            }else{
                servicoAtribuido = new ServicoAtribuido(
                        servico,
                        LocalDateTime.parse(req.getDataInicio(), formatter),
                        cliente,
                        req.getObservacoes());
            }
            Chat chat = new Chat(servicoAtribuido);
            servicoAtribuidoRepository.save(servicoAtribuido);
            chatRepository.save(chat);

            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PatchMapping("/finalizar/{idServicoAttr}")
    public ResponseEntity<Void> patchFinalizado(@PathVariable Integer idServicoAttr){
        Optional<ServicoAtribuido> servicoAtribuidoOptional = servicoAtribuidoRepository.findById(idServicoAttr);
        if (servicoAtribuidoOptional.isPresent()){
            ServicoAtribuido servicoAtribuido = servicoAtribuidoOptional.get();
            if (!servicoAtribuido.isFinalizado()){
                servicoAtribuido.setFinalizado(true);
                servicoAtribuido.setHoraFim(LocalDateTime.now());
                servicoAtribuido.setStatus("Finalizado");
                Chat chat = chatRepository.findByServicoAtribuido_Id(idServicoAttr);
                chat.setFinalizado(true);
                servicoAtribuidoRepository.save(servicoAtribuido);
                chatRepository.save(chat);
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/finalizar/{idServicoAttr}/{avaliacao}")
    public ResponseEntity<Void> patchFinalizadoComAvaliacao(@PathVariable Integer idServicoAttr,
                                                            @PathVariable Double avaliacao){
        if (avaliacao < 0 || avaliacao >5)
            return ResponseEntity.status(400).build();
        Optional<ServicoAtribuido> servicoAtribuidoOptional = servicoAtribuidoRepository.findById(idServicoAttr);
        if (servicoAtribuidoOptional.isPresent()){
            ServicoAtribuido servicoAtribuido = servicoAtribuidoOptional.get();
            if (!servicoAtribuido.isFinalizado()){
                servicoAtribuido.setFinalizado(true);
                servicoAtribuido.setHoraFim(LocalDateTime.now());
                servicoAtribuido.setStatus("Finalizado");
                servicoAtribuido.setAvaliacao(avaliacao);
                Chat chat = chatRepository.findByServicoAtribuido_Id(idServicoAttr);
                chat.setFinalizado(true);
                servicoAtribuidoRepository.save(servicoAtribuido);
                chatRepository.save(chat);
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(404).build();
    }


}
