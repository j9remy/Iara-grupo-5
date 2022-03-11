package school.sptech.iara.model;

public class App {
    public static void main(String[] args) {
        Endereco endereco1 = new Endereco("cep","rua","num","bairro","cidade", "SP","Complemento");


        System.out.println(endereco1.toString());
    }
}
