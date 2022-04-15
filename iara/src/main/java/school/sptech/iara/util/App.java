package school.sptech.iara.util;

import java.util.List;

public class App {
    public static void main(String[] args) {

        Lista<Integer> lista = new Lista<>(10);

        Integer[] aaa = {9,8,7,6};
        System.out.println(lista.getTamanho());
        System.out.println(lista.estaCheio());
        System.out.println(lista.estaVazio());
        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(3);
        lista.adiciona(4);


//        lista.adiciona(aaa);

       lista.adiciona(99,0);

        lista.exibe();



    }
}
