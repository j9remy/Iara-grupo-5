package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Habilidade;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;
import school.sptech.iara.model.ServicoAtribuido;
import school.sptech.iara.repository.ServicoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @GetMapping
    public ResponseEntity getServicos(){
        List<Servico> servicos = repository.findAll();
        if (!servicos.isEmpty()){
            return ResponseEntity.status(200).body(servicos);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna serviço pelo index
    @GetMapping("/{id}")
    public ResponseEntity getServicoPorIndex(@PathVariable int id){
        Optional<Servico> servicoOptional = repository.findById(id);
        if (servicoOptional.isPresent()){
            Servico servico = servicoOptional.get();
            return ResponseEntity.status(200).body(servico);
        }
        return ResponseEntity.status(404).build();
    }

    //add serviço atribuido
//    @PostMapping("/servico-atribuido/{index}")
//    public void addServicoAtribuido(@RequestBody ServicoAtribuido servicoAtribuido,
//                                    @PathVariable int index){
//        servicos.get(index).addServicoAtribuido(servicoAtribuido);
//    }

//    @PutMapping("/alterna-ativo/{index}")
//    public void desativarServico(@PathVariable int index){
//        try{
//            if (!Objects.isNull(servicos.get(index))){
//                servicos.get(index).setAtivo();
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

}
