package school.sptech.iara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Endereco;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;
import school.sptech.iara.repository.EnderecoRepository;
import school.sptech.iara.repository.HabilidadeRepository;
import school.sptech.iara.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.iara.repository.ServicoRepository;
import school.sptech.iara.request.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorRepository repository;
    @Autowired
    private HabilidadeRepository habilidadeRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;


    // retorna todos registros de prestadores
    @GetMapping
    public ResponseEntity getListaPrestadores(){
        List<Prestador> prestadores = repository.findAll();
        if (!prestadores.isEmpty()){
            return ResponseEntity.status(200).body(prestadores);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna usuário pelo index
    @GetMapping("/{id}")
    public ResponseEntity getPrestadorPorId(@PathVariable Integer id){
        Optional<Prestador> prestadorOptional = repository.findById(id);
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            return ResponseEntity.status(200).body(prestador);
        }
        return ResponseEntity.status(404).build();
    }

    //cadastro de prestador
    @PostMapping
    public ResponseEntity postCadastrarPrestador(@RequestBody Prestador prestador){
        List<Prestador> prestadoresInvalidos = repository.validarCadastro(
                prestador.getEmail(), prestador.getCpf(), prestador.getTelefone()
        );

        if (prestadoresInvalidos.isEmpty()){
            repository.save(prestador);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    //Autenticar usuário
    @PostMapping("/autenticacao")
    public ResponseEntity postAutenticarPrestador(@RequestBody @Valid UsuarioEmailSenhaRequest req){

        Optional<Prestador> prestadorOptional = repository.findByEmailAndSenha(req.getEmail(), req.getSenha());
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            prestador.autenticar(req.getEmail(), req.getSenha());
            repository.save(prestador);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    //    desautenticar usuário
    @DeleteMapping("/autenticacao")
    public ResponseEntity deleteLogoffPrestador(@RequestBody @Valid UsuarioEmailSenhaRequest req){
        Optional<Prestador> prestadorOptional = repository.findByEmailAndSenha(req.getEmail(), req.getSenha());
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            prestador.logOff(req.getEmail(), req.getSenha());
            repository.save(prestador);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping
    public ResponseEntity putPrestador(@RequestBody @Valid PrestadorUpdateRequest req){
        Optional<Prestador> prestadorOptional = repository.findById(req.getId());
        if(prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            prestador.setNome(req.getNome());
            prestador.setSobrenome(req.getSobrenome());
            prestador.setDataNasc(req.getDataNasc());
            prestador.setSenha(req.getSenha());
            prestador.setSexo(req.getGenero());
            prestador.setAtendeDomicilio(req.getAtendeDomicilio());
            if(!req.getResumo().isEmpty()){
                prestador.setResumo(req.getResumo());
            }
            Boolean telefoneExiste = repository.existsByTelefone(req.getTelefone());
            if(!req.getTelefone().equals(prestador.getTelefone())){
                if (!telefoneExiste){
                    prestador.setTelefone(req.getTelefone());
                    repository.save(prestador);
                    return ResponseEntity.status(201).build();
                }
            }
            repository.save(prestador);
            return ResponseEntity.status(206).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/endereco/{idPrestador}")
    public ResponseEntity postEnderecoCliente(@PathVariable Integer idPrestador,
                                              @RequestBody EnderecoRequest enderecoRequest){
        List<Endereco> enderecos = enderecoRepository.enderecoValido(enderecoRequest.getCep(),
                enderecoRequest.getComplemento(),
                enderecoRequest.getNumero());
        Optional<Prestador> prestadorOptional = repository.findById(idPrestador);
        if (!enderecos.isEmpty() && prestadorOptional.isPresent()){
            Endereco endereco = enderecos.get(0);
            Prestador prestador = prestadorOptional.get();
            prestador.addEndereco(endereco);
            repository.save(prestador);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping(value = "/foto/{idPrestador}", produces = "image/jpeg")
    public ResponseEntity<byte[]> getFoto(@PathVariable Integer idPrestador) {
        byte[] foto = repository.getFoto(idPrestador);
        if (foto == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(foto);
    }

    @PatchMapping(value = "/foto/{idPrestador}", consumes = "image/jpeg")
    public ResponseEntity patchFoto(@PathVariable Integer idPrestador,
                                    @RequestBody byte[] novaFoto) {
        if (!repository.existsById(idPrestador)) {
            return ResponseEntity.status(404).build();
        }
        repository.atualizarFoto(idPrestador, novaFoto);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity getRelatorio() {
        String relatorio = "";
        List<Prestador> lista = repository.findAll();
        for (Prestador prestador : lista) {
            relatorio += prestador.getId()
                    + ";" + prestador.getNome() + " " + prestador.getSobrenome() +
                    ";" + prestador.getCpf() +
                    "," + prestador.getDataNasc() +
                    ";" + prestador.getEmail() +
                    ";" + prestador.getSexo() +
                    ";" + prestador.getTelefone() +
                    ";" + prestador.getResumo() +
                    ";" + prestador.getAtendeDomicilio() +
                    "\r\n";
        }
        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition",
                        "filename=\"relatorio_prestador.csv\"")
                .body(relatorio);
    }

}
