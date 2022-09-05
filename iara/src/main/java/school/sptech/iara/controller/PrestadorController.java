package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.*;
import school.sptech.iara.repository.*;
import school.sptech.iara.request.EnderecoSimplesRequest;
import school.sptech.iara.request.PrestadorUpdateRequest;
import school.sptech.iara.request.UsuarioEmailSenhaRequest;
import school.sptech.iara.response.PrestadorAvaliacaoResponse;
import school.sptech.iara.util.GravaArquivo;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestador")
@CrossOrigin
public class PrestadorController {

    @Autowired
    private PrestadorRepository repository;
    @Autowired
    private HabilidadeRepository habilidadeRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ServicoAtribuidoRepository servicoAtribuidoRepository;
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PortifolioRepository portifolioRepository;
    @Autowired
    private ServicoController servicoController;


    // retorna todos registros de prestadores
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK, retorna uma lista de prestadores"),
            @ApiResponse(responseCode = "204", description = "A lista de prestadores está vazia")
    })
    public ResponseEntity<List<Prestador>> getListaPrestadores(){
        List<Prestador> prestadores = repository.findAll();
        if (!prestadores.isEmpty()){
            return ResponseEntity.status(200).body(prestadores);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna usuário pelo index
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna prestador com o id procurado"),
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado")
    })
    public ResponseEntity<Prestador> getPrestadorPorId(@PathVariable Integer id){
        Optional<Prestador> prestadorOptional = repository.findById(id);
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            return ResponseEntity.status(200).body(prestador);
        }
        return ResponseEntity.status(404).build();
    }

    //cadastro de prestador
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prestador cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Prestador já existe ou possui dados inválidos")
    })
    public ResponseEntity<Void> postCadastrarPrestador(@RequestBody Prestador prestador){
        List<Prestador> prestadoresInvalidos = repository.validarCadastro(
                prestador.getEmail(), prestador.getCpf(), prestador.getTelefone()
        );

        if (prestadoresInvalidos.isEmpty()){
            repository.save(prestador);
            Agenda agenda = new Agenda(prestador);
            agendaRepository.save(agenda);
            portifolioRepository.save(new Portifolio(prestador));
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    //Autenticar usuário
    @PostMapping("/autenticacao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prestador autenticado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado")
    })
    public ResponseEntity<Void> postAutenticarPrestador(@RequestBody @Valid UsuarioEmailSenhaRequest req){
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prestador desautenticado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado")
    })
    public ResponseEntity<Void> deleteLogoffPrestador(@RequestBody @Valid UsuarioEmailSenhaRequest req){
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prestador atualizado com sucesso"),
            @ApiResponse(responseCode = "206", description = "Prestador atualizado com sucesso, com a exceção do " +
                    "campo telefone, pois esse número já está cadastrado"),
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado")
    })
    public ResponseEntity<Void> putPrestador(@RequestBody @Valid PrestadorUpdateRequest req){
        Optional<Prestador> prestadorOptional = repository.findById(req.getId());
        if(prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            prestador.setNome(req.getNome());
            prestador.setSobrenome(req.getSobrenome());
            prestador.setDataNasc(req.getDataNasc());
            prestador.setSenha(req.getSenha());
            prestador.setGenero(req.getGenero());
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Endereço já existe")
    })
    public ResponseEntity<Void> postEnderecoCliente(@PathVariable Integer idPrestador,
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

//    @GetMapping("/agenda/{idPrestador}")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
//            @ApiResponse(responseCode = "204", description = "Busca realizada com sucesso, porém não há serviços não atendidos"),
//            @ApiResponse(responseCode = "404", description = "Prestador não encontrado")
//    })
//    public ResponseEntity<List<ServicoAtribuidoResponse>> getAgenda(@PathVariable Integer idPrestador){
//        Optional<Prestador> prestadorOptional = repository.findById(idPrestador);
//        if (prestadorOptional.isPresent()){
//            Prestador prestador = prestadorOptional.get();
//            List<ServicoAtribuido> servicos = servicoAtribuidoRepository.
//                    findAllByFinalizadoAndServico_AtivoAndServico_PrestadorOrderByDataHoraInicio(false,true,prestador);
//            if (!servicos.isEmpty()){
//                List<ServicoAtribuidoResponse> servicoAtribuidoResponses = new ArrayList<>();
//                for (ServicoAtribuido serv: servicos){
//                    servicoAtribuidoResponses.add(serv.formatarResposta());
//                }
//                return ResponseEntity.status(200).body(servicoAtribuidoResponses);
//            }
//            return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(404).build();
//    }

    @GetMapping(value = "/foto/{idPrestador}", produces = "image/jpeg")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Foto inválida"),
            @ApiResponse(responseCode = "200", description = "Retorna a foto do prestador solicitado")
    })
    public ResponseEntity<byte[]> getFoto(@PathVariable Integer idPrestador) {
        byte[] foto = repository.getFoto(idPrestador);
        if (foto == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(foto);
    }

    @GetMapping("/avaliacao/{idPrestador}")
    public ResponseEntity<PrestadorAvaliacaoResponse> getAvaliacao(@PathVariable Integer idPrestador){
        Optional<Prestador> prestadorOptional = repository.findById(idPrestador);
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            List<ServicoAtribuido> servicoAtribuidos = servicoAtribuidoRepository.
                    findAllByServico_PrestadorAndStatus(prestador, "Finalizado");
            if (!servicoAtribuidos.isEmpty()){
                Double soma = 0d;
                Integer contagem = 0;
                for (ServicoAtribuido serv: servicoAtribuidos){
                    if (serv.getAvaliacao() >= 0 && serv.getAvaliacao() <= 5){
                        soma += serv.getAvaliacao();
                        contagem++;
                    }
                }
                Double media = soma / contagem;
                if(media >= 0 && media <= 5){
                    return ResponseEntity.status(200).body(new PrestadorAvaliacaoResponse(prestador.getId(), media));
                }
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PatchMapping(value = "/foto/{idPrestador}", consumes = "image/jpeg")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Prestador não encontrado"),
            @ApiResponse(responseCode = "200", description = "Foto atualizada com sucesso")
    })
    public ResponseEntity patchFoto(@PathVariable Integer idPrestador,
                                    @RequestBody byte[] novaFoto) {
        if (!repository.existsById(idPrestador)) {
            return ResponseEntity.status(404).build();
        }
        repository.atualizarFoto(idPrestador, novaFoto);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/registro/{nomeArq}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arquivo gerado com sucesso")
    })
    public ResponseEntity<Void> getRegistro(@PathVariable String nomeArq) {
        List<Prestador> lista = repository.findAll();
        GravaArquivo gravaArq = new GravaArquivo();
        int contaRegCorpo = 0;

        // Monta o registro de header
        String header = "00PRESTADOR";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaArq.gravaRegistro(header, nomeArq + ".txt");

        String corpo;
        for (Prestador p : lista) {
            corpo = "02";
            corpo += String.format("%-30.30s", p.getNome());
            corpo += String.format("%-50.50s", p.getSobrenome());
            corpo += String.format("%-11.11s", p.getCpf());
            corpo += String.format("%-19.19s", p.getDataNasc());
            corpo += String.format("%-1.1s", p.getGenero());
            corpo += String.format("%-50.50s", p.getEmail());
            corpo += String.format("%-15.15s", p.getTelefone());
            corpo += String.format("%-140.140s", p.getResumo());
            corpo += String.format("%-5.5s", p.getAtendeDomicilio());
            corpo += String.format("%-5.5s", p.getAtendeEstabelecimento());
            corpo += String.format("%4.2f", p.getDistancia());
            contaRegCorpo++;
            gravaArq.gravaRegistro(corpo, nomeArq + ".txt");
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegCorpo);
        gravaArq.gravaRegistro(trailer, nomeArq + ".txt");

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/registro/{nomeArq}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prestadores cadastrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Arquivo não encontrado")
    })
    public ResponseEntity<Void> postRegistro(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, sobrenome, cpf, email, senha, telefone, resumo;
        LocalDate dataNasc;
        Double distancia;
        char sexo;
        boolean atendeDomicilio, atendeEstabelecimento;
        int contaRegCorpoLido = 0;
        int qtdRegCorpoGravado;

        List<Prestador> listaLida = new ArrayList<>();

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq + ".txt"));
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
                switch (tipoRegistro) {
                    case "00":
                        System.out.println("Tipo de arquivo: " + registro.substring(2, 11));
                        System.out.println("Data e hora da gravação: " + registro.substring(11, 30));
                        System.out.println("Versão do documento: " + registro.substring(30, 32));
                        break;
                    case "01":
                        System.out.println("É um registro de trailer");
                        qtdRegCorpoGravado = Integer.parseInt(registro.substring(2, 12));
                        if (contaRegCorpoLido == qtdRegCorpoGravado) {
                            System.out.println("Quantidade de registros lidos é compatível " +
                                    "com a quantidade de registros gravados");
                        } else {
                            System.out.println("Quantidade de registros lidos não é compatível " +
                                    "com a quantidade de registros gravados");
                        }
                        break;
                    case "02":
                        nome = registro.substring(2, 32).trim();
                        sobrenome = registro.substring(32, 82).trim();
                        cpf = registro.substring(82, 93).trim();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        dataNasc = LocalDate.parse(registro.substring(93, 103), formatter);
                        sexo = registro.charAt(112);
                        email = registro.substring(113, 163).trim();
                        telefone = registro.substring(163, 178).trim();
                        senha = "abcde";
                        resumo = registro.substring(178, 318).trim();
                        atendeDomicilio = Boolean.parseBoolean(registro.substring(318, 322).trim());
                        atendeEstabelecimento = Boolean.parseBoolean(registro.substring(322, 328).trim());
                        distancia = Double.parseDouble(registro.substring(328, 332).replace(',','.'));
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
                                atendeDomicilio,
                                atendeEstabelecimento,
                                distancia
                                );

                        // No projeto de PI, poderia fazer:
                        // repository.save(a);

                        // No nosso caso, vamos adicionar o objeto a na listaLida:
                        repository.save(p);
                        listaLida.add(p);
                        break;
                    default:
                        System.out.println("Tipo de registro inválido!");
                        break;
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
    @ApiResponse(responseCode = "200", description = "Arquivo gerado com sucesso")
    public ResponseEntity<String> getRelatorio() {
        String relatorio = "";
        List<Prestador> lista = repository.findAll();
        for (Prestador prestador : lista) {
            relatorio += prestador.getId()
                    + ";" + prestador.getNome() + " " + prestador.getSobrenome() +
                    ";" + prestador.getCpf() +
                    "," + prestador.getDataNasc() +
                    ";" + prestador.getEmail() +
                    ";" + prestador.getGenero() +
                    ";" + prestador.getTelefone() +
                    ";" + prestador.getResumo() +
                    ";" + prestador.getAtendeDomicilio() +
                    ";" + prestador.getAtendeEstabelecimento() +
                    ";" + prestador.getDistancia() +
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
