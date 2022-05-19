package school.sptech.iara.util;

public class Fila {

    private int tamanho;
    private String[] fila;

    // Constructor
    public Fila(int capacidade) {
        fila = new String[capacidade];
        tamanho = 0;
    }

    // Methods
    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(String info) {
        if (isFull()) {
            throw new IllegalStateException("Fila cheia!");
        }
        else {
            fila[tamanho++] = info;
        }

    }

    public String peek() {
        return fila[0];
    }

    public String poll() {
        String primeiro = peek();

        if (!isEmpty()) {
            // Faz a fila andar
            for (int i = 0; i < tamanho-1; i++) {
                fila[i] = fila[i+1];
            }
            tamanho--;
            fila[tamanho] = null;
        }

        return primeiro;
    }

    /* Método exibe() - exibe o conteúdo da fila */
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        }
        else {
            for (int i = 0; i < tamanho; i++) {
                System.out.println(fila[i]);
            }
        }
    }

    public int getTamanho(){
        return tamanho;
    }
}
