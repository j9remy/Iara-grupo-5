package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Habilidade;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.repository.HabilidadeRepository;
import school.sptech.iara.repository.PrestadorRepository;
import school.sptech.iara.request.PrestadorHabilidadeRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habilidade")
public class HabilidadeController {

    @Autowired
    private HabilidadeRepository repository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @GetMapping
    public ResponseEntity getHabilidades(){
        List<Habilidade> habilidades = repository.findAll();
        if (!habilidades.isEmpty()){
            return ResponseEntity.status(200).body(habilidades);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna habilidade pelo index
    @GetMapping("/{id}")
    public ResponseEntity getHabilidadePorIndex(@PathVariable int id){
        Optional<Habilidade> habilidadeOptional = repository.findById(id);
        if (habilidadeOptional.isPresent()){
            Habilidade habilidade = habilidadeOptional.get();
            return ResponseEntity.status(200).body(habilidade);
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping
    public ResponseEntity postAddHabilidade(@RequestBody Habilidade habilidade){
        if (!repository.existsByDescricao(habilidade.getDescricao()) &&
                !repository.existsByHabilidade(habilidade.getHabilidade())){
            repository.save(habilidade);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    // adiciona habilidade
    @PostMapping("/prestador")
    public ResponseEntity postAddHabilidade(@RequestBody @Valid PrestadorHabilidadeRequest req){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(req.getUserId());
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            if (!prestador.habilidadeExiste(req.getHabilidade())){
                repository.save(req.getHabilidade());
                prestador.addHabilidade(req.getHabilidade());
                prestadorRepository.save(prestador);
                return ResponseEntity.status(201).build();
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(404).build();
    }

}
