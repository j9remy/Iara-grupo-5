package school.sptech.iara.util;

import java.util.List;

public class App {
    public static void main(String[] args) {

        Lista<Integer> lista = new Lista<>(10);

        Integer[] aaa = {9,8,7,6};
        System.out.println(lista.size());
        System.out.println(lista.isFull());
        System.out.println(lista.isEmpty());
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);


//        lista.adiciona(aaa);

       lista.add(99,0);

        lista.exibe();



    }
}
