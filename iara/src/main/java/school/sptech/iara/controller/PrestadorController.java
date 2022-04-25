package school.sptech.iara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;
import school.sptech.iara.repository.HabilidadeRepository;
import school.sptech.iara.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.iara.repository.ServicoRepository;
import school.sptech.iara.request.PrestadorHabilidadeRequest;
import school.sptech.iara.request.PrestadorServicoRequest;
import school.sptech.iara.request.UsuarioEmailSenhaRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public ResponseEntity getPrestadorPorIndex(@PathVariable Integer id){
        Optional<Prestador> prestadorOptional = repository.findById(id);
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            return ResponseEntity.status(200).body(prestador);
        }
        return ResponseEntity.status(404).build();
    }

    //cadastro de prestador
    @PostMapping
    public ResponseEntity postCadastrarPrestadores(@RequestBody Prestador prestador){
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
    public ResponseEntity postAutenticarUsuario(@RequestBody @Valid UsuarioEmailSenhaRequest req){

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
    public ResponseEntity deleteDesautenticarUsuario(@RequestBody @Valid UsuarioEmailSenhaRequest req){
        Optional<Prestador> prestadorOptional = repository.findByEmailAndSenha(req.getEmail(), req.getSenha());
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            prestador.logOff(req.getEmail(), req.getSenha());
            repository.save(prestador);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    // adiciona habilidade
    @PostMapping("/habilidade")
    public ResponseEntity postAddHabilidade(@RequestBody PrestadorHabilidadeRequest req){
        Optional<Prestador> prestadorOptional = repository.findById(req.getUserId());
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            if (!prestador.habilidadeExiste(req.getHabilidade())){
                habilidadeRepository.save(req.getHabilidade());
                prestador.addHabilidade(req.getHabilidade());
                repository.save(prestador);
                return ResponseEntity.status(201).build();
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(404).build();
    }

//    adiciona serviço
    @PostMapping("/servico") // revisar fluxo
    public ResponseEntity postAddServico(@RequestBody PrestadorServicoRequest req){
        Optional<Prestador> prestadorOptional = repository.findById(req.getIdPrestador());
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            if (!prestador.servicoExiste(req.getServico())){
                Servico servico = new Servico(req.getServico().getValor(),
                        req.getServico().getDescricao(),
                        req.getServico().getTipo());
                prestador.addServico(servico);
                servicoRepository.save(servico);
                repository.save(prestador);
                return ResponseEntity.status(201).build();
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(404).build();
    }




//    @GetMapping("/relatorio-prestador")
//    public ResponseEntity getRelatorio() {
//        String relatorio = "";
//
//        List<Prestador> lista = repository.findAll();
//
//        for (Prestador prestador : lista) {
//            relatorio += prestador.getId()
//                    + "," + prestador.getNome() + " " + prestador.getSobrenome() +
//                    "," + prestador.getCpf() + "," + prestador.getDataNasc() +
//                    "," + prestador.getEmail() + "," + prestador.getSexo() +
//                    "," + prestador.getTelefone() + "\r\n";
//        }
//
//        return ResponseEntity
//                .status(200)
//                .header("content-type", "text/csv")
//                .header("content-disposition",
//                        "filename=\"Relatorio_Prestador.csv\"")
//                .body(relatorio);
//    }

}
