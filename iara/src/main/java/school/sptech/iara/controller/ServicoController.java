package school.sptech.iara.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;
import school.sptech.iara.model.ServicoAtribuido;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    private List<Servico> servicos = new ArrayList<>();

    @GetMapping
    public List<Servico> getServicos(){
        return servicos;
    }

    // retorna serviço pelo index
    @GetMapping("/{index}")
    public Servico getServicoPorIndex(@PathVariable int index){
        try{
            if (!Objects.isNull(servicos.get(index))){
                return servicos.get(index);
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return null;
    }

    // Cadastro de serviços
    @PostMapping
    public void cadastrarServico(@RequestBody Servico servico[]){
        for (int i = 0; i < servico.length; i++) {
            boolean encontrado = false;
            for (Servico ser: servicos) {
                if (servico.toString().equalsIgnoreCase(ser.toString())){
                    encontrado = true;
                }
            }
            if (!encontrado){
                servicos.add(servico[i]);
            }
        }
    }

    //add serviço atribuido
    @PostMapping("/servico-atribuido/{index}")
    public void addServicoAtribuido(@RequestBody ServicoAtribuido servicoAtribuido,
                                    @PathVariable int index){
        servicos.get(index).addServicoAtribuido(servicoAtribuido);
    }

    @PutMapping("/alterna-ativo/{index}")
    public void desativarServico(@PathVariable int index){
        try{
            if (!Objects.isNull(servicos.get(index))){
                servicos.get(index).setAtivo();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
