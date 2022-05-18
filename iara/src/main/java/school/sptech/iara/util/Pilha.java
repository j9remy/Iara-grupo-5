package school.sptech.iara.util;

public class Pilha {

    // Atributos
    private int[] pilha;
    private int topo;

    // Construtor
    public Pilha(int capacidade) {
        pilha = new int[capacidade];
        topo = -1;
    }

    // Métodos

    public Boolean isEmpty() {
        return topo == -1;

// A instrução acima equivale a esse bloco de instruções
//        if (topo == -1) {
//            return true;
//        }
//        else {
//            return false;
//        }
    }

    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(int info) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
        }
        else {
//            topo++;
//            pilha[topo] = info;
            // as 2 instruções acima equivalem a esta abaixo:
            pilha[++topo] = info;
        }
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
//        int retorno = pilha[topo];
//        topo--;
//        return retorno;

        return pilha[topo--];
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return pilha[topo];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        }
        else {
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }
}
