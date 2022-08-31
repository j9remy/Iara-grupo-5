package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.*;
import school.sptech.iara.repository.*;
import school.sptech.iara.request.ServicoAtribuidoRequest;
import school.sptech.iara.response.ServicoAtribuidoResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico-atribuido")
@CrossOrigin
public class ServicoAtribuidoController {

    @Autowired
    private ServicoAtribuidoRepository servicoAtribuidoRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de serviços atribuídos"),
            @ApiResponse(responseCode = "204", description = "Retorna uma lista vazia")
    })
    public ResponseEntity<List<ServicoAtribuidoResponse>> getServicosAtribuidosPorServico(){
        List<ServicoAtribuido> servicosAtribuidos = servicoAtribuidoRepository.findAll();
        if (servicosAtribuidos.isEmpty())
            return ResponseEntity.status(204).build();
        List<ServicoAtribuidoResponse> servicoAtribuidoResponses = new ArrayList<>();
        for (ServicoAtribuido serv: servicosAtribuidos) {
            servicoAtribuidoResponses.add(serv.formatarResposta());
        }
        return ResponseEntity.status(200).body(servicoAtribuidoResponses);
    }

    @PostMapping("/{idUser}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço atribuído cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Serviço e/ou cliente e/ou prestador não encontrados")
    })
    public ResponseEntity<Void> postServicoAttrServico(@PathVariable Integer idUser,
                                                @RequestBody ServicoAtribuidoRequest req){
        Optional<Servico> servicoOptional = servicoRepository.findById(req.getIdServico());
        Optional<Cliente> clienteOptional = clienteRepository.findById(idUser);
        if (servicoOptional.isPresent() && clienteOptional.isPresent()){
            Servico servico = servicoOptional.get();
            Cliente cliente = clienteOptional.get();
            ServicoAtribuido servicoAtribuido;
            Prestador prestador = servico.getPrestador();
            Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(prestador.getId());
            if (agendaOptional.isPresent()){
                Agenda agenda = agendaOptional.get();
                Agendamento agendamento = new Agendamento(servico.getTipo() +" - "+ servico.getDescricao(),req.getObservacoes(), req.getDataInicio().toLocalDate(),
                        req.getDataInicio().toLocalTime(), req.getDataInicio().toLocalTime().plusHours(servico.getDuracaoEstimada().getHour())
                        .plusMinutes(servico.getDuracaoEstimada().getMinute()), agenda);

                if (agendamento.getHoraFim().isBefore(agendamento.getHoraInicio()) ||
                    agendamento.getData().isBefore(LocalDate.now()) ||
                    agendamento.getData().equals(LocalDate.now()) && agendamento.getHoraInicio().isBefore(LocalTime.now())){

                    return ResponseEntity.status(400).build();
                }
                List<Agendamento> agendamentos = agendamentoRepository.findAllByAgendaAndData(agenda,req.getDataInicio().toLocalDate());
                if (agendamentos.isEmpty())
                    return ResponseEntity.status(400).build();
                for (Agendamento ag:agendamentos) {
                    if (agendamento.getHoraInicio().isAfter(ag.getHoraInicio()) && agendamento.getHoraInicio().isBefore(ag.getHoraFim()) ||
                        agendamento.getHoraFim().isAfter(ag.getHoraInicio()) && agendamento.getHoraFim().isBefore(ag.getHoraFim()) ||
                        agendamento.getHoraInicio().equals(ag.getHoraInicio()) && agendamento.getHoraFim().equals(ag.getHoraFim())
                        ){
                        return ResponseEntity.status(400).build();
                    }
                }


                if (req.getObservacoes().isBlank()) {
                    servicoAtribuido = new ServicoAtribuido(
                            servico,
                            cliente);
                } else {
                    servicoAtribuido = new ServicoAtribuido(
                            servico,
                            cliente,
                            req.getObservacoes());
                }
                Chat chat = new Chat(servicoAtribuido);
                servicoAtribuidoRepository.save(servicoAtribuido);
                chatRepository.save(chat);
                agendamento.setServicoAtribuido(servicoAtribuido);
                agendamentoRepository.save(agendamento);
                return ResponseEntity.status(201).build();
            }

        }
        return ResponseEntity.status(400).build();
    }

    @PatchMapping("/finalizar/{idServicoAttr}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atribuído finalizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Serviço atribuído já está finalizado"),
            @ApiResponse(responseCode = "404", description = "Serviço atribuído não encontrado")
    })
    public ResponseEntity<Void> patchFinalizado(@PathVariable Integer idServicoAttr){
        Optional<ServicoAtribuido> servicoAtribuidoOptional = servicoAtribuidoRepository.findById(idServicoAttr);
        if (servicoAtribuidoOptional.isPresent()){
            ServicoAtribuido servicoAtribuido = servicoAtribuidoOptional.get();
            if (!servicoAtribuido.isFinalizado()){
                servicoAtribuido.setFinalizado(true);
                servicoAtribuido.setDataHoraFim(LocalDateTime.now());
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atribuído finalizado com sucesso e nota atribuída" +
                    " com sucesso"),
            @ApiResponse(responseCode = "400", description = "Serviço atribuído já está finalizado ou a nota inserida" +
                    " é inválida"),
            @ApiResponse(responseCode = "404", description = "Serviço atribuído não encontrado")
    })
    public ResponseEntity<Void> patchFinalizadoComAvaliacao(@PathVariable Integer idServicoAttr,
                                                            @PathVariable Double avaliacao){
        if (avaliacao < 0 || avaliacao >5)
            return ResponseEntity.status(400).build();
        Optional<ServicoAtribuido> servicoAtribuidoOptional = servicoAtribuidoRepository.findById(idServicoAttr);
        if (servicoAtribuidoOptional.isPresent()){
            ServicoAtribuido servicoAtribuido = servicoAtribuidoOptional.get();
            if (!servicoAtribuido.isFinalizado()){
                servicoAtribuido.setFinalizado(true);
                servicoAtribuido.setDataHoraFim(LocalDateTime.now());
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

    @DeleteMapping("cancelar/{idServicoAttr}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atribuído cancelado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Serviço atribuído já está finalizado"),
            @ApiResponse(responseCode = "404", description = "Serviço atribuído não encontrado")
    })
    public ResponseEntity<Void> deleteCancelado(@PathVariable Integer idServicoAttr) {
        Optional<ServicoAtribuido> servicoAtribuidoOptional = servicoAtribuidoRepository.findById(idServicoAttr);
        if (servicoAtribuidoOptional.isPresent()) {
            ServicoAtribuido servicoAtribuido = servicoAtribuidoOptional.get();
            if (!servicoAtribuido.isFinalizado()) {
                servicoAtribuido.setFinalizado(true);
                servicoAtribuido.setDataHoraFim(LocalDateTime.now());
                servicoAtribuido.setStatus("Cancelado");
                Chat chat = chatRepository.findByServicoAtribuido_Id(idServicoAttr);
                chat.setFinalizado(true);
                Agendamento agendamento = agendamentoRepository.findByServicoAtribuido_Id(idServicoAttr);
                agendamentoRepository.deleteById(agendamento.getId());
                chatRepository.save(chat);
                return ResponseEntity.status(202).build();
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(404).build();
    }

}
