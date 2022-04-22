package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.AvaliacaoCliente;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.repository.AvaliacaoRepository;
import school.sptech.iara.repository.ClienteRepository;
import school.sptech.iara.request.ClienteIdAvaliacaoRequest;
import school.sptech.iara.request.UsuarioEmailSenhaRequest;
import school.sptech.iara.response.UsuarioAvaliacaoResponse;
import school.sptech.iara.util.Lista;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

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

    @GetMapping("/avaliacao/{id}")
    public ResponseEntity getAvaliacao(@PathVariable Integer id){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            UsuarioAvaliacaoResponse respAval = new UsuarioAvaliacaoResponse(cliente, cliente.calcAvaliacao());
            return ResponseEntity.status(200).body(respAval);
        }
        return ResponseEntity.status(404).build();    }

    //Adicionar avaliação a lista de avaliações
    @PostMapping("/avaliacao")
    public ResponseEntity postAddAvaliacao(@RequestBody ClienteIdAvaliacaoRequest req){
        if (req.getAvaliacao() < 0 || req.getAvaliacao() >5){
            return ResponseEntity.status(400).build();
        }

        Optional<Cliente> clienteOptional = repository.findById(req.getId());
        if (clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            AvaliacaoCliente avaliacaoCliente = new AvaliacaoCliente(req.getAvaliacao(),cliente);
            avaliacaoRepository.save(avaliacaoCliente);
            cliente.addAvaliacao(avaliacaoCliente);
            repository.save(cliente);
            return ResponseEntity.status(201).build();
        }else {
            return ResponseEntity.status(204).build();
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

    @GetMapping("/relatorio-cliente")
    public ResponseEntity getRelatorio() {
        String relatorio = "";

        List<Cliente> lista = repository.findAll();

        for (Cliente cliente : lista) {
            relatorio += cliente.getId()
                    + "," + cliente.getNome() + " " + cliente.getSobrenome() +
                    "," + cliente.getCpf() + "," + cliente.getDataNasc() +
                    "," + cliente.getEmail() + "," + cliente.getSexo() +
                    "," + cliente.getTelefone() + "\r\n";
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition",
                        "filename=\"Relatorio_Cliente.csv\"")
                .body(relatorio);
    }

}
