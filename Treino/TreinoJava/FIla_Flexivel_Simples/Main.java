import java.util.*;

class Celula
{
    int elemento;
    Celula prox;

    public Celula(int elemento, Celula prox)
    {
        this.elemento = elemento;
        this.prox = prox;
    }

    public Celula(int elemento)
    {
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula()
    {
        this.elemento = 0;
        this.prox = null;
    }
}

class Fila
{
    private Celula primeiro;
    private Celula ultimo;

    //inicia a celula cabeça
    public Fila() 
    {
        primeiro = new Celula(); 
        ultimo = primeiro;
    }

    // Inserir Inicio
    public void inserirInicio(int elemento)
    {
        Celula temp = new Celula(elemento, primeiro.prox);
        primeiro.prox = temp;

        if(primeiro == ultimo)
        {
            ultimo = temp;
        }

        temp = null;
    }

    // RemoverInicio
    public void RemoverInicio()
    {
        Celula temp = primeiro;
        primeiro= primeiro.prox;
        temp = temp.prox = null;
    }

    // Mostrar
    public void mostrar()
    {
        Celula i = primeiro.prox;

        for(; i != null; i = i.prox)
        {
            System.out.println(i.elemento);
        }
    }


    // Ordenações


}

public class Main
{
    public static void main(String[] args) 
    {
        Fila fila = new Fila();

        fila.inserirInicio(1);
        fila.inserirInicio(5);
        fila.inserirInicio(3);
        fila.inserirInicio(8);
        fila.inserirInicio(2);
        fila.inserirInicio(7);
        fila.inserirInicio(4);
        fila.inserirInicio(6);
        fila.RemoverInicio();

        fila.mostrar();
    }
}