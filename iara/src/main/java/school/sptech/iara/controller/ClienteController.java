package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.AvaliacaoCliente;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Endereco;
import school.sptech.iara.repository.AvaliacaoRepository;
import school.sptech.iara.repository.ClienteRepository;
import school.sptech.iara.repository.EnderecoRepository;
import school.sptech.iara.request.*;
import school.sptech.iara.response.UsuarioAvaliacaoResponse;
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
    public ResponseEntity<List<Cliente>> getCliente(){
        List<Cliente> clientes = repository.findAll();
        if (!clientes.isEmpty()){
            return ResponseEntity.status(200).body(clientes);
        }
        return ResponseEntity.status(204).build();
    }

    // retorna usuário pelo index
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientePorId(@PathVariable int id){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return ResponseEntity.status(200).body(cliente);
        }
        return ResponseEntity.status(404).build();
    }

    //cadastro de clientes, com possibilidade de cadastrar vários de uma só vez
    @PostMapping
    public ResponseEntity<Void> postCadastroClientes(@RequestBody Cliente cliente){
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
    public ResponseEntity<UsuarioAvaliacaoResponse> getAvaliacao(@PathVariable Integer id){
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
    public ResponseEntity<Void> postCadastroAvaliacao(@RequestBody ClienteIdAvaliacaoRequest req){
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
    public ResponseEntity<Void> postAutenticarUsuario(@RequestBody @Valid UsuarioEmailSenhaRequest req){

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
    public ResponseEntity<Void> deleteLogOffUsuario(@RequestBody @Valid UsuarioEmailSenhaRequest req){
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
    public ResponseEntity<Void> putCliente(@RequestBody @Valid ClienteUpdateRequest req){
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
    public ResponseEntity<Void> postEnderecoCliente(@PathVariable Integer idCliente,
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
//    @ApiResponses({
//        @ApiResponse(responseCode = "204", description = "vazio"),
//
//    })
    public ResponseEntity<Void> patchFoto(@PathVariable Integer idCliente,
                                    @RequestBody byte[] novaFoto) {
        Optional<Cliente> clienteOptional = repository.findById(idCliente);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setFoto(novaFoto);
            repository.save(cliente);
            return ResponseEntity.status(200).build();
        }
            return ResponseEntity.status(404).build();
    }

    @GetMapping("/registro/{nomeArq}")
    public ResponseEntity<Void> postRegistro(@PathVariable String nomeArq) {
        List<Cliente> lista = repository.findAll();
        GravaArquivo gravaArq = new GravaArquivo();
        int contaRegCorpo = 0;

        // Monta o registro de header
        String header = "CLIENTE";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaArq.gravaRegistro(header, nomeArq);

        String corpo;
        for (Cliente c : lista) {
            corpo = "02";
            corpo += String.format("%-30.30s", c.getNome());
            corpo += String.format("%-50.50s", c.getSobrenome());
            corpo += String.format("%-11.11s", c.getCpf());
            corpo += String.format("%-19.19s", c.getDataNasc());
            corpo += String.format("%-1.1s", c.getSexo());
            corpo += String.format("%-50.50s", c.getEmail());
            corpo += String.format("%-15.15s", c.getTelefone());
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
    public ResponseEntity<Void> getRegistro(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, sobrenome, cpf, email, senha, telefone;
        Timestamp dataNasc;
        char sexo;
        int contaRegCorpoLido = 0;
        int qtdRegCorpoGravado;

        List<Cliente> listaLida = new ArrayList<>();

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
                switch (tipoRegistro) {
                    case "00":
                        System.out.println("É um registro de header");
                        System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
                        System.out.println("Ano e semestre: " + registro.substring(6, 11));
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
                    case "00002":
                        System.out.println("É um registro de corpo");
                        nome = registro.substring(6, 35).trim();
                        sobrenome = registro.substring(36, 85).trim();
                        cpf = registro.substring(86, 96).trim();
                        dataNasc = Timestamp.valueOf(registro.substring(97, 115).trim());
                        sexo = registro.charAt(116);
                        email = registro.substring(117, 166).trim();
                        senha = registro.substring(167, 177).trim();
                        telefone = registro.substring(178, 191).trim();
                        contaRegCorpoLido++;

                        Cliente c = new Cliente(nome,
                                sobrenome,
                                cpf,
                                dataNasc,
                                email,
                                senha,
                                sexo,
                                telefone);

                        // No projeto de PI, poderia fazer:
                        // repository.save(a);

                        // No nosso caso, vamos adicionar o objeto a na listaLida:
                        repository.save(c);
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
        for (Cliente c : listaLida) {
            System.out.println(c);
        }
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity<String> getRelatorio() {
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
