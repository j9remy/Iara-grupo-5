package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Servico;
import school.sptech.iara.model.ServicoAtribuido;
import school.sptech.iara.repository.ServicoAtribuidoRepository;
import school.sptech.iara.repository.ServicoRepository;
import school.sptech.iara.request.ServicoAtribuidoRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico-atribuido")
public class ServicoAtribuidoController {

    @Autowired
    private ServicoAtribuidoRepository servicoAtribuidoRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("/{idServico}")
    public ResponseEntity getServicosAtribuidosPorServico(){
        List<ServicoAtribuido> servicosAtribuidos = servicoAtribuidoRepository.findAll();
        if (servicosAtribuidos.isEmpty())
            return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(servicosAtribuidos);
    }

    @PostMapping
    public ResponseEntity postServicoAttrServico(@RequestBody ServicoAtribuidoRequest req){
        Optional<Servico> servicoOptional = servicoRepository.findById(req.getIdServico());
        if (servicoOptional.isPresent()){
            Servico servico = servicoOptional.get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            ServicoAtribuido servicoAtribuido = new ServicoAtribuido(servico,
                    LocalDateTime.parse(req.getDataInicio(), formatter));
            servicoAtribuidoRepository.save(servicoAtribuido);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

}
