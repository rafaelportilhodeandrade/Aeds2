import java.util.*;

class CelulaPilha
{
    int elemento;
    CelulaPilha prox;

    public CelulaPilha(int elemento)
    {
        this.elemento = elemento;
        prox = null;
    }
}

class CelulaLista
{
    CelulaPilha topo;
    CelulaLista prox;

    public CelulaLista()
    {
        topo = null;
        prox = null;
    }
}

class Lista
{
    CelulaLista primeiro;
    CelulaLista ultimo;

    public Lista()
    {
        ultimo = primeiro = new CelulaLista();
    }

    private int medirPilha(CelulaLista i)
    {
        int cont = 0;
        CelulaPilha temp = i.topo;

        for(; temp != null; temp = temp.prox) cont++;
        
        return cont;
    } 

    public CelulaLista maiorPilha()
    {
        if(primeiro.topo == null) throw new Error("Lista de pilhas vaiz");

        CelulaLista maior = primeiro;
        int cont = -1;
        int cont2;

        CelulaLista i = primeiro;

        for(; i != null; i = i.prox)
        {
            cont2 = medirPilha(i);
            if(cont2 > cont)
            {
                cont = cont2;
                maior = i;
            }
        }

        return maior;
    }
}


public class Main
{
    public static void main(String[] args) {
        Lista lista = new Lista();

        
    }
}