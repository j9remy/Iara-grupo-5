package school.sptech.iara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Habilidade;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;
import school.sptech.iara.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorRepository prestadorRepository;
    private List<Prestador> prestadores = new ArrayList<>();
    private List<Habilidade> habilidades = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();

    // retorna todos registros de prestadores
    @GetMapping
    public ResponseEntity getListaPrestadores(){
        prestadores.addAll(prestadorRepository.findAll());
        if (!prestadores.isEmpty()){
            return ResponseEntity.status(200).body(prestadores);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna usuário pelo index
    @GetMapping("/{index}")
    public ResponseEntity getPrestadorPorIndex(@PathVariable Integer index){
        try{
            if (!Objects.isNull(prestadorRepository.existsById(index))){
                return ResponseEntity.status(200).body(prestadorRepository.findById(index));
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(400).build();
    }

    //cadastro de prestador
    @PostMapping
    public ResponseEntity postCadastrarPrestadores(@RequestBody Prestador prestador){
//        if (!prestadorRepository.existsById(prestador.getId()))
//            return ResponseEntity.status(201).body(prestadorRepository.save(prestador));

        return ResponseEntity.status(400).build();
    }

    //Autenticar usuário
    @PostMapping("/autenticacao/{email}/{senha}")
    public ResponseEntity postAutenticarUsuario(@PathVariable String email, @PathVariable String senha){
        if (prestadorRepository.existsByEmailAndSenha(email,senha)) {
            Prestador prestador = prestadorRepository.getByEmailAndSenha(email,senha);
            if (!prestador.isAutenticado()){
                prestador.autenticar(email,senha);
                return ResponseEntity.status(200).build();
            }

        }
        return ResponseEntity.status(404).build();
    }

    // adiciona habilidade
    @PostMapping("/habilidade/{index}") // revisar fluxo
    public ResponseEntity postAddHabilidade(@RequestBody Habilidade habilidade,
                                  @PathVariable int index){
        boolean encontrado = false;
        for (Habilidade hab: habilidades) {
            if (hab.toString().equals(habilidade.toString())){
                encontrado = true;
            }
        }
        if (!encontrado){
            try{
                if (!Objects.isNull(prestadores.get(index))){
                    prestadores.get(index).addHabilidade(habilidade);
                    return ResponseEntity.status(201).build();
                }
            }catch (Exception e){
                return ResponseEntity.status(404).build();
            }
        }
        return ResponseEntity.status(404).build();
    }

    //adiciona serviço
    @PostMapping("/servico/{index}") // revisar fluxo
    public ResponseEntity postAddServico(@RequestBody Servico servico,
                                  @PathVariable int index){
//        if (prestadores.get(index).getServicos().isEmpty()){
//            prestadores.get(index).addServico(servico);
//            return ResponseEntity.status(201).build();
//        }

        boolean encontrado = false;
//        for (Servico ser: prestadores.get(index).getServicos()) {
//            if (ser.equals(servico)){
//                encontrado = true;
//            }
//        }
        if (!encontrado){
            try{
                if (!Objects.isNull(prestadores.get(index))){
                    prestadores.get(index).addServico(servico);
                    return ResponseEntity.status(201).build();
                }
            }catch (Exception e){
                System.out.println(e);
                return ResponseEntity.status(404).build();
            }
        }
        return ResponseEntity.status(404).build();
    }

    //    desautenticar usuário
    @DeleteMapping("/autenticacao/{email}/{senha}")// revisar o fluxo
    public ResponseEntity deleteDesautenticarUsuario(@PathVariable String email, @PathVariable String senha){

        if (prestadorRepository.existsByEmailAndSenha(email,senha)) {
            Prestador prestador = prestadorRepository.getByEmailAndSenha(email,senha);
            if (prestador.isAutenticado()){
                prestador.logOff(email,senha);
                return ResponseEntity.status(200).build();
            }

        }
        return ResponseEntity.status(404).build();
    }


}
