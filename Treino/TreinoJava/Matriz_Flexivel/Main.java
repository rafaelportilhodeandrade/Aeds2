class Celula {
    int elemento;
    Celula sup;
    Celula esq;
    Celula dir;
    Celula inf;

    public Celula() {
        this.elemento = 0;
        this.sup = null;
        this.esq = null;
        this.dir = null;
        this.inf = null;
    }

    public Celula(int elemento) {
        this.elemento = elemento;
        this.sup = null;
        this.esq = null;
        this.dir = null;
        this.inf = null;
    }
}

class Matriz {
    private Celula inicio;
    private int col;
    private int lin;

    public Matriz() {
        inicio = new Celula();
        this.col = 3;
        this.lin = 3;
    }

    public Matriz(int col, int lin) {
        inicio = null;
        this.col = col;
        this.lin = lin;
    }

    public void criarMatriz() {
        Celula linhaCima = null;
        Celula linhaAtualInicio = null;

        for (int i = 0; i < lin; i++) {
            Celula celulaAnterior = null;
            Celula celulaAtual = null;

            for (int j = 0; j < col; j++) {
                celulaAtual = new Celula(1);

                if (i == 0 && j == 0) {
                    inicio = celulaAtual;
                }

                if (celulaAnterior != null) {
                    celulaAnterior.dir = celulaAtual;
                    celulaAtual.esq = celulaAnterior;
                }

                if (linhaCima != null) {
                    Celula acima = linhaCima;
                    for (int k = 0; k < j; k++) {
                        acima = acima.dir;
                    }
                    acima.inf = celulaAtual;
                    celulaAtual.sup = acima;
                }

                celulaAnterior = celulaAtual;

                if (j == 0) {
                    linhaAtualInicio = celulaAtual;
                }
            }

            linhaCima = linhaAtualInicio;
        }
    }

    public void inserirLinhaAbaixo() {
    Celula linhaAcima = inicio;
    while (linhaAcima.inf != null) {
        linhaAcima = linhaAcima.inf;
    }
    Celula atual = null;
    Celula nova = null;
    Celula acima = linhaAcima;
    
    for (int i = 0; i < col; i++) {
        nova = new Celula(2); 

        
        if (atual != null) {
            atual.dir = nova;
            nova.esq = atual;
        }

        
        nova.sup = acima;
        acima.inf = nova;

        
        atual = nova;
        acima = acima.dir;
    }

    lin++; 
}


    public void inserirColunaDireita() {
        Celula colunaEsq = inicio;
        while (colunaEsq.dir != null) {
            colunaEsq = colunaEsq.dir;
        }

        Celula antiga = null, nova = null;

        for (int i = 0; i < lin; i++) {
            nova = new Celula(3);

            colunaEsq.dir = nova;
            nova.esq = colunaEsq;
            if (antiga != null) {
                nova.sup = antiga;
                antiga.inf = nova;
            }
            colunaEsq = colunaEsq.inf;
        }
        col++;
    }

    public void mostrar() {
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                System.out.print(j.elemento + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Matriz matriz = new Matriz();

        matriz.criarMatriz();
        matriz.inserirLinhaAbaixo();
        matriz.inserirColunaDireita();
        matriz.mostrar();
    }
}