package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Agenda;
import school.sptech.iara.model.Dia;
import school.sptech.iara.model.Semana;
import school.sptech.iara.repository.AgendaRepository;
import school.sptech.iara.repository.DiaRepository;
import school.sptech.iara.repository.SemanaRepository;
import school.sptech.iara.request.DiaRequest;
import school.sptech.iara.request.SemanaRequest;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private SemanaRepository semanaRepository;

    @PostMapping("/semana")
    public ResponseEntity<Void> postSemana(@RequestBody SemanaRequest request) {
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(request.getIdPrestador());
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            if (!semanaRepository.existsByDiaInicialAndDiaFinal(request.getDiaInicial(), request.getDiaFinal())) {
                Semana semana = new Semana(request.getDiaInicial(), request.getDiaFinal(), agenda);
                semanaRepository.save(semana);
                for (int i = 0; i < request.getCodigoDia().length; i++) {
                    if (Objects.isNull(request.getInicioIntervalo()) || Objects.isNull(request.getFinalIntervalo())) {
                        Dia dia = new Dia(request.getCodigoDia()[i], request.getHorarioInicial(), request.getHorarioFinal(), semana);
                        diaRepository.save(dia);
                        break;
                    } else {
                        Dia dia = new Dia(request.getCodigoDia()[i], request.getHorarioInicial(), request.getHorarioFinal(),
                                request.getInicioIntervalo(), request.getFinalIntervalo(), semana);
                        diaRepository.save(dia);
                    }
                }
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/dia")
    public ResponseEntity<Void> putDia(@RequestBody DiaRequest request){
        Optional<Agenda> agendaOptional = agendaRepository.findByPrestador_Id(request.getIdPrestador());
        if (agendaOptional.isPresent()) {
            Agenda agenda = agendaOptional.get();
            Optional<Semana> semanaOptional = semanaRepository.findById(request.getIdSemana());
            if (semanaOptional.isPresent() && semanaRepository.existsByAgenda(agenda)) {
                Semana semana = semanaOptional.get();
                Optional<Dia> diaOptional = diaRepository.findByCodigoDiaAndSemana(request.getCodigoDia(), semana);
                if (diaOptional.isPresent()) {
                    Dia dia = diaOptional.get();
                    dia.setHorarioInicial(request.getHorarioInicial());
                    dia.setHorarioFinal(request.getHorarioFinal());
                    dia.setInicioIntervalo(request.getInicioIntervalo());
                    dia.setFinalIntervalo(request.getFinalIntervalo());
                    diaRepository.save(dia);
                    return ResponseEntity.status(200).build();
                }
            }
        }
        return ResponseEntity.status(400).build();
    }


}
