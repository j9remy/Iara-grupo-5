package school.sptech.iara.util;

import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.repository.ClienteRepository;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
        }

        // try-catch para gravar o registro e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo: " + erro);
        }
    }

    public static void gravaArquivoTxt(List<Cliente> lista, String nomeArq) {
        int contaRegCorpo = 0;

        // Monta o registro de header
        String header = "00CLIENTE2022";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        String corpo;
        for (Cliente c : lista) {
            corpo = "02";
            corpo += String.format("-5.5s", c.getNome());
            corpo += String.format("-5.5s", c.getSobrenome());
            corpo += String.format("-5.5s", c.getCpf());
            corpo += String.format("-5.5s", c.getDataNasc());
            corpo += String.format("-5.5s", c.getSexo());
            corpo += String.format("-5.5s", c.getEmail());
            corpo += String.format("-5.5s", c.getTelefone());
            contaRegCorpo++;
            gravaRegistro(corpo, nomeArq);
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegCorpo);
        gravaRegistro(trailer, nomeArq);
    }

    public static void leArquivoTxt(String nomeArq) {

        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, sobrenome, cpf, dataNasc, sexo, email, telefone;
        int contaRegCorpoLido = 0;
        int qtdRegCorpoGravado;

        List<Cliente> listaLida = new ArrayList<>();

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
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
                    dataNasc = registro.substring(97,115).trim();
                    sexo = registro.substring(116).trim();
                    email = registro.substring(117,166).trim();
                    telefone = registro.substring(167,181).trim();
                    contaRegCorpoLido++;

                    Cliente c = new Cliente(nome,
                            sobrenome,
                            cpf,
                            dataNasc,
                            email,
                            sexo,
                            senha,
                            telefone);

                    // No projeto de PI, poderia fazer:
                    // repository.save(a);

                    // No nosso caso, vamos adicionar o objeto a na listaLida:
                    listaLida.add(c);
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
        }

        // Vamos exibir a listaLida
        System.out.println("\nConteúdo da lista lida:");
        for (Cliente c : listaLida) {
            System.out.println(c);
        }

    }

    @Autowired
    private static ClienteRepository repository;

    public static void main(String[] args) {
        repository.findAll();

        gravaArquivoTxt(repository, "Cliente.txt");
        leArquivoTxt("Cliente.txt");
    }

}

