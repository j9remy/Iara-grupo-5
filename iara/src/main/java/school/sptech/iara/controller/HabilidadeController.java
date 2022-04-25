package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Habilidade;
import school.sptech.iara.repository.HabilidadeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habilidade")
public class HabilidadeController {

//    private List<Habilidade> habilidades = new ArrayList<>();

    @Autowired
    private HabilidadeRepository repository;

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


}
