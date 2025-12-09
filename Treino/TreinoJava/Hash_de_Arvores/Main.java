import java.util.*;

class Doidona {
    T1 t1;

    public Doidona() {
        t1 = new T1();
    }

    public void inserir(int elemento) {
        t1.inserir(elemento);
    }

    public void mostrar() {
        t1.mostrar();
    }

    public void remover(int elemento) {
        t1.remover(elemento);
    }
}

class T1 {
    static final int EMPTY = 0;
    static final int REMOVED = -1;

    int[] hash;
    T2 t2;
    final int tamanho = 5;

    public T1() {
        hash = new int[tamanho];
        t2 = new T2();
    }

    private int getHashT1(int elemento) {
        return elemento % tamanho;
    }

    public void inserir(int elemento) {
        int pos = getHashT1(elemento);
        if (hash[pos] == EMPTY || hash[pos] == REMOVED) {
            hash[pos] = elemento;
        } else {
            t2.inserir(elemento);
        }
    }

    public void mostrar() {
        System.out.println("ELEMENTOS NA T1: ");
        for (int i = 0; i < hash.length; i++) {
            System.out.println(hash[i]);
        }

        t2.mostrar();
    }

    public void remover(int elemento) {
        int pos = getHashT1(elemento);
        if (hash[pos] == elemento) {
            int substituto = t2.getElemento(pos, tamanho);
            if (substituto != 0)
                hash[pos] = substituto;
            else
                hash[pos] = REMOVED;
        } else {
            t2.remover(elemento);
        }
    }
}

class T2 {
    T3 t3;
    Lista lista;
    Arvore arvore;

    public T2() {
        t3 = new T3();
        lista = new Lista();
        arvore = new Arvore();
    }

    private int getHashT2(int elemento) {
        return elemento % 3;
    }

    public void inserir(int elemento) {
        int pos = getHashT2(elemento);

        if (pos == 0) {
            t3.inserir(elemento);
        } else if (pos == 1) {
            lista.inserir(elemento);
        } else if (pos == 2) {
            arvore.inserir(elemento);
        }
    }

    public void mostrar() {
        System.out.println("ELEMENTOS NA LISTA DA T2: ");
        lista.mostrar();
        System.out.println("ELEMENTOS NA ARVORE DA T2: ");
        arvore.mostrar();
        t3.mostrar();
    }

    public int getElemento(int pos, int tamanho) {
        int elemento = 0;
        elemento = t3.getElemento(pos, tamanho);
        if (elemento == 0) {
            elemento = lista.getElemento(pos, tamanho);
        }
        if (elemento == 0) {
            elemento = arvore.getElemento(pos, tamanho);
        }

        return elemento;
    }

    public void remover(int elemento) {
        int bucket = getHashT2(elemento);
        if (bucket == 0) {
            t3.remover(elemento);
        } else if (bucket == 1) {
            lista.remover(elemento);
        } else if (bucket == 2) {
            arvore.remover(elemento);
        }
    }
}

class T3 {
    static final int EMPTY = 0;
    static final int REMOVED = -1;

    int[] hash;
    final int tamanho = 5;
    Arvore arvore;

    public T3() {
        hash = new int[tamanho];
        arvore = new Arvore();
    }

    private int getHashT3(int elemento) {
        return elemento % tamanho;
    }

    private int getReHashT3Index(int index) {
        return (index + 1) % tamanho;
    }

    public void inserir(int elemento) {
        int pos = getHashT3(elemento);
        int pos2 = getReHashT3Index(pos);

        if (hash[pos] == EMPTY || hash[pos] == REMOVED) {
            hash[pos] = elemento;
        } else if (hash[pos2] == EMPTY || hash[pos2] == REMOVED) {
            hash[pos2] = elemento;
        } else {
            arvore.inserir(elemento);
        }
    }

    public void mostrar() {
        System.out.println("ELEMENTOS NA T3: ");
        for (int i = 0; i < hash.length; i++) {
            System.out.println(hash[i]);
        }

        System.out.println("ELEMENTOS NA ARVORE DA T3: ");
        arvore.mostrar();
    }

