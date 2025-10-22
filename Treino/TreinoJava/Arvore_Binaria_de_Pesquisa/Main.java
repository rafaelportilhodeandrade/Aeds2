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

        arvore.caminhar();
    }    
}