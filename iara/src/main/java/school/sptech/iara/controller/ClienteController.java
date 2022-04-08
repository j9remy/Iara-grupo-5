package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    private List<Cliente> clientes = new ArrayList<>();

    // retorna todos registros de usuários
    @GetMapping
    public ResponseEntity getListaClientes(){
        if(clientes.isEmpty())
            return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(clientes);
    }

    // retorna usuário pelo index
    @GetMapping("/{index}")
    public ResponseEntity getClientePorIndex(@PathVariable int index){
        try{
            if (!Objects.isNull(clientes.get(index))){
                return ResponseEntity.status(200).body(clientes.get(index));
            }
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(204).build();
    }

    //cadastro de clientes, com possibilidade de cadastrar vários de uma só vez
    @PostMapping
    public ResponseEntity postCadastrarClientes(@RequestBody Cliente cliente){
        for (Cliente cl: this.clientes) {
            if (cl.getEmail().equalsIgnoreCase(cliente.getEmail()) ||
                cl.getCpf().equalsIgnoreCase(cliente.getCpf()) ||
                cl.getTelefone().equalsIgnoreCase(cliente.getTelefone())){
                return ResponseEntity.status(409).build();
            }
        }
        repository.save(cliente);
        return ResponseEntity.status(201).build();
    }

    //Adicionar avaliação a lista de avaliações
    @PutMapping("/avaliacao/{indexUser}/{avaliacao}")
    public ResponseEntity postAddAvaliacao(@PathVariable int indexUser, @PathVariable int avaliacao){
        try{
            if (avaliacao < 0 || avaliacao >5){
                return ResponseEntity.status(400).build();
            }

            if (!Objects.isNull(clientes.get(indexUser))){
//                clientes.get(indexUser).addAvaliacao(avaliacao);
                return ResponseEntity.status(201).build();
            }else {
                return ResponseEntity.status(404).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }


    }

    //Autenticar usuário
    @PostMapping("/autenticacao/{email}/{senha}")
    public ResponseEntity postAutenticarUsuario(@PathVariable String email, @PathVariable String senha){
        for (Cliente cliente: clientes) {
            if (cliente.usuarioExiste(email,senha)){
                if (!cliente.isAutenticado()){
                    cliente.autenticar(email,senha);
                    return ResponseEntity.status(200).build();
                }
            }
        }
        return ResponseEntity.status(404).build();
    }

//    desautenticar usuário
    @DeleteMapping("/autenticacao/{email}/{senha}")
    public ResponseEntity deleteDesautenticarUsuario(@PathVariable String email, @PathVariable String senha){
        for (Cliente cliente: clientes) {
            if (cliente.usuarioExiste(email,senha)){
                if (cliente.isAutenticado()){
                    cliente.logOff(email,senha);
                    return ResponseEntity.status(200).build();
                }
            }
        }
        return ResponseEntity.status(404).build();
    }
}
