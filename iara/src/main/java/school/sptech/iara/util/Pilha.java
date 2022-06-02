package school.sptech.iara.util;

public class Pilha <T> {

    // Atributos
    private T[] pilha;
    private int topo;

    // Construtor
    public Pilha(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
    }

    // Métodos

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(T obj) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
        }
        else {
            pilha[++topo] = obj;
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo--];
    }

    public T peek() {
        if (isEmpty()) {
            return null;
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

    public Integer size(){
        return topo;
    }

}
