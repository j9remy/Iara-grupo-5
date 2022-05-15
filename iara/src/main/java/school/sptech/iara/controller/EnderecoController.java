package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Endereco;
import school.sptech.iara.repository.EnderecoRepository;
import school.sptech.iara.request.EnderecoRequest;
import school.sptech.iara.rest.viacep.ViacepClient;
import school.sptech.iara.rest.viacep.ViacepResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViacepClient viacepClient;

    @GetMapping
    public ResponseEntity getEnderecos(){
        List<Endereco> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty())
            return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEndereco(@PathVariable Integer id){
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()){
            return ResponseEntity.status(200).body(enderecoOptional.get());

        }
        return ResponseEntity.status(404).build();
    }


    @PostMapping
    public ResponseEntity postEndereco(@RequestBody EnderecoRequest enderecoRequest){
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


}
