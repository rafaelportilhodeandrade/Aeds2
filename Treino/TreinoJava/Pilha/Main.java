import java.util.*;

class Pilha
{
    int[] elemento;
    int capacidade;
    int tam;

    public Pilha(int max)
    {
        this.capacidade = max;
        elemento = new int[max];
        this.tam = 0;
    }

    // ---------- Inserir Fim ----------
    public void inserirFim(int elemento)
    {
        if(tam == capacidade) throw new Error("Erro");
        
        this.elemento[tam] = elemento;
        tam++;
    }

    // ---------- Remover Fim ----------
    public void removerFim()
    {
        if(tam == 0) throw new Error("Erro");

        tam--;
    }

    // Mostrar
    public void mostrar()
    {
        for(int i = 0; i < tam; i++)
        {
            System.out.println(elemento[i]);
        }
    }

    // Ordenações

    // Auxiliares
    public void swap(int i, int menor)
    {
        int temp = elemento[i];
        elemento[i] = elemento[menor];
        elemento[menor] = temp;
    }

    // Seleção
    public void seleção()
    {
        for(int i = 0; i < tam; i++)
        {
            int menor = i;
            for(int j = i + 1; j < tam; j++)
            {
                if(elemento[menor] > elemento[j])
                {
                    menor = j;
                }
            }
            swap(i,menor);
        }
    }

    // Inserção
    public void inserção()
    {
        for(int i = 1; i < tam; i++)
        {
            int j = i - 1;
            int temp = elemento[i];
            while (j >= 0  && elemento[j] > temp)
            {
                elemento[j + 1] = elemento[j];
                j--;
            }
            elemento[j + 1] = temp;
        }
    }

    // BubbleSort
    public void bubble()
    {
        for(int i = 0; i < tam - 1; i++)
        {
            for(int j = 0; j < tam - 1 - i; j++)
            {
                if(elemento[j] > elemento[j + 1]) swap(j, j + 1);
            }
        }
    }

    // QuickSort
    public void QuickSort()
    {
        
    }
}   

public class Main
{
    public static void main(String[] args) 
    {
        Pilha pilha = new Pilha(5);
        pilha.inserirFim(2);
        pilha.inserirFim(4);
        pilha.inserirFim(1);
        pilha.inserirFim(3);
        pilha.inserirFim(5);
        //pilha.seleção();
        //pilha.inserção();
        //pilha.bubble();
        pilha.QuickSort();

        pilha.mostrar();
    }
}