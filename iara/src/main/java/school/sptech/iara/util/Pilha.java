package school.sptech.iara.util;

public class Pilha {

    private int[] pilha;
    private int topo;

    // Constructor
    public Pilha(int capacidade) {
        pilha = new int[capacidade];
        topo = -1;
    }

    // Methods
    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(int info) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
        }
        else {
            pilha[++topo] = info;
        }
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
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
