package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.Service.HabilidadeService;
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
@CrossOrigin
public class HabilidadeController {

    @Autowired
    private HabilidadeRepository repository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private HabilidadeService habilidadeService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de habilidades"),
            @ApiResponse(responseCode = "204", description = "Retorna uma lista vazia")
    })
    public ResponseEntity<List<Habilidade>> getHabilidades(){
        List<Habilidade> habilidades = repository.findAll();
        if (!habilidades.isEmpty()){
            return ResponseEntity.status(200).body(habilidades);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna habilidade pelo index
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a habilidade solicitada"),
            @ApiResponse(responseCode = "404", description = "Habilidade não encontrada")
    })
    public ResponseEntity<Habilidade> getHabilidadePorIndex(@PathVariable int id){
        Optional<Habilidade> habilidadeOptional = repository.findById(id);
        if (habilidadeOptional.isPresent()){
            Habilidade habilidade = habilidadeOptional.get();
            return ResponseEntity.status(200).body(habilidade);
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habilidade criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Habilidade já existe")
    })
    public ResponseEntity<Void> postAddHabilidade(@RequestBody Habilidade habilidade){
        if (!repository.existsByDescricao(habilidade.getDescricao()) &&
                !repository.existsByCategoria(habilidade.getCategoria())){
            repository.save(habilidade);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    // adiciona habilidade
    @PostMapping("/prestador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habilidade adicionada ao prestador solicitado com sucesso"),
            @ApiResponse(responseCode = "400", description = "O prestador já possui esta habilidade cadastrada"),
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado")
    })
    public ResponseEntity<Void> postAddHabilidade(@RequestBody @Valid PrestadorHabilidadeRequest req){
        return ResponseEntity.status(habilidadeService.associarPrestador(req).getStatusCodeValue()).build();
    }

    // remove habilidade
    @DeleteMapping("/prestador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Habilidade removida do prestador solicitado" +
                    " com sucesso."),
            @ApiResponse(responseCode = "400", description = "O prestador não possui esta habilidade cadastrada."),
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado.")
    })
    public ResponseEntity<Void> deleteRemoveHabilidade(@RequestBody @Valid PrestadorHabilidadeRequest req) {
        return ResponseEntity.status(habilidadeService.desassociarPrestador(req).getStatusCodeValue()).build();
    }

}
