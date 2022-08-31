package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;
import school.sptech.iara.model.ServicoAtribuido;
import school.sptech.iara.repository.PrestadorRepository;
import school.sptech.iara.repository.ServicoAtribuidoRepository;
import school.sptech.iara.repository.ServicoRepository;
import school.sptech.iara.request.PrestadorServicoRequest;
import school.sptech.iara.request.ServicoRequest;
import school.sptech.iara.response.ServicoAvaliacaoResponse;
import school.sptech.iara.response.ServicoResponse;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico")
@CrossOrigin
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ServicoAtribuidoRepository servicoAtribuidoRepository;
    @Autowired
    private PrestadorRepository repository;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de serviços"),
            @ApiResponse(responseCode = "204", description = "A lista não foi encontrada")
    })
    public ResponseEntity<List<ServicoResponse>> getListaServicos(){
        if (!servicoRepository.findAll().isEmpty()){
            List<ServicoResponse> resp = new ArrayList<>();
            List<Servico> servicos = servicoRepository.findAll();
            for (Servico servico : servicos){
                ServicoResponse servicoResponse = new ServicoResponse(servico.getId(),
                        servico.getValor(), servico.getDescricao(),servico.getTipo(),servico.getDuracaoEstimada(),
                        servico.getPrestador().getId(),servico.getAvaliacao(),servico.getQtdServicosAvaliados());
                resp.add(servicoResponse);
            }
            return ResponseEntity.status(200).body(resp);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{index}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista do serviço desejado"),
            @ApiResponse(responseCode = "404", description = "O serviço desejado não foi encontrado")
    })
    public ResponseEntity<ServicoResponse> getServicoPorIndex(@PathVariable int index){
        Optional<Servico> servicoOptional = servicoRepository.findById(index);
        if (servicoOptional.isPresent()) {
            Servico servico = servicoOptional.get();
            ServicoResponse servicoResponse = new ServicoResponse(servico.getId(),
                    servico.getValor(), servico.getDescricao(),servico.getTipo(),servico.getDuracaoEstimada(),
                    servico.getPrestador().getId(),servico.getAvaliacao(),servico.getQtdServicosAvaliados());
            return ResponseEntity.status(200).body(servicoResponse);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/avaliacao/{idServico}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a média de avaliações de todos os serviços atribuidos deste serviço"),
            @ApiResponse(responseCode = "204", description = "Este serviço não tem avaliações"),
            @ApiResponse(responseCode = "400", description = "Serviço inexistente")
    })
    public ResponseEntity<ServicoAvaliacaoResponse> getAvaliacao(@PathVariable Integer idServico){
        Optional<Servico> servicoOptional = servicoRepository.findById(idServico);
        if (servicoOptional.isPresent()){
            Servico servico = servicoOptional.get();
            List<ServicoAtribuido> servicoAtribuidos = servicoAtribuidoRepository.findAllByServicoAndStatus(servico, "Finalizado");
            Double soma = 0d;
            Double media = 0d;
            Integer contagem = 0;
            for (ServicoAtribuido serv: servicoAtribuidos) {
                if(serv.getAvaliacao() >= 0 && serv.getAvaliacao() <= 5){
                    soma += serv.getAvaliacao();
                    contagem++;
                }
            }
            if (!servicoAtribuidos.isEmpty()){
                media = soma / contagem;
                return ResponseEntity.status(200).body(new ServicoAvaliacaoResponse(servico.getId(), media));
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(400).build();
    }


//    adiciona serviço
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço adicionado com sucesso ao prestador desejado"),
            @ApiResponse(responseCode = "400", description = "O serviço já existe"),
            @ApiResponse(responseCode = "404", description = "O prestador desejado não foi encontrado"),
    })
    public ResponseEntity<Void> postAddServico(@RequestBody @Valid PrestadorServicoRequest req){
        Optional<Prestador> prestadorOptional = repository.findById(req.getIdPrestador());
        if (prestadorOptional.isPresent()) {
            Prestador prestador = prestadorOptional.get();
            if (!prestador.servicoExiste(req.getServico())) {
                Servico servico = new Servico(req.getServico().getValor(),
                        req.getServico().getDescricao(),
                        req.getServico().getTipo(),
                        req.getServico().getDuracaoEstimada(),
                        prestador);
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O serviço desejado não foi encontrado")
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O serviço desejado foi ativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O serviço ativo desejado não foi encontrado")
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O serviço desejado desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O serviço ativo desejado não foi encontrado")
    })
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

    @PatchMapping("/duracao/{id}/{duracao}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O serviço desejado desativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "A duração desejada é igual a cadastradaa atualmente"),
            @ApiResponse(responseCode = "404", description = "O serviço desejado não foi encontrado")
    })
    public ResponseEntity<Void> patchDuracao(@PathVariable Integer id,
                                       @PathVariable LocalTime duracao){
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
