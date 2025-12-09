import java.util.*;

class No {
    char elemento;
    final int tamanho = 255;
    No[] prox = new No[tamanho];
    boolean folha;

    public No(char elemento) {
        this.elemento = elemento;
        for (int i = 0; i < tamanho; i++)
            prox[i] = null;
        folha = false;
    }

    public static int getHash(char C) {
        return (int) C;
    }
}

class ArvoreTRIE {
    No raiz;

    public ArvoreTRIE() {
        raiz = new No(' ');
    }

    public int contarAs() {
        int resp = 0;
        if (raiz == null)
            return resp;
        resp = contarAs(raiz);
        return resp;
    }

    private int contarAs(No node) {
        int resp = (node.elemento == 'A') ? 1 : 0;

        for (int j = 0; j < node.prox.length; j++) {
            if (node.prox[j] != null) {
                resp += contarAs(node.prox[j]);
            }
        }

        return resp;
    }

    public void inserir(String palavra) {
        inserir(palavra, raiz, 0);
    }

    private void inserir(String palavra, No no, int i) {
        int pos = No.getHash(palavra.charAt(i));
        if (no.prox[pos] == null) {
            no.prox[pos] = new No(palavra.charAt(i));

            if (i == palavra.length() - 1) {
                no.prox[pos].folha = true;
            } else {
                inserir(palavra, no.prox[pos], i + 1);
            }
        } else if (no.prox[pos].folha == false && i < palavra.length() - 1) {
            inserir(palavra, no.prox[pos], i + 1);
        } else {
            throw new Error("ERRO AO INSERIR PALAVRA");
        }
    }

    public void mostrar() {
        mostrar(" ", raiz);
    }

    private void mostrar(String s, No no) {
        if (no.folha == true) {
            System.out.println("Palavra: " + (s + no.elemento));
        } else {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }

    public boolean pesquisar(String palavra) {
        return pesquisar(palavra, raiz, 0);
    }

    private boolean pesquisar(String palavra, No no, int i) {
        int pos = No.getHash(palavra.charAt(i));

        if (no.prox[pos] == null) {
            return false;
        }

        if (i == palavra.length() - 1) {
            return no.prox[pos].folha == true;
        }

        else if (i < palavra.length() - 1) {
            return pesquisar(palavra, no.prox[pos], i + 1);
        }

        throw new Error("Erro ao pesquisar");
    }

}

public class Main {
    public static void main(String[] args) {
        ArvoreTRIE arvore = new ArvoreTRIE();

        arvore.inserir("arroz");
        arvore.inserir("bernardo");
        arvore.inserir("capim");
        arvore.inserir("array");
        arvore.inserir("belo-horizonte");

        if (arvore.pesquisar("arrozes"))
            System.out.println("ACHOU");
        else
            System.out.println("NAO ACHOU");

        arvore.mostrar();
    }
}