    public int getElemento(int pos, int tam) {
        int elemento = 0;
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i];
            if (v != EMPTY && v != REMOVED && v % tam == pos) {
                elemento = v;

                hash[i] = REMOVED;
                return elemento;
            }
        }

        elemento = arvore.getElemento(pos, tam);
        return elemento;
    }

    public void remover(int elemento) {
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == elemento) {
                hash[i] = REMOVED;
                return;
            }
        }

        try {
            arvore.remover(elemento);
        } catch (Error e) {

        }
    }
}

class No {
    int elemento;
    No esq;
    No dir;

    public No(int elemento) {
        this.elemento = elemento;
        esq = null;
        dir = null;
    }
}

class Arvore {
    No raiz;

    public Arvore() {
        raiz = null;
    }

    public void inserir(int elemento) {
        raiz = inserir(elemento, raiz);
    }

    private No inserir(int elemento, No i) {
        if (i == null)
            i = new No(elemento);
        else if (i.elemento > elemento)
            i.esq = inserir(elemento, i.esq);
        else if (i.elemento < elemento)
            i.dir = inserir(elemento, i.dir);
        else
            throw new Error("Elemento ja existe");

        return i;
    }

    public void mostrar() {
        mostrar(raiz);
    }

    private void mostrar(No i) {
        if (i != null) {
            mostrar(i.esq);
            System.out.println(i.elemento);
            mostrar(i.dir);
        }
    }

    public int getElemento(int pos, int tam) {
        return getElemento(pos, tam, raiz);
    }

    private int getElemento(int pos, int tam, No i) {
        if (i == null)
            return 0;

        int left = getElemento(pos, tam, i.esq);
        if (left != 0)
            return left;

        if (i.elemento % tam == pos) {
            int elemento = i.elemento;
            remover(elemento);
            return elemento;
        }

        int right = getElemento(pos, tam, i.dir);
        if (right != 0)
            return right;

        return 0;
    }

    public void remover(int elemento) {
        raiz = remover(elemento, raiz);
    }

    private No remover(int elemento, No i) {
        if (i == null)
            throw new Error("Erro");
        else if (elemento < i.elemento)
            i.esq = remover(elemento, i.esq);
        else if (elemento > i.elemento)
            i.dir = remover(elemento, i.dir);
        else if (i.dir == null)
            i = i.esq;
        else if (i.esq == null)
            i = i.dir;
        else {
            i.dir = menorDir(i, i.dir);
        }

        return i;
    }

    public No menorDir(No i, No j) {
        if (j.esq == null) {
            i.elemento = j.elemento;
            j = j.dir;
        } else
            j.esq = menorDir(i, j.esq);

        return j;
    }
}

class Celula {
    int elemento;
    Celula prox;

    public Celula(int elemento) {
        this.elemento = elemento;
        prox = null;
    }
}

class Lista {
    Celula primeiro, ultimo;

    public Lista() {
        primeiro = ultimo = new Celula(0);
    }

    public void inserir(int elemento) {
        Celula temp = new Celula(elemento);

        ultimo.prox = temp;
        ultimo = temp;

        temp = null;
    }

    public void mostrar() {
        Celula i = primeiro.prox;

        for (; i != null; i = i.prox) {
            System.out.println(i.elemento);
        }
    }

    public int getElemento(int pos, int tam) {
        Celula i = primeiro;
        Celula temp = primeiro.prox;
        while (temp != null) {
            if (temp.elemento % tam == pos) {
                int elemento = temp.elemento;

                i.prox = temp.prox;
                if (temp == ultimo)
                    ultimo = i;
                return elemento;
            }
            i = temp;
            temp = temp.prox;
        }
        return 0;
    }

    public void remover(int elemento) {
        Celula i = primeiro;
        Celula temp = primeiro.prox;

        while (temp != null && temp.elemento != elemento) {
            i = temp;
            temp = temp.prox;
        }

        if (temp != null) {
            i.prox = temp.prox;
            if (temp == ultimo)
                ultimo = i;
            temp.prox = null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Doidona doidona = new Doidona();

        int[] elementos = { 23, 12, 344, 87, 32, 21, 99, 65, 34, 132, 30, 20 };

        for (int i = 0; i < elementos.length; i++) {
            doidona.inserir(elementos[i]);
        }

        doidona.remover(32);

        doidona.mostrar();
    }
}
