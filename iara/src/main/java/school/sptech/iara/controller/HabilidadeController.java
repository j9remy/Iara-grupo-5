package school.sptech.iara.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Habilidade;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/habilidade")
public class HabilidadeController {

    private List<Habilidade> habilidades = new ArrayList<>();

    @GetMapping
    public List<Habilidade> getHabilidades(){
        return habilidades;
    }

    @PostMapping
    public void postAddHabilidade(@RequestBody Habilidade habilidade){
        boolean encontrado = false;
        for (Habilidade hab: habilidades) {
            if (hab.toString().equals(habilidade.toString())){
                encontrado = true;
            }
        }
        if (!encontrado){
            habilidades.add(habilidade);
        }
    }


}
