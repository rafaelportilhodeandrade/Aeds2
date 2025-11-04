import java.util.*;

class No2
{
    String palavra;
    No2 esq;
    No2 dir;
}

class No
{
    char letra;
    No esq;
    No dir;
    No2 raiz;
}

class ArvoreArvore
{
    No raiz;


    public int contarArvoreInterna(int tam, No2 i)
    {
        int resp = 0;

        if(i != null)
        {
            if(tam == i.palavra.length())
            {
                resp ++;
            }

            resp += contarArvoreInterna(tam, i.esq) + contarArvoreInterna(tam, i.dir);
        }

        return resp;
    }

    public int contarPalavras(char letra, int tam, No i)
    {
        int resp = 0;

        if(i != null)
        {
            if(i.letra == letra) resp += contarArvoreInterna(tam, i.raiz);

            resp += contarPalavras(letra, tam, i.esq) + contarPalavras(letra, tam, i.dir);
        }

        return resp;
    }

    // conta o numero de palavras que possui o mesmo caractere inicial e o mesmo numero de
    public int contarPalavra(String palavra)
    {
        return contarPalavras(palavra.charAt(0), palavra.length(), raiz);
    }
}

public class Main
{
    public static void main(String[] args) 
    {
        
    }
}