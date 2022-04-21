package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.repository.ClienteRepository;
import school.sptech.iara.request.UsuarioEmailSenhaRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    // retorna todos registros de usuários
    @GetMapping
    public ResponseEntity pegarItens(){
        List<Cliente> clientes = repository.findAll();
        if (!clientes.isEmpty()){
            return ResponseEntity.status(200).body(clientes);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna usuário pelo index
    @GetMapping("/{index}")
    public ResponseEntity getClientePorIndex(@PathVariable int index){

        Optional<Cliente> clienteOptional = repository.findById(index);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return ResponseEntity.status(200).body(repository.findById(index));
        }

        return ResponseEntity.status(204).build();
    }

    //cadastro de clientes, com possibilidade de cadastrar vários de uma só vez
    @PostMapping
    public ResponseEntity postCadastrarClientes(@RequestBody Cliente cliente){
        List<Cliente> clienteOptional = repository.validarCadastro(
                cliente.getEmail(), cliente.getCpf(), cliente.getTelefone()
        );
        if (clienteOptional.isEmpty()){
            repository.save(cliente);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();

    }

    //Adicionar avaliação a lista de avaliações
    @PutMapping("/avaliacao/{idUser}/{avaliacao}")
    public ResponseEntity postAddAvaliacao(@PathVariable int idUser, @PathVariable int avaliacao){
        try{
            if (avaliacao < 0 || avaliacao >5){
                return ResponseEntity.status(400).build();
            }

            Optional<Cliente> clienteOptional = repository.findById(idUser);
            if (clienteOptional.isPresent()){
                Cliente cliente = clienteOptional.get();
                cliente.addAvaliacao(avaliacao);
                repository.save(cliente);
                return ResponseEntity.status(201).build();
            }else {
                return ResponseEntity.status(204).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }


    }

    //Autenticar usuário
    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticarUsuario(@RequestBody @Valid UsuarioEmailSenhaRequest req){

        Optional<Cliente> clienteOptional = repository.findByEmailAndSenha(req.getEmail(), req.getSenha());

        if (clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.autenticar(req.getEmail(), req.getSenha());
            repository.save(cliente);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

//    desautenticar usuário
    @DeleteMapping("/autenticacao")
    public ResponseEntity deleteDesautenticarUsuario(@RequestBody @Valid UsuarioEmailSenhaRequest req){
        Optional<Cliente> clienteOptional = repository.findByEmailAndSenha(req.getEmail(), req.getSenha());
        if (clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.logOff(req.getEmail(), req.getSenha());
            repository.save(cliente);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
