package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Endereco;
import school.sptech.iara.repository.EnderecoRepository;
import school.sptech.iara.request.EnderecoRequest;
import school.sptech.iara.request.EnderecoSimplesRequest;
import school.sptech.iara.rest.viacep.ViacepClient;
import school.sptech.iara.rest.viacep.ViacepResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
@CrossOrigin
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViacepClient viacepClient;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de endereços"),
            @ApiResponse(responseCode = "204", description = "Retorna uma lista vazia")
    })
    public ResponseEntity<List<Endereco>> getEnderecos(){
        List<Endereco> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty())
            return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(enderecos);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o endereço solicitado"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    public ResponseEntity<Endereco> getEndereco(@PathVariable Integer id){
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent())
            return ResponseEntity.status(200).body(enderecoOptional.get());
        return ResponseEntity.status(404).build();
    }


    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Endereço já existe")
    })
    public ResponseEntity<Void> postEnderecoReq(@RequestBody EnderecoSimplesRequest enderecoRequest){
        List<Endereco> enderecos = enderecoRepository.enderecoValido(enderecoRequest.getCep(),
                enderecoRequest.getComplemento(),
                enderecoRequest.getNumero());
        if(enderecos.isEmpty()){
            ViacepResponse viacepResponse = viacepClient.getAddress(enderecoRequest.getCep());
            String complemento = viacepResponse.getComplemento();
            if (!viacepResponse.getComplemento().equals(enderecoRequest.getComplemento())){
                complemento = enderecoRequest.getComplemento();
            }
            Endereco endereco = new Endereco(enderecoRequest.getCep(),
                    viacepResponse.getLogradouro(),
                    enderecoRequest.getNumero(),
                    viacepResponse.getBairro(),
                    viacepResponse.getLocalidade(),
                    viacepResponse.getUf(),
                    complemento);
            enderecoRepository.save(endereco);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O endereço desejado não foi encontrado")
    })
    public ResponseEntity<Endereco> putEndereco(@RequestBody @Valid EnderecoRequest req,
                                                @PathVariable Integer id) {
        Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(id);
        if (enderecoEncontrado.isPresent()) {
            Endereco endereco = enderecoEncontrado.get();
            endereco.setRua(req.getRua());
            endereco.setCidade(req.getCidade());
            endereco.setBairro(req.getBairro());
            endereco.setUf(req.getUf());
            endereco.setCep(req.getCep());
            endereco.setNumero(req.getNumero());
            endereco.setComplemento(req.getComplemento());
            enderecoRepository.save(endereco);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "O endereço desejado não foi encontrado")
    })
    public ResponseEntity<Endereco> deleteEndereco(@PathVariable Integer id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            enderecoRepository.delete(endereco);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

}
