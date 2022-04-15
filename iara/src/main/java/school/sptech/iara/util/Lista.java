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

    public void adiciona(T elemento) {
        if (estaCheio())
            System.out.println("Lista está cheia!");
        else {
            vetor[nroElem] = elemento;
            nroElem++;
        }
    }

    public void adiciona(T[] elementos) {
        for (int i = 0; i < elementos.length; i++)
            adiciona(elementos[i]);
    }

    public boolean indiceExiste(int indice) {
        if (indice >= 0 && indice <= nroElem)
            return true;

        return false;
    }

    public void adiciona(T elemento, int indice) {
        if (!estaCheio() && indiceExiste(indice)) {
            for (int i = nroElem - 1; i >= indice; i--)
                vetor[i + 1] = vetor[i];

            vetor[indice] = elemento;
            nroElem++;
        }
    }

    public void exibe() {
        if (estaVazio())
            System.out.println("\nLista vazia!");
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (contem(elementoBuscado))
                return i;
        }
        return -1;
    }

    public boolean contem(T elemento) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public void atualizaPorElemento(T elementoDaLista, T elementoNovo) {
        if (contem(elementoDaLista))
            vetor[busca(elementoDaLista)] = elementoNovo;
        else
            System.out.println("Elemento não existe na lista");
    }

    public void atualizaPorIndice(int indice, T elemento) {
        if (Objects.nonNull(busca(indice)))
            vetor[indice] = elemento;
        else
            System.out.println("Índice não existe na lista");
    }

    public boolean removePeloIndice (int indice) {
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

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T busca(int indice) {
        if (indice < 0 || indice >= nroElem)
            return null;
        else
            return vetor[indice];
    }

    public void Limpa() {
        nroElem = 0;
    }

    public boolean estaVazio() {
        if (nroElem == 0)
            return true;

        return false;
    }

    public boolean estaCheio() {
        if (getTamanho() >= vetor.length)
            return true;

        return false;
    }


}
