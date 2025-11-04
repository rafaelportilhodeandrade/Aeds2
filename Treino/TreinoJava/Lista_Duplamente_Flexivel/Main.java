import java.util.*;

class Celula
{
    int elemento;
    Celula prox;
    Celula ant;

    public Celula(int elemento)
    {
        this.elemento = elemento;
        prox = null;
        ant = null;
    }

    public Celula(int elemento, Celula prox, Celula ant)
    {
        this.elemento = elemento;
        this.prox = prox;
        this.ant = ant;
    }
}

class Lista
{
    private Celula primeiro = null;
    private Celula ultimo = null;
    
    public Lista()
    {
        primeiro = ultimo = new Celula(0);
    }

    // |---------- Inserção ----------|

    public void inserirInicio(int elemento)
    {
        Celula temp = new Celula(elemento, primeiro.prox, primeiro);

        primeiro.prox = temp;
        if(temp.prox != null) temp.prox.ant = temp;
        else ultimo = temp;

        temp = null;
    }

    public void inserirFim(int elemento)
    {
        Celula temp = new Celula(elemento);

        ultimo.prox = temp;
        temp.ant = ultimo;
        ultimo = temp;

        temp = null;
    }

    public int getTam()
    {
        int cont = 0;

        for(Celula i = primeiro.prox; i != null; i = i.prox) cont++;

        return cont;
    }

    public void inserir(int elemento, int pos)
    {
        int tam = getTam();

        if(pos < 0 || pos > tam) throw new Error("ERRO");
        else if(pos == 0) inserirInicio(elemento);
        else if(pos == tam) inserirFim(elemento);
        else
        {
            Celula temp = primeiro;
            Celula i = new Celula(elemento);
            for(int j = 0; j < pos; j++) temp = temp.prox;

            i.prox = temp.prox;
            i.ant = temp;
            temp.prox.ant = i;
            temp.prox = i;

            temp = i = null;
        }
    }

    // |---------- Remoção ----------|
    public void removerInicio()
    {
        Celula temp = primeiro.prox;

        primeiro.prox = temp.prox;
        temp.prox.ant = primeiro;

        if(primeiro.prox == null) ultimo = primeiro;

        temp = null;
    }

    public void removerFim()
    {
        Celula temp = ultimo;

        ultimo = temp.ant;

        ultimo.prox = null;
        temp = temp.ant = null;
    }

    public void remover(int pos)
    {
        int tam = getTam();

        if(pos < 0 || pos > tam) throw new Error("ERRO");
        else if(pos == 0) removerInicio();
        else if(pos == tam) removerFim();
        else
        {
            Celula temp = primeiro;
            for(int i = 0; i < pos; i++) temp = temp.prox;
            Celula j = temp.prox;

            temp.prox = j.prox;
            temp.prox.ant = temp;
            j = j.ant = j.prox = null;
        }
    }
        

    // |---------- Mostrar ----------|

    public void mostrar()
    {
        for(Celula i = primeiro.prox; i != null; i = i.prox) System.out.println(i.elemento);;
    }
}

public class Main
{
    public static void main(String[] args) 
    {
        Lista lista = new Lista();

        lista.inserirFim(0);
        lista.inserirFim(2);
        lista.inserirFim(5);
        lista.inserirFim(1);
        lista.inserirFim(3);
        lista.inserirFim(6);
        lista.inserirFim(4);
        //lista.removerInicio();
        //lista.remover(4);
        //lista.removerFim();


        lista.mostrar();
    }
}