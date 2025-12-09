import java.util.*;

class No {
    char elemento;
    final int tamanho = 255;
    No[] prox = new No[tamanho];
    boolean fim;

    public No(char elemento) {
        this.elemento = elemento;
        for (int i = 0; i < tamanho; i++)
            prox[i] = null;
        fim = false;
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

    // ============================================================
    // INSERIR – agora insere TODOS os sufixos
    // ============================================================
    public void inserir(String palavra) {
        for (int i = 0; i < palavra.length(); i++) {
            inserirSufixo(palavra, i);
        }
    }

    private void inserirSufixo(String palavra, int inicio) {
        inserirRec(palavra, raiz, inicio);
    }

    private void inserirRec(String palavra, No no, int i) {
        int pos = No.getHash(palavra.charAt(i));

        if (no.prox[pos] == null) {
            no.prox[pos] = new No(palavra.charAt(i));
        }

        if (i == palavra.length() - 1) {
            no.prox[pos].fim = true;
        } else {
            inserirRec(palavra, no.prox[pos], i + 1);
        }
    }

    // ============================================================
    // PESQUISAR – funciona para qualquer sufixo
    // ============================================================
    public boolean pesquisar(String palavra) {
        return pesquisarRec(palavra, raiz, 0);
    }

    private boolean pesquisarRec(String palavra, No no, int i) {
        int pos = No.getHash(palavra.charAt(i));

        if (no.prox[pos] == null) {
            return false;
        }

        if (i == palavra.length() - 1) {
            return no.prox[pos].fim;
        }

        return pesquisarRec(palavra, no.prox[pos], i + 1);
    }

    // ============================================================
    // REMOVER – remove somente quando o nó não é compartilhado
    // ============================================================
    public void remover(String palavra) {
        removerRec(palavra, raiz, 0);
    }

    private boolean removerRec(String palavra, No no, int i) {
        if (no == null) return false;

        int pos = No.getHash(palavra.charAt(i));

        No filho = no.prox[pos];
        if (filho == null) return false;

        boolean apagarFilho = false;

        if (i == palavra.length() - 1) {
            filho.fim = false;
            apagarFilho = naoTemFilhos(filho);
        } else {
            apagarFilho = removerRec(palavra, filho, i + 1) && !filho.fim;
        }

        if (apagarFilho) {
            no.prox[pos] = null;
            return naoTemFilhos(no);
        }

        return false;
    }

    private boolean naoTemFilhos(No no) {
        for (int i = 0; i < no.prox.length; i++) {
            if (no.prox[i] != null) return false;
        }
        return true;
    }

    // ============================================================
    // MOSTRAR – imprime todas as palavras armazenadas
    // ============================================================
    public void mostrar() {
        mostrarRec("", raiz);
    }

    private void mostrarRec(String prefixo, No no) {
        if (no.fim) {
            System.out.println(prefixo + no.elemento);
        }

        for (int i = 0; i < no.prox.length; i++) {
            if (no.prox[i] != null) {
                mostrarRec(prefixo + no.elemento, no.prox[i]);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ArvoreTRIE arvore = new ArvoreTRIE();

        arvore.inserir("arroz");
        arvore.inserir("bernardo");
        arvore.inserir("capim");

        System.out.println(arvore.pesquisar("roz") ? "ACHOU" : "NAO ACHOU");

        arvore.remover("roz");

        System.out.println(arvore.pesquisar("roz") ? "ACHOU" : "NAO ACHOU");

        arvore.mostrar();
    }
}
