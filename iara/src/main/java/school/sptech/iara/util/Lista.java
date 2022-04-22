package school.sptech.iara.util;

import java.util.Objects;

public class Lista <T>{

    private T[] vetor;
    private Integer nroElem;

    public Lista(Integer tamanho) {
        vetor = (T[]) new Object[tamanho];
        this.nroElem = 0;
    }

    // Métodos

    public void add(T elemento) {
        if (isFull())
            System.out.println("Lista está cheia!");
        else {
            vetor[nroElem] = elemento;
            nroElem++;
        }
    }
    public void add(T elemento, int indice) {
        if (!isFull() && exists(indice)) {
            for (int i = nroElem - 1; i >= indice; i--)
                vetor[i + 1] = vetor[i];

            vetor[indice] = elemento;
            nroElem++;
        }
    }
    public void add(T[] elementos) {
        for (int i = 0; i < elementos.length; i++)
            add(elementos[i]);
    }

    public int getElement(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (contains(elementoBuscado))
                return i;
        }
        return -1;
    }
    public T getElement(int indice) {
        if (indice < 0 || indice >= nroElem)
            return null;
        else
            return vetor[indice];
    }

    public void exibe() {
        if (isEmpty())
            System.out.println("\nLista vazia!");
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public void update(T elementoDaLista, T elementoNovo) {
        if (contains(elementoDaLista))
            vetor[getElement(elementoDaLista)] = elementoNovo;
        else
            System.out.println("Elemento não existe na lista");
    }
    public void update(int indice, T elemento) {
        if (Objects.nonNull(getElement(indice)))
            vetor[indice] = elemento;
        else
            System.out.println("Índice não existe na lista");
    }

    public boolean remove(int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return false;
        }
        for (int i = indice; i < nroElem-1; i++) {
            vetor[i] = vetor[i+1];
        }
        nroElem--;
        return true;
    }

    public boolean remove(T elementoARemover) {
        return remove(getElement(elementoARemover));
    }

    public boolean exists(int indice) {
        if (indice >= 0 && indice <= nroElem)
            return true;

        return false;
    }

    public boolean contains(T elemento) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }



    public int size() {
        return nroElem;
    }
    public void clear() {
        nroElem = 0;
    }
    public boolean isEmpty() {
        return nroElem == 0;
    }
    public boolean isFull() {
        return size() >= vetor.length;
    }


}
