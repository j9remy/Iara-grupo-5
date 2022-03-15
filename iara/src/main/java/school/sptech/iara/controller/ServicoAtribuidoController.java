package school.sptech.iara.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Servico;
import school.sptech.iara.model.ServicoAtribuido;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/servico-atribuido")
public class ServicoAtribuidoController {

    private List<ServicoAtribuido> servicoAtribuidos = new ArrayList<>();

    @GetMapping
    public List<ServicoAtribuido> getListaServicos(){
        return servicoAtribuidos;
    }

    @GetMapping("/{index}")
    public ServicoAtribuido getServicoPorIndex(@PathVariable int index){
        try{
            if (!Objects.isNull(servicoAtribuidos.get(index))){
                return servicoAtribuidos.get(index);
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return null;
    }

    @PostMapping
    public void postCadastrarServico(@RequestBody Cliente cliente){
        servicoAtribuidos.add(new ServicoAtribuido(cliente));
    }

}
