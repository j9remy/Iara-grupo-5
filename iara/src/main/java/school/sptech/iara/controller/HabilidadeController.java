package school.sptech.iara.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Habilidade;
import school.sptech.iara.model.Prestador;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/habilidade")
public class HabilidadeController {

    private List<Habilidade> habilidades = new ArrayList<>();

    @GetMapping
    public List<Habilidade> getHabilidades(){
        return habilidades;
    }

    // retorna habilidade pelo index
    @GetMapping("/{index}")
    public Habilidade getHabilidadePorIndex(@PathVariable int index){
        try{
            if (!Objects.isNull(habilidades.get(index))){
                return habilidades.get(index);
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return null;
    }

    @PostMapping
    public void postAddHabilidade(@RequestBody Habilidade habilidade[]){
        for (int i = 0; i < habilidade.length;i++){
            boolean encontrado = false;
            for (Habilidade hab: habilidades) {
                if (hab.toString().equals(habilidade[i].toString())){
                    encontrado = true;
                }
            }
            if (!encontrado){
                habilidades.add(habilidade[i]);
            }
        }
    }


}
