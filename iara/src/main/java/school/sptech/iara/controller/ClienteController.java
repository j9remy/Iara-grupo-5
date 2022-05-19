package school.sptech.iara.controller;

import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.AvaliacaoCliente;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Endereco;
import school.sptech.iara.model.Usuario;
import school.sptech.iara.repository.AvaliacaoRepository;
import school.sptech.iara.repository.ClienteRepository;
import school.sptech.iara.repository.EnderecoRepository;
import school.sptech.iara.request.ClienteIdAvaliacaoRequest;
import school.sptech.iara.request.ClienteUpdateRequest;
import school.sptech.iara.request.EnderecoSimplesRequest;
import school.sptech.iara.request.UsuarioEmailSenhaRequest;
import school.sptech.iara.response.UsuarioAvaliacaoResponse;

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
    @Autowired
    private EnderecoRepository enderecoRepository;

    // retorna todos registros de usuários
    @GetMapping
    public ResponseEntity getCliente(){
        List<Cliente> clientes = repository.findAll();
        if (!clientes.isEmpty()){
            return ResponseEntity.status(200).body(clientes);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna usuário pelo index
    @GetMapping("/{id}")
    public ResponseEntity getClientePorId(@PathVariable int id){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return ResponseEntity.status(200).body(repository.findById(id));
        }
        return ResponseEntity.status(404).build();
    }

    //cadastro de clientes, com possibilidade de cadastrar vários de uma só vez
    @PostMapping
    public ResponseEntity postCadastroClientes(@RequestBody Cliente cliente){
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
        return ResponseEntity.status(404).build();
    }

    //Adicionar avaliação a lista de avaliações
    @PostMapping("/avaliacao")
    public ResponseEntity postCadastroAvaliacao(@RequestBody ClienteIdAvaliacaoRequest req){
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
    public ResponseEntity deleteLogOffUsuario(@RequestBody @Valid UsuarioEmailSenhaRequest req){
        Optional<Cliente> clienteOptional = repository.findByEmailAndSenha(req.getEmail(), req.getSenha());
        if (clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.logOff(req.getEmail(), req.getSenha());
            repository.save(cliente);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping
    public ResponseEntity putCliente(@RequestBody @Valid ClienteUpdateRequest req){
        Optional<Cliente> clienteOptional = repository.findById(req.getId());
        if (clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.setNome(req.getNome());
            cliente.setSobrenome(req.getSobrenome());
            cliente.setDataNasc(req.getDataNasc());
            cliente.setSenha(req.getSenha());
            cliente.setSexo(req.getGenero());
            Boolean telefoneExiste = repository.existsByTelefone(req.getTelefone());
            if(!req.getTelefone().equals(cliente.getTelefone())){
                if (!telefoneExiste){
                    cliente.setTelefone(req.getTelefone());
                    repository.save(cliente);
                    return ResponseEntity.status(201).build();
                }
            }
            repository.save(cliente);
            return ResponseEntity.status(206).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/endereco/{idCliente}")
    public ResponseEntity postEnderecoCliente(@PathVariable Integer idCliente,
                                              @RequestBody EnderecoSimplesRequest enderecoRequest){
        List<Endereco> enderecos = enderecoRepository.enderecoValido(enderecoRequest.getCep(),
                enderecoRequest.getComplemento(),
                enderecoRequest.getNumero());
        Optional<Cliente> clienteOptional = repository.findById(idCliente);
        if (!enderecos.isEmpty() && clienteOptional.isPresent()){
            Endereco endereco = enderecos.get(0);
            Cliente cliente = clienteOptional.get();
            cliente.addEndereco(endereco);
            repository.save(cliente);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping(value = "/foto/{idCliente}", produces = "image/jpeg")
    public ResponseEntity<byte[]> getFoto(@PathVariable Integer idCliente) {
        byte[] foto = repository.getFoto(idCliente);
        if (foto == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(foto);
    }

    @PatchMapping(value = "/foto/{idCliente}", consumes = "image/jpeg")
    public ResponseEntity patchFoto(@PathVariable Integer idCliente,
                                    @RequestBody byte[] novaFoto) {
        if (!repository.existsById(idCliente)) {
            return ResponseEntity.status(404).build();
        }
        repository.atualizarFoto(idCliente, novaFoto);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity getRelatorio() {
        String relatorio = "";

        List<Cliente> lista = repository.findAll();

        for (Cliente cliente : lista) {
            relatorio += cliente.getId() +
                ";" + cliente.getNome() + " " + cliente.getSobrenome() +
                ";" + cliente.getCpf() +
                ";" + cliente.getDataNasc() +
                ";" + cliente.getEmail() +
                ";" + cliente.getSexo() +
                ";" + cliente.getTelefone() + "\r\n";
        }

        return ResponseEntity
            .status(200)
            .header("content-type", "text/csv")
            .header("content-disposition",
                    "filename=\"relatorio_cliente.csv\"")
            .body(relatorio);
    }

}
