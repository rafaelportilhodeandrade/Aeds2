class No
{
    int elemento;
    No dir;
    No esq;

    public No()
    {
        this.elemento = 0;
        this.dir = null;
        this.esq = null;
    }

    public No(int elemento)
    {
        this.elemento = elemento;
        this.dir = null;
        this.esq = null;
    }

    public void setElemento(int elemento)
    {
        this.elemento = elemento;
    }

    public void setDir(No dir)
    {
        this.dir = dir;
    }

    public void setEsq(No esq)
    {
        this.esq = esq;
    }

}

class Arvore
{
    private No raiz;

    public Arvore()
    {
        raiz = null;
    }


    public void inserir(int elemento)
    {
        raiz = inserir(elemento, raiz);
    }

    public No inserir(int elemento, No i)
    {
        if(i == null) i = new No(elemento);
        else if(elemento < i.elemento) i.esq = inserir(elemento, i.esq);
        else if(elemento > i.elemento) i.dir = inserir(elemento, i.dir);
        else throw new Error("Erro");

        return i;
    }

    public boolean pesquisar(int elemento)
    {
        return pesquisar(elemento, raiz);
    }

    public boolean pesquisar(int elemento, No i)
    {
        boolean resp = false;
        if(i == null) resp = false;
        else if(i.elemento == elemento) resp = true;
        else if(elemento < i.elemento) resp = pesquisar(elemento, i.esq);
        else if(elemento > i.elemento) resp = pesquisar(elemento, i.dir);

        return resp;
    }

    public void caminhar()
    {
        caminhar(raiz);
    }

    public void caminhar(No i)
    {
        if(i != null)
        {
            caminhar(i.esq);
            caminhar(i.dir);
            System.out.println(i.elemento);
        }
    }

    public int getMaior()
    {
        No i = raiz;
        int maior = -1;
        if(i != null)
        {
            for(; i != null; i = i.dir)
            {
                maior = i.elemento;
            }
        }

        return maior;
    }

    public int getMenor()
    {
        No i = raiz;
        int menor = raiz.elemento;
        if(i != null)
        {
            for(; i != null; i = i.esq)
            {
                menor = i.elemento;
            }
        }

        return menor;
    }

    public void remover(int elemento)
    {
        remover(elemento, raiz);
    }

    public No remover(int elemento, No i)
    {
        if(i == null) throw new Error("Erro");
        else if(elemento < i.elemento) i.esq = remover(elemento, i.esq);
        else if(elemento > i.elemento) i.dir = remover(elemento, i.dir);
        else if(i.dir == null) i = i.esq;
        else if(i.esq == null) i = i.dir;
        else
        {
            //i.esq = maiorEsq(i, i.esq);
            i.dir = menorDir(i, i.dir);
        } 

        return i;
    }

    public No maiorEsq(No i, No j)
    {
        if(j.dir == null) 
        {
            i.elemento = j.elemento;
            j = j.esq;
        }
        else j.dir = maiorEsq(i, j.dir);

        return j;
    }

    public No menorDir(No i, No j)
    {
        if(j.esq == null)
        {
            i.elemento = j.elemento;
            j = j.dir;
        }
        else j.esq = menorDir(i, j.esq);

        return j;
    }

    public void mostrarRaiz()
    {
        System.out.println(raiz.elemento);
    }
}

public class Main
{
    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        arvore.inserir(6);
        arvore.inserir(3);
        arvore.inserir(9);
        arvore.inserir(1);
        arvore.inserir(5);
        arvore.inserir(2);
        arvore.inserir(4);
        arvore.inserir(8);
        arvore.inserir(10);
        arvore.inserir(11);
        arvore.inserir(7);
        arvore.remover(6);
        arvore.mostrarRaiz();

        //arvore.caminhar();
        //int maior = arvore.getMaior();
        //int menor = arvore.getMenor();
        //System.out.println(menor);
    }    
}
