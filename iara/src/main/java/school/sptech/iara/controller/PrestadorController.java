package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Endereco;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.repository.EnderecoRepository;
import school.sptech.iara.repository.HabilidadeRepository;
import school.sptech.iara.repository.PrestadorRepository;
import school.sptech.iara.repository.ServicoRepository;
import school.sptech.iara.request.EnderecoSimplesRequest;
import school.sptech.iara.request.PrestadorUpdateRequest;
import school.sptech.iara.request.UsuarioEmailSenhaRequest;
import school.sptech.iara.util.GravaArquivo;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                                              @RequestBody EnderecoSimplesRequest enderecoRequest){
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

    @GetMapping("/registro/{nomeArq}")
    public ResponseEntity postRegistro(@PathVariable String nomeArq) {
        List<Prestador> lista = repository.findAll();
        GravaArquivo gravaArq = new GravaArquivo();
        int contaRegCorpo = 0;

        // Monta o registro de header
        String header = "PRESTADOR";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaArq.gravaRegistro(header, nomeArq);

        String corpo;
        for (Prestador p : lista) {
            corpo = "02";
            corpo += String.format("%-5.5s", p.getNome());
            corpo += String.format("%-5.5s", p.getSobrenome());
            corpo += String.format("%-5.5s", p.getCpf());
            corpo += String.format("%-5.5s", p.getDataNasc());
            corpo += String.format("%-5.5s", p.getSexo());
            corpo += String.format("%-5.5s", p.getEmail());
            corpo += String.format("%-5.5s", p.getTelefone());
            corpo += String.format("%-5.5s", p.getResumo());
            corpo += String.format("%-5.5s", p.getAtendeDomicilio());
            contaRegCorpo++;
            gravaArq.gravaRegistro(corpo, nomeArq);
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegCorpo);
        gravaArq.gravaRegistro(trailer, nomeArq);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/registro/{nomeArq}")
    public ResponseEntity getRegistro(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, sobrenome, cpf, email, senha, telefone, resumo;
        Timestamp dataNasc;
        Character sexo;
        Boolean atendeDomicilio;
        int contaRegCorpoLido = 0;
        int qtdRegCorpoGravado;

        List<Prestador> listaLida = new ArrayList<>();

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
            return ResponseEntity.status(400).build();
        }

        try {
            // Leitura do primeiro registro do arquivo
            registro = entrada.readLine();

            while (registro != null) { // enquanto não chegou ao final do arquivo
                // obtém os 2 primeiros caracteres do registro
                // 01234567
                // 00NOTA20221
                tipoRegistro = registro.substring(0,2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2,6));
                    System.out.println("Ano e semestre: " + registro.substring(6,11));
                    System.out.println("Data e hora da gravação: " + registro.substring(11,30));
                    System.out.println("Versão do documento: " + registro.substring(30,32));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("É um registro de trailer");
                    qtdRegCorpoGravado = Integer.parseInt(registro.substring(2,12));
                    if (contaRegCorpoLido == qtdRegCorpoGravado) {
                        System.out.println("Quantidade de registros lidos é compatível " +
                                "com a quantidade de registros gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros lidos não é compatível " +
                                "com a quantidade de registros gravados");
                    }
                }
                else if (tipoRegistro.equals("00002")) {
                    System.out.println("É um registro de corpo");
                    nome = registro.substring(6,35).trim();
                    sobrenome = registro.substring(36,85).trim();
                    cpf = registro.substring(86,96).trim();
                    dataNasc = Timestamp.valueOf(registro.substring(97,115).trim());
                    sexo = registro.charAt(116);
                    email = registro.substring(117,166).trim();
                    senha = registro.substring(167,177).trim();
                    telefone = registro.substring(178,191).trim();
                    resumo = registro.substring(192,391).trim();
                    atendeDomicilio = Boolean.valueOf(registro.substring(392,393).trim());
                    contaRegCorpoLido++;

                    Prestador p = new Prestador(nome,
                            sobrenome,
                            cpf,
                            dataNasc,
                            email,
                            senha,
                            sexo,
                            telefone,
                            resumo,
                            atendeDomicilio);

                    // No projeto de PI, poderia fazer:
                    // repository.save(a);

                    // No nosso caso, vamos adicionar o objeto a na listaLida:
                    repository.save(p);
                }
                else {
                    System.out.println("Tipo de registro inválido!");
                }

                // Lê o próximo registro
                registro = entrada.readLine();
            }

            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo: " + erro);
            return ResponseEntity.status(400).build();
        }

        // Vamos exibir a listaLida
        System.out.println("\nConteúdo da lista lida:");
        for (Prestador p : listaLida) {
            System.out.println(p);
        }
        return ResponseEntity.status(201).build();
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
