import java.util.*;

class Celula 
{
    //---------- Construtores ----------
    int elemento;
    Celula prox;

    public Celula(int elemento, Celula prox) 
    {
        this.elemento = elemento;
        this.prox = prox;
    }

    public Celula(int elemento) 
    {
        this(elemento, null);
    }

    public Celula() 
    {
        this(0, null);
    }

    public void setElemento(int elemento) 
    {
        this.elemento = elemento;
    }

    public int getElemento() 
    {
        return elemento;
    }

    public void setProx(Celula prox) 
    {
        this.prox = prox;
    }

    public Celula getProx() 
    {
        return prox;
    }
}

class ListaEncadeada 
{
    private Celula primeiro;
    private Celula ultimo;

    //inicia a celula cabeça
    public ListaEncadeada() 
    {
        primeiro = new Celula(); 
        ultimo = primeiro;
    }

    //---------- Inserir Inicio ----------
    public void InserirInicio(int elemento) 
    {
        Celula temp = new Celula(elemento, primeiro.prox);
        primeiro.prox = temp;

        if (primeiro == ultimo) 
        {
            ultimo = temp;
        }
        temp = null;
    }

    //---------- Inserir Fim ----------
    public void InserirFim(int elemento)
    {
        Celula temp = new Celula(elemento, null);
        ultimo.prox = temp;
        ultimo = temp;
        temp = null;
    }

    //conta o tamanho da lista
    public int Contador() 
    {
        int cont = 0;
        Celula i = primeiro.prox;
    
        while(i != null) 
        {
            cont++;
            i = i.prox;
        }
    
        return cont; 
    }
    

    //Insere em qualquer posição da lista
    public void Inserir(int elemento, int pos) 
    {
        int tam = Contador();
    
        if (pos < 0 || pos > tam + 1) throw new IndexOutOfBoundsException("Posição inválida");
    
        if (pos == 0) InserirInicio(elemento);
         
        else if (pos == tam + 1) InserirFim(elemento);

        else 
        {
            Celula temp = new Celula(elemento);
            Celula i = primeiro;
            for (int j = 0; j < pos - 1; j++) 
            {
                i = i.prox;
            }
            temp.prox = i.prox;
            i.prox = temp;
            temp = i = null;
        }
    }
    

    //Remover inicio alterando a celula cabeça
    public void RemoverInicio() 
    {
        if(primeiro == ultimo) throw new IllegalStateException("lista com apenas um elemento");

        Celula temp = primeiro;
        primeiro = primeiro.prox;
        temp = temp.prox = null;
    }

    //Remover o ultimo elemento da lista e se tiver apenas um lança um erro.
    public void RemoverFim()
    {
        if(primeiro == ultimo) throw new IllegalStateException("lista com apenas um elemento");

        Celula i = primeiro;
        for(; i.prox != ultimo; i = i.prox);
        ultimo = i;
        ultimo.prox = null;
    }

    //remove em qualquer posição da lista
    public void Remover(int pos)
    {
        int tam = Contador();

        if(tam == 0) throw new IllegalStateException("lista com apenas um elemento");
        else if(pos == 0) RemoverInicio();
        else if(pos == tam) RemoverFim();
        else
        {
            Celula i = primeiro;
            for(int j = 0; j < pos - 1; j++) i = i.prox;
            Celula temp = i.prox;
            i.prox = i.prox.prox;
            temp.prox = null;
            i = temp = null;
        }
    }

    //---------- ORDENAÇÕES ----------

    //--------- Seleção ---------
    public void Selecao() 
    {
        int tam = Contador();

        if(tam == 0) throw new IllegalStateException("lista com apenas um elemento");

        for (Celula i = primeiro.prox; i != null; i = i.prox) 
        {
            Celula menor = i;

            for (Celula j = i.prox; j != null; j = j.prox) 
            {
                if (j.elemento < menor.elemento) 
                {
                    menor = j;
                }
            }

            if (menor != i) 
            {
                int temp = i.elemento;
                i.elemento = menor.elemento;
                menor.elemento = temp;
            }
        }
    }

    //--------- Inserção ---------

    public void Insercao()
    {
        int tam = Contador();

        if(tam == 0) throw new IllegalStateException("lista com apenas um elemento ou nenhum");

        Celula i = primeiro.prox.prox;
        Celula j;

        while(i != null)
        {
            int temp = i.elemento;
            j = getAnterior(i);
            
            while(j != primeiro && j.elemento > temp) 
            {
                j.prox.elemento = j.elemento;
                j = getAnterior(j);
            }
            
            j.prox.elemento = temp;
            i = i.prox;
        }
    }

    private Celula getAnterior(Celula i) {
        Celula j = primeiro;
        while(j.prox != i) j = j.prox;
        return j;
    }

    //--------- Bolha ---------
    public void Bolha()
    {
        int tam = Contador();

        if(tam == 0) throw new IllegalStateException("lista com apenas um elemento ou nenhum");

        Celula var = ultimo;

        for(int i = 0; i < tam - 1; i++)
        {
            for(Celula j = primeiro.prox; j != var; j = j.prox)
            {
                if(j.elemento > j.prox.elemento)
                {
                    int temp = j.elemento;
                    j.elemento = j.prox.elemento;
                    j.prox.elemento = temp;
                }
            }
            var = getAnterior(var);
        }
    }

    //---------- ShellSort ----------

    //---------- CountingSort ----------

    //---------- RadixSort ----------

    //--------- Quicksort ---------

    //--------- Mergesort ---------

    //--------- Heapsort ---------

    //---------- Mostrar ----------
    public void Mostrar()
    {
        Celula i = primeiro.prox;

        System.out.println("");
        while(i != null)
        {
            System.out.println(i.elemento);
            i = i.prox;
        }
        System.out.println("");
    }
}

public class Main 
{
    public static void main(String[] args) 
    {
        ListaEncadeada lista = new ListaEncadeada();

        lista.InserirInicio(5);
        lista.InserirInicio(6);
        lista.InserirInicio(1);
        lista.InserirInicio(2);
        lista.InserirInicio(4);
        lista.InserirFim(3);
        lista.Inserir(7,4);
        //lista.Selecao();
        //lista.Insercao();
        lista.Bolha();
        lista.Mostrar();
    }
}