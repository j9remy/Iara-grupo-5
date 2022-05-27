package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;
import school.sptech.iara.repository.PrestadorRepository;
import school.sptech.iara.repository.ServicoRepository;
import school.sptech.iara.request.PrestadorServicoRequest;
import school.sptech.iara.request.ServicoRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private PrestadorRepository repository;

    @GetMapping
    public ResponseEntity<List<Servico>> getListaServicos(){
        if (servicoRepository.findAll().isEmpty())
            return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(servicoRepository.findAll());
    }

    @GetMapping("/{index}")
    public ResponseEntity<Servico> getServicoPorIndex(@PathVariable int index){
        Optional<Servico> servicoOptional = servicoRepository.findById(index);
        if (servicoOptional.isPresent())
            return ResponseEntity.status(200).body(servicoOptional.get());
        return ResponseEntity.status(404).build();
    }

//    adiciona serviço
    @PostMapping
    public ResponseEntity<Void> postAddServico(@RequestBody @Valid PrestadorServicoRequest req){
        Optional<Prestador> prestadorOptional = repository.findById(req.getIdPrestador());
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            if (!prestador.servicoExiste(req.getServico())){
                Servico servico = new Servico(req.getServico().getValor(),
                        req.getServico().getDescricao(),
                        req.getServico().getTipo(),
                        req.getServico().getDuracaoEstimada());
                prestador.addServico(servico);
                servicoRepository.save(servico);
                repository.save(prestador);
                return ResponseEntity.status(201).build();
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putAtualizaServico(@RequestBody @Valid ServicoRequest servicoRequest,
                                             @PathVariable int id) {
        Optional<Servico> servicoEncontrado = servicoRepository.findById(id);
        if (servicoEncontrado.isPresent()) {
            Servico servico = servicoEncontrado.get();
            servico.setTipo(servicoRequest.getTipo());
            servico.setDescricao(servicoRequest.getDescricao());
            servico.setValor(servicoRequest.getValor());
            servicoRepository.save(servico);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/ativo/{id}")
    public ResponseEntity<Void> postAtivoTrue(
            @PathVariable Integer id){
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if (servicoOptional.isPresent()){
            Servico servico = servicoOptional.get();
            servico.setAtivo(true);
            servicoRepository.save(servico);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/ativo/{id}")
    public ResponseEntity<Void> deleteAtivoTrue(
            @PathVariable Integer id){
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if (servicoOptional.isPresent()){
            Servico servico = servicoOptional.get();
            servico.setAtivo(false);
            servicoRepository.save(servico);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/duracao/{id}/{duracao}") //
    public ResponseEntity<Void> patchDuracao(@PathVariable Integer id,
                                       @PathVariable Double duracao){
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if (servicoOptional.isPresent()){
            Servico servico = servicoOptional.get();
            if (servico.getDuracaoEstimada().equals(duracao)){
                return ResponseEntity.status(400).build();
            }
            servico.setDuracaoEstimada(duracao);
            servicoRepository.save(servico);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

}
