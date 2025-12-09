class No
{
    int elemento;
    int nivel;
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
        this.nivel = 1;
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

    public void setNivel() 
    {
        int nivelEsq = (esq == null ? 0 : esq.nivel);
        int nivelDir = (dir == null ? 0 : dir.nivel);
        this.nivel = 1 + Math.max(nivelEsq, nivelDir);
    }    

    public int getFatorBalanceamento()
    {
        int fatorBalanceamento = 0;
        int nivelEsq = (esq == null ? 0 : esq.nivel);
        int nivelDir = (dir == null ? 0 : dir.nivel);
        
        fatorBalanceamento = nivelDir - nivelEsq;

        //System.out.println("( " + fatorBalanceamento + " )");

        return fatorBalanceamento;
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

    private No inserir(int elemento, No i)
    {
        if(i == null) i = new No(elemento);
        else if(elemento < i.elemento) i.esq = inserir(elemento, i.esq);
        else if(elemento > i.elemento) i.dir = inserir(elemento, i.dir);
        else throw new Error("Erro");

        i = balancear(i);
        return i;
    }

    public boolean pesquisar(int elemento)
    {
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar(int elemento, No i)
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

    private void caminhar(No i)
    {
        if(i != null)
        {
            
            caminhar(i.esq);
            int fb = i.getFatorBalanceamento();
            System.out.print(i.elemento + " ");
            System.out.print("( " + i.nivel + " ) ");
            System.out.println("( " + fb + " )");
            caminhar(i.dir);
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
        raiz = remover(elemento, raiz);
    }

    private No remover(int elemento, No i)
    {
        if(i == null) throw new Error("Erro");
        else if(elemento < i.elemento) i.esq = remover(elemento, i.esq);
        else if(elemento > i.elemento) i.dir = remover(elemento, i.dir);
        else if(i.dir == null) i = i.esq;
        else if(i.esq == null) i = i.dir;
        else
        {
            i.esq = maiorEsq(i, i.esq);
            //i.dir = menorDir(i, i.dir);
        } 

        if(i != null)
        {
            i = balancear(i);
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



    public No rotacionarEsq(No no)
    {
        No noDir = no.dir;
        No dirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = dirEsq;
        
        no.setNivel();
        noDir.setNivel();

        return noDir;
    }   
    
    public No rotacionarDir(No no)
    {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        no.setNivel();
        noEsq.setNivel();

        return noEsq;
    }

    public No rotacaoDirEsq(No no)
    {
        no.dir = rotacionarDir(no.dir);
        return rotacionarEsq(no);
    }

    public No rotacaoEsqDir(No no)
    {
        no.esq = rotacionarEsq(no.esq);
        return rotacionarDir(no);
    }

    public No balancear(No i)
    {
        if(i.getFatorBalanceamento() >= 2)
        {
            if(i.dir.getFatorBalanceamento() < 0)
            {
                i = rotacaoDirEsq(i);
            }
            else
            {
                i = rotacionarEsq(i);
            }
        }
        else if(i.getFatorBalanceamento() <= -2)
        {
            if(i.esq.getFatorBalanceamento() > 0)
            {
                i = rotacaoEsqDir(i);
            }
            else
            {
                i = rotacionarDir(i);
            }
        }
        else
        {
            i.setNivel();
        }

        return i;
    }
}

public class Main
{
    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        arvore.inserir(4);
        arvore.inserir(35);
        arvore.inserir(10);
        arvore.inserir(13);
        arvore.inserir(3);
        arvore.inserir(30);
        arvore.inserir(15);
        arvore.inserir(12);
        arvore.inserir(7);
        arvore.inserir(40);
        arvore.inserir(20);
        // arvore.remover(4);
        // arvore.remover(3);
        // arvore.remover(12);
        // arvore.remover(15);
        // arvore.remover(40);


        arvore.caminhar();


        //int maior = arvore.getMaior();
        //int menor = arvore.getMenor();
        //System.out.println(menor);
    }    
}