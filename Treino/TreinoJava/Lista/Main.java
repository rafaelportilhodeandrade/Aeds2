class Lista {
    private int[] elemento;
    private int tam;
    private int capacidade;

    public Lista(int max) {
        this.elemento = new int[max];
        this.tam = 0;
        this.capacidade = max;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getTam() {
        return tam;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void inserirFim(int elem) {
        if (tam == capacidade)
            throw new Error("Lista cheia");
        elemento[tam++] = elem;
    }

    public void inserirInicio(int elem) {
        if (tam == capacidade)
            throw new Error("Lista cheia");
        for (int i = tam - 1; i >= 0; i--)
            elemento[i + 1] = elemento[i];
        elemento[0] = elem;
        tam++;
    }

    public void inserir(int elem, int pos) {
        if (tam == capacidade)
            throw new Error("Lista cheia");
        for (int i = tam - 1; i >= pos; i--)
            elemento[i + 1] = elemento[i];
        elemento[pos] = elem;
        tam++;
    }

    public void removerInicio() {
        if (tam == 0)
            throw new Error("Lista vazia");
        for (int i = 0; i < tam - 1; i++)
            elemento[i] = elemento[i + 1];
        tam--;
    }

    public void removerFim() {
        if (tam == 0)
            throw new Error("Lista vazia");
        tam--;
    }

    public void remover(int pos) {
        if (tam == 0)
            throw new Error("Lista vazia");
        for (int i = pos; i < tam - 1; i++)
            elemento[i] = elemento[i + 1];
        tam--;
    }

    // |---------- SELEÇÃO ----------|
    public void selecao() {
        for (int i = 0; i < tam - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < tam; j++) {
                if (elemento[j] < elemento[menor])
                    menor = j;
            }
            swap(i, menor);
        }
    }

    // |---------- INSERÇÃO ----------|
    public void insercao() {
        for (int i = 1; i < tam; i++) {
            int chave = elemento[i];
            int j = i - 1;
            while (j >= 0 && elemento[j] > chave) {
                elemento[j + 1] = elemento[j];
                j--;
            }
            elemento[j + 1] = chave;
        }
    }

    // |---------- BOLHA ----------|
    public void bolha() {
        for (int i = 0; i < tam - 1; i++) {
            for (int j = 0; j < tam - 1 - i; j++) {
                if (elemento[j] > elemento[j + 1])
                    swap(j, j + 1);
            }
        }
    }

    // |---------- MERGE ----------|
    public void intercalar(int esq, int meio, int dir) {
        int nEsq = (meio + 1) - esq;
        int nDir = dir - meio;

        int[] arrayEsq = new int[nEsq + 1];
        int[] arrayDir = new int[nDir + 1];

        for (int i = 0; i < nEsq; i++)
            arrayEsq[i] = elemento[esq + i];
        for (int j = 0; j < nDir; j++)
            arrayDir[j] = elemento[meio + 1 + j];

        arrayEsq[nEsq] = arrayDir[nDir] = Integer.MAX_VALUE;

        int iEsq = 0, iDir = 0;
        for (int k = esq; k <= dir; k++) {
            if (arrayEsq[iEsq] <= arrayDir[iDir])
                elemento[k] = arrayEsq[iEsq++];
            else
                elemento[k] = arrayDir[iDir++];
        }
    }

    public void mergeSort(int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(esq, meio);
            mergeSort(meio + 1, dir);
            intercalar(esq, meio, dir);
        }
    }

    // |---------- QUICK ----------|
    public void quickSort(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = elemento[(esq + dir) / 2];

        while (i <= j) {
            while (elemento[i] < pivo)
                i++;
            while (elemento[j] > pivo)
                j--;

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (esq < j)
            quickSort(esq, j);
        if (i < dir)
            quickSort(i, dir);
    }

    // |---------- HEAP ----------|
    public void heapSort() {
        //construir o heap
        for (int tamHeap = 2; tamHeap <= tam; tamHeap++) {
            int i = tamHeap - 1;
            while (i > 0 && elemento[i] > elemento[i / 2]) {
                swap(i, i / 2);
                i /= 2;
            }
        }

        for (int tamHeap = tam - 1; tamHeap > 0; tamHeap--) {
            swap(0, tamHeap);
            int i = 0;

            while (i < tamHeap) {
                int filho = (i * 2) + 1;
                if (filho < tamHeap - 1 && elemento[filho] < elemento[filho + 1]) {
                    filho++;
                }

                if (filho < tamHeap && elemento[i] < elemento[filho]) {
                    swap(i, filho);
                    i = filho;
                } else {
                    break;
                }
            }
        }
    }

    // |---------- COUNTING ----------|
    public void countingSort() {
        if (tam == 0)
            return;

        int maior = elemento[0];
        int menor = elemento[0];

        for (int i = 1; i < tam; i++) {
            if (elemento[i] > maior)
                maior = elemento[i];
            if (elemento[i] < menor)
                menor = elemento[i];
        }

        int range = maior - menor + 1;
        int[] cont = new int[range];
        int[] saida = new int[tam];

        for (int i = 0; i < tam; i++)
            cont[elemento[i] - menor]++;

        for (int i = 1; i < range; i++)
            cont[i] += cont[i - 1];

        for (int i = tam - 1; i >= 0; i--) {
            saida[--cont[elemento[i] - menor]] = elemento[i];
        }

        for (int i = 0; i < tam; i++)
            elemento[i] = saida[i];
    }

    // |---------- SHELL ----------|
    public void shellSort() {
        for (int gap = tam / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < tam; i++) {
                int temp = elemento[i];
                int j = i;
                while (j >= gap && elemento[j - gap] > temp) {
                    elemento[j] = elemento[j - gap];
                    j -= gap;
                }
                elemento[j] = temp;
            }
        }
    }

    // |---------- RADIX ----------|
    public void radixSort() {
        int max = getMax();
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingRadix(exp);
    }

    private int getMax() {
        int max = elemento[0];
        for (int i = 1; i < tam; i++)
            if (elemento[i] > max)
                max = elemento[i];
        return max;
    }

    private void countingRadix(int exp) {
        int[] saida = new int[tam];
        int[] cont = new int[10];

        for (int i = 0; i < tam; i++)
            cont[(elemento[i] / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            cont[i] += cont[i - 1];

        for (int i = tam - 1; i >= 0; i--) {
            int idx = (elemento[i] / exp) % 10;
            saida[--cont[idx]] = elemento[i];
        }

        for (int i = 0; i < tam; i++)
            elemento[i] = saida[i];
    }

    // |---------- AUX ----------|
    public void swap(int i, int j) {
        int temp = elemento[i];
        elemento[i] = elemento[j];
        elemento[j] = temp;
    }

    public void mostrar() {
        for (int i = 0; i < tam; i++)
            System.out.println(elemento[i]);
    }
}

public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista(5);

        lista.inserirInicio(2);
        lista.inserirFim(3);
        lista.inserir(1, 1);
        lista.inserirInicio(4);
        lista.inserirInicio(5);
        // lista.Seleção();
        // lista.inserção();
        // lista.bolha();
        // lista.quickSort(0, 4);
        lista.mergeSort(0, 4);

        lista.mostrar();
    }
}