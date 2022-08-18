package school.sptech.iara.controller;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Agenda;
import school.sptech.iara.model.Agendamento;
import school.sptech.iara.model.ServicoAtribuido;
import school.sptech.iara.repository.AgendaRepository;
import school.sptech.iara.repository.AgendamentoRepository;
import school.sptech.iara.repository.ServicoAtribuidoRepository;
import school.sptech.iara.request.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/agenda")
@CrossOrigin
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ServicoAtribuidoRepository servicoAtribuidoRepository;

    @GetMapping("/{idPrestador}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agenda encontrada e disponibilizados os agendamentos"),
            @ApiResponse(responseCode = "204", description = "Agenda encontrada porém se encontra vazia"),
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado")
    })
    public ResponseEntity<List<Agendamento>> getAgendamentosGeral(@PathVariable Integer idPrestador){
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(idPrestador);
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            List<Agendamento> agendamentos = agendamentoRepository.findAllByAgendaOrderByDataAscHoraInicioAsc(agenda);
            if (!agendamentos.isEmpty()){
                return ResponseEntity.status(200).body(agendamentos);
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agenda encontrada e disponibilizados os agendamentos"),
            @ApiResponse(responseCode = "204", description = "Agenda encontrada porém se encontra vazia"),
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado")
    })
    public ResponseEntity<List<Agendamento>> getAgendamentosDia(@RequestBody AgendamentoDataRequest req){
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(req.getIdPrestador());
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            List<Agendamento> agendamentos = agendamentoRepository.findAllByAgendaAndDataOrderByHoraInicio
                    (agenda,req.getData());
            if (!agendamentos.isEmpty()){
                return ResponseEntity.status(200).body(agendamentos);
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Agendamento já cadastrado ou prestador inválido")
    })
    public ResponseEntity<Void> postAgendamento(@RequestBody AgendamentoRequest req){
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(req.getIdPrestador());
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            Agendamento agendamento = new Agendamento(req.getTitulo(), req.getDescricao(), req.getData(),
                    req.getHoraInicio(),req.getHoraFim(),agenda);
            List<Agendamento> agendamentos = agendamentoRepository.findAllByAgendaAndData(agenda,req.getData());
            for (Agendamento ag: agendamentos) {
                if (ag.getHoraInicio().equals(agendamento.getHoraInicio()) && ag.getHoraFim().equals(agendamento.getHoraFim()) && ag.getDescricao().equals(agendamento.getDescricao())){
                    return ResponseEntity.status(400).build();
                }
            }
            agendamentoRepository.save(agendamento);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/intervalos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Intervalos Cadastrados até o final do proximo mes"),
            @ApiResponse(responseCode = "207", description = "Parte dos intervalos foram inseridos"),
            @ApiResponse(responseCode = "400", description = "Intervalos já cadastrados ou prestador inxistente")
    })
    public ResponseEntity<Void> postIntervalos(@RequestBody AgendamentoIntervalosRequest req){
        LocalDate hoje = LocalDate.now();
        LocalDate fimDaInsercao = LocalDate.of(hoje.getYear(), hoje.getMonth().plus(2), 1);
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(req.getIdPrestador());
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            int contador = 0;
            int contadorEntradas = 0;
            for (LocalDate i = hoje; i.isBefore(fimDaInsercao); i = i.plusDays(1)){
                for (int j = 0; j < req.getDiasDaSemana().length; j++) {
                    if (i.getDayOfWeek().equals(req.getDiasDaSemana()[j])){
                        if (agendamentoRepository.findAllByAgendaAndData(agenda,i).isEmpty()){
                            Agendamento horaInicioTrabalho = new Agendamento("Horario Fora do trabalho","", i,
                                    LocalTime.of(0,0), req.getHoraInicioTrabalho(),agenda); // tempo fora da meia noite até o horário de inicio
                            agendamentoRepository.save(horaInicioTrabalho);
                            if (!req.getHoraFimPausa().equals(req.getHoraInicioPausa())){
                                Agendamento horaInicioPausa = new Agendamento("Horário de pausa","", i, req.getHoraInicioPausa(),
                                        req.getHoraFimPausa(), agenda);
                                agendamentoRepository.save(horaInicioPausa);
                            }
                            Agendamento horaFimTrabalho = new Agendamento("Horario Fora do trabalho","", i,
                                     req.getHoraFimTrabalho(), LocalTime.of(23,59),agenda); // tempo fora do horario de finalização até as 23:59
                            agendamentoRepository.save(horaFimTrabalho);
                            contadorEntradas++;
                            break;
                        }
                        contador++;
                    }
                }
            }
            if (contador > contadorEntradas && contadorEntradas > 0){
                return ResponseEntity.status(207).build();
            } else if (contadorEntradas == 0) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/intervalos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Intervalos alterados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi alterado, pode ser que o dia desejado para alterar não existe")
    })
    public ResponseEntity<Void> putIntervalos(@RequestBody AgendamentoIntervalosRequest req){
        LocalDate hoje = LocalDate.now();
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(req.getIdPrestador());
        int contador = 0;
        if (agendaOptional.isPresent()) {
            Agenda agenda = agendaOptional.get();
            List<Agendamento> agendamentos = agendamentoRepository.findAllByAgendaAndDataAfter(agenda, hoje);
            for (Agendamento ag: agendamentos) {
                for (int i = 0; i < req.getDiasDaSemana().length; i++){
                    if (req.getDiasDaSemana()[i].equals(ag.getData().getDayOfWeek())){
                        if (ag.getHoraInicio().equals(LocalTime.of(0,0))){
                            Agendamento agendamento = ag;
                            agendamento.setHoraInicio(LocalTime.of(0,0));
                            agendamento.setHoraFim(req.getHoraInicioTrabalho());
                            agendamentoRepository.save(agendamento);
                        }else if (ag.getHoraFim().equals(LocalTime.of(23,59))){
                            Agendamento agendamento = ag;
                            agendamento.setHoraFim(LocalTime.of(23,59));
                            agendamento.setHoraInicio(req.getHoraFimTrabalho());
                            agendamentoRepository.save(agendamento);
                        }else if(ag.getTitulo().equals("Horário de pausa")){
                            Agendamento agendamento = ag;
                            agendamento.setHoraInicio(req.getHoraInicioPausa());
                            agendamento.setHoraFim(req.getHoraFimPausa());
                            agendamentoRepository.save(agendamento);
                        }
                        contador++;
                    }
                }
            }
        }
        if (contador > 0){
            return ResponseEntity.status(201).build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> putAgendamento(@RequestBody AgendamentoNewAndOldRequest req){
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(req.getIdPrestador());
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            List<Agendamento> agendamentos = agendamentoRepository.findAllByAgendaAndDataAndHoraInicioAndHoraFim
                    (agenda,req.getDataAntiga(),req.getHoraInicioAntiga(),req.getHoraFimAntiga());
            if (!agendamentos.isEmpty()){
                for (Agendamento ag:agendamentos){
                    ag.setTitulo(req.getTitulo());
                    ag.setDescricao(req.getDescricao());
                    ag.setData(req.getDataNova());
                    ag.setHoraInicio(req.getHoraInicioNova());
                    ag.setHoraFim(req.getHoraFimNova());
                    agendamentoRepository.save(ag);
                }
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/intervalos/{idPrestador}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Intervalos excluidos com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum registro encontrado para a exclusão"),
            @ApiResponse(responseCode = "400", description = "prestador inxistente")
    })
    public ResponseEntity<Void> deleteIntervalos(@RequestBody DayOfWeek[] diasDaSemana, @PathVariable Integer idPrestador){
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(idPrestador);
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            List<Agendamento> agendamentos = agendamentoRepository.findAllByAgendaAndDataAfter(agenda, LocalDate.now());
            if (!agendamentos.isEmpty()){
                Integer contador = 0;
                for (Agendamento ag:agendamentos) {
                    for (DayOfWeek dayOfWeek : diasDaSemana) {
                        if (ag.getData().getDayOfWeek().equals(dayOfWeek) && agendamentoRepository.countAgendamentoByData(ag.getData()) <= 3) {
                            agendamentoRepository.delete(ag);
                            contador++;
                        }
                    }
                }
                if (contador > 0){
                    return ResponseEntity.status(202).build();
                }
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Agendamento excluido com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum registro encontrado para a exclusão"),
            @ApiResponse(responseCode = "400", description = "prestador inxistente")
    })
    public ResponseEntity<Void> deleteAgendamento(@RequestBody AgendamentoDataHoraRequest req){
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(req.getIdPrestador());
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            List<Agendamento> agendamentos = agendamentoRepository.findAllByAgendaAndDataAndHoraInicioAndHoraFim
                    (agenda, req.getData(), req.getHoraInicio(), req.getHoraFim());
            if (!agendamentos.isEmpty()){
                for (Agendamento ag: agendamentos) {
                    if (!Objects.isNull(ag.getServicoAtribuido())){
                        ServicoAtribuido servicoAtribuido = ag.getServicoAtribuido();
                        if (!servicoAtribuido.isFinalizado()){
                            servicoAtribuido.setStatus("Cancelado");
                            servicoAtribuido.finalizarServico();
                            servicoAtribuidoRepository.save(servicoAtribuido);
                        }
                    }
                    agendamentoRepository.delete(ag);
                }
                return ResponseEntity.status(202).build();
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(400).build();
    }


}
