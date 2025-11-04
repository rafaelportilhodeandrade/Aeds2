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
    private Celula topo;

    public Pilha() {
        topo = null;
    }

    public void empilhar(int elemento) {
        Celula temp = new Celula(elemento);

        temp.prox = topo;
        topo = temp;
    }

    public void desempilhar() {
        if (topo == null) {
            System.out.println("-1");
        } else {
            Celula temp = topo;

            topo = topo.prox;

            System.out.println(temp.elemento);

            temp = temp.prox = null;
        }
    }

    public void mostrar() {
        if (topo == null) {
            System.out.println("V");
        } else {
            for (Celula i = topo; i != null; i = i.prox) {
                System.out.print(i.elemento + " ");
            }
            System.out.println();
        }

    }

    public boolean pesquisar(int elemento) {
        if (topo == null) return false;

        for (Celula i = topo; i != null; i = i.prox) {
            if (i.elemento == elemento) {
                return true; 
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pilha pilha = new Pilha();

        String comando;
        int elemento;

        while (sc.hasNext()) {
            comando = sc.next();

            if (comando.equals("E")) {
                elemento = sc.nextInt();
                pilha.empilhar(elemento);
            } else if (comando.equals("D")) {
                pilha.desempilhar();
            } else if (comando.equals("M")) {
                pilha.mostrar();
            } else if (comando.equals("P")) {
                elemento = sc.nextInt();
                System.out.println(pilha.pesquisar(elemento) ? "S" : "N");
            }
        }

        sc.close();
    }
}