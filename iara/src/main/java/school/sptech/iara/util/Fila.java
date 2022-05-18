package school.sptech.iara.util;

public class Fila {
    // Atributos
    private int tamanho;
    private String[] fila;

    // Construtor
    public Fila(int capacidade) {
        fila = new String[capacidade];
        tamanho = 0;
    }

    // Métodos

    /* Método isEmpty() - retorna true se a fila está vazia e false caso contrário */
    public boolean isEmpty() {
        return tamanho == 0;
    }

    /* Método isFull() - retorna true se a fila está cheia e false caso contrário */
    public boolean isFull() {
        return tamanho == fila.length;
    }

    /* Método insert - recebe um elemento e insere esse elemento na fila
                       no índice tamanho, e incrementa tamanho
                       Retornar IllegalStateException caso a fila esteja cheia
     */
    public void insert(String info) {
        if (isFull()) {
            throw new IllegalStateException("Fila cheia!");
        }
        else {
            fila[tamanho++] = info;
        }

    }

    /* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
    public String peek() {
        return fila[0];
    }

    /* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
       vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
     */
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
