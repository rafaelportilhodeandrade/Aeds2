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
    public void inserirFim(int elemento)
    {
        if()
    }

    // RemoverInicio
    public void RemoverInicio()
    {

    }

    // Mostrar

    // Ordenações
}

public class Main
{
    public static void main(String[] args) 
    {
        Fila fila = new Fila();


    }
}