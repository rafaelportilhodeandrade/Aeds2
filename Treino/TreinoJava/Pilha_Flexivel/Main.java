import java.util.*;

class Celula {
    int elemento;
    Celula prox;

    public Celula(int elemento) {
        this.elemento = elemento;
        prox = null;
    }
}

class Pilha {
    Celula topo;

    public Pilha() {
        topo = null;
    }

    public void inserir(int elemento) {
        Celula temp = new Celula(elemento);
        temp.prox = topo;
        topo = temp;
        temp = null;
    }

    public void remover() {
        Celula temp = topo;
        topo = topo.prox;
        temp = temp.prox = null;
    }

    public void mostrar() {
        for (Celula i = topo; i != null; i = i.prox) {
            System.out.println(i.elemento);
        }
    }

    // |---------- ORDENAÇÕES ----------|
    
}

public class Main {
    public static void main(String[] args) {
        Pilha pilha = new Pilha();

        pilha.inserir(5);
        pilha.inserir(2);
        pilha.inserir(1);
        pilha.inserir(4);
        pilha.inserir(3);
        pilha.remover();
        pilha.mostrar();
    }
}