package school.sptech.iara.model;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        //Endereço
        Endereco endereco1 = new Endereco("cep","rua","num",
                "bairro","cidade", "SP","Complemento");
        Endereco endereco2 = new Endereco("cep2","rua2","num2",
                "bairro2","cidade2", "SP2","Complemento2");

        //Cliente
        Cliente cl = new Cliente("Lucas","Teixeira",
                "123123", LocalDate.of(2003,9,07),
                "lucas@email.com","senha",'M', "telefone",endereco1);

        cl.addAvaliacao(5);
        cl.addAvaliacao(4);
        System.out.println(cl.getAvaliacao()); // Esperado 4.5

        //Servico pt1
        Servico s1 = new Servico(45.0,"Corte masculino + degradê","Corte");
        Servico s2 = new Servico(146.9,"Pacote de unha completo","Unha");
        s1.setAtivo(false);

        //Habilidade
        Habilidade hab1 = new Habilidade("Corte", "Masculino");
        Habilidade hab2 = new Habilidade("Unha", "Gel");
        Habilidade hab3 = new Habilidade("Depilação", "Cera");
        System.out.println(hab2.toString());

        //Serviços atribuidos
        ServicoAtribuido sa1 = new ServicoAtribuido(cl);
        ServicoAtribuido sa2 = new ServicoAtribuido(cl);

        sa1.finalizarServico();
        sa2.finalizarServico();
        sa1.addAvaliacao(5.0);
        sa2.addAvaliacao(4.0);
        System.out.println("\nAvaliação serviço prestado: " + sa1.getAvaliacao());
        System.out.println(sa1.toString());

        //Usuario
        cl.autenticar("lucas@email.com","senha");
        System.out.println("\n" + cl.isAutenticado());
        cl.logOff("lucas@email.com","senha");
        System.out.println(cl.isAutenticado());

        //Serviço pt2
        s1.addServicoAtribuido(sa1);
        s1.addServicoAtribuido(sa2);
        System.out.println("\nqtdServico attr: " + s1.getQtdServicosAtribuidos());
        System.out.println("Lista serv attr: " + s1.getServicoAtribuidos());
        System.out.println("getAvaliacao serviço: " + s1.getAvaliacao());
        s1.atualizarServico(30.0,"Corte masculino + degradê","Corte");
        System.out.println("preço alterado para 30: " + s1.toString());

        //Prestador
        Prestador pr = new Prestador("Rogerio", "Silva",
                "3213123",LocalDate.of(1970,2,14),
                "rogerio@email.com","senha2", 'M', "0000000", endereco2,
                "Esse é um resumo");
        pr.addHabilidade(hab1);
        pr.addHabilidade(hab2);
        pr.addHabilidade(hab3);

        pr.addServico(s1);
        pr.addServico(s2);

        System.out.println(pr.getServicos());
        System.out.println("\nAvaliação: " + pr.getAvaliacao());
        System.out.println(pr.getHabilidades());
        System.out.println(pr.getServicos());
        System.out.println(pr.getServicosAtivos());
    }
}
