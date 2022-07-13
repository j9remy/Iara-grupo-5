package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Agenda;
import school.sptech.iara.model.Dia;
import school.sptech.iara.model.Agendamento;
import school.sptech.iara.repository.AgendaRepository;
import school.sptech.iara.repository.DiaRepository;
import school.sptech.iara.repository.AgendamentoRepository;
import school.sptech.iara.request.DiaRequest;
import school.sptech.iara.request.SemanaRequest;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
@CrossOrigin
public class AgendaController {

//    @Autowired
//    private AgendaRepository agendaRepository;
//
//    @Autowired
//    private DiaRepository diaRepository;
//
//    @Autowired
//    private AgendamentoRepository semanaRepository;
//
//    @PostMapping("/semana")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Semana cadastrada com sucesso"),
//            @ApiResponse(responseCode = "400", description = "Semana não pôde ser cadastrado, pois o prestador " +
//                    "não foi encontrado ou a semana já existe")
//    })
//    public ResponseEntity<Void> postSemana(@RequestBody SemanaRequest request) {
//        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(request.getIdPrestador());
//        if (agendaOptional.isPresent()){
//            Agenda agenda = agendaOptional.get();
//            if (!semanaRepository.existsByDiaInicialAndDiaFinal(request.getDiaInicial(), request.getDiaFinal())) {
//                Agendamento semana = new Agendamento(request.getDiaInicial(), request.getDiaFinal(), agenda);
//                semanaRepository.save(semana);
//                for (int i = 0; i < request.getCodigoDia().length; i++) {
//                    if (Objects.isNull(request.getInicioIntervalo()) || Objects.isNull(request.getFinalIntervalo())) {
//                        Dia dia = new Dia(request.getCodigoDia()[i], request.getHorarioInicial(), request.getHorarioFinal(), semana);
//                        diaRepository.save(dia);
//                        break;
//                    } else {
//                        Dia dia = new Dia(request.getCodigoDia()[i], request.getHorarioInicial(), request.getHorarioFinal(),
//                                request.getInicioIntervalo(), request.getFinalIntervalo(), semana);
//                        diaRepository.save(dia);
//                    }
//                }
//                return ResponseEntity.status(201).build();
//            }
//        }
//        return ResponseEntity.status(400).build();
//    }
//
//    @PutMapping("/dia")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Horário(s) atualizado(s) com sucesso"),
//            @ApiResponse(responseCode = "400", description = "Horários não puderam ser atualizados, pois o prestador" +
//                    "a semana e/ou o dia não foram encontrados")
//    })
//    public ResponseEntity<Void> putDia(@RequestBody DiaRequest request){
//        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(request.getIdPrestador());
//        if (agendaOptional.isPresent()) {
//            Agenda agenda = agendaOptional.get();
//            Optional<Agendamento> semanaOptional = semanaRepository.findById(request.getIdSemana());
//            if (semanaOptional.isPresent() && semanaRepository.existsByAgenda(agenda)) {
//                Agendamento semana = semanaOptional.get();
//                Optional<Dia> diaOptional = diaRepository.findByCodigoDiaAndSemana(request.getCodigoDia(), semana);
//                if (diaOptional.isPresent()) {
//                    Dia dia = diaOptional.get();
//                    dia.setHorarioInicial(request.getHorarioInicial());
//                    dia.setHorarioFinal(request.getHorarioFinal());
//                    dia.setInicioIntervalo(request.getInicioIntervalo());
//                    dia.setFinalIntervalo(request.getFinalIntervalo());
//                    diaRepository.save(dia);
//                    return ResponseEntity.status(200).build();
//                }
//            }
//        }
//        return ResponseEntity.status(400).build();
//    }


}
