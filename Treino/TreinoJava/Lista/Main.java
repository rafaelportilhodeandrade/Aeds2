class Lista
{
    private int[] elemento;
    private int tam;
    private int capacidade;

    public Lista(int max)
    {
        this.elemento = new int[max];
        this.tam = 0;
        this.capacidade = max;
    }

    public void setTam(int tam)
    {
        this.tam = tam;
    }

    public void setCapacidade(int capacidade)
    {
        this.capacidade = capacidade;
    }

    public int getTam()
    {
        return tam;
    }

    public int getCapacidade()
    {
        return capacidade;
    }

    public void inserirFim(int elem)
    {
        if(tam == capacidade)
        {
            throw new Error("Lista cheia");
        }

        elemento[tam] = elem;

        tam++;
    }

    public void inserirInicio(int elem)
    {
        if(tam == capacidade)
        {
            throw new Error("Lista cheia");
        }

        for(int i = tam - 1; i >= 0; i--)
        {
            elemento[i + 1] = elemento[i];
        }

        elemento[0] = elem;
        tam++;
    }

    public void inserir(int elem, int pos)
    {
        if(tam == capacidade)
        {
            throw new Error("Lista cheia");
        }

        for(int i = tam -1; i >= pos; i--)
        {
            elemento[i + 1] = elemento[i];
        }

        elemento[pos] = elem;
        tam++;
    }

    public void removerInicio()
    {
        if(tam == 0)
        {
            throw new Error("Lista vazia");
        }

        for(int i = 0; i < tam - 1; i++)
        {
            elemento[i] = elemento[i + 1];
        }

        tam--;
    }

    public void removerFim()
    {
        if(tam == 0)
        {
            throw new Error("Lista vazia");
        }

        tam--;
    }

    public void remover(int pos)
    {
        if(tam == 0)
        {
            throw new Error("Lista vazia");
        }

        for(int i = pos; i < tam - 1; i++)
        {
            elemento[i] = elemento[i + 1];
        }

        tam--;
    }

    // |---------- ORDENAÇÕES ----------|

    // |---------- Seleção ----------|

    public void Seleção()
    {
        if(tam == 0)
        {
            throw new Error("Lista vazia");
        }

        for(int i = 0; i < tam - 1; i++)
        {
            int menor = i;
            int j = i + 1;
            for(; j < tam; j++)
            {
                if(elemento[j] < elemento[menor])
                {
                    menor = j;
                }
            }
            swap(i,menor);
        }
    }

    // |---------- Inserção ----------|

    public void inserção()
    {
        if(tam == 0)
        {
            throw new Error("Lista vazia");
        }

    }
    
    // |---------- Bolha ----------|

    // |---------- Couting ----------|

    // |---------- Radixx ----------|

    // |---------- Shell ----------|

    // |---------- Merge ----------|

    // |---------- Quick----------|

    // |---------- Heap ----------|

    // |---------- Auxiliares ----------|

    public void swap(int i, int j)
    {
        int temp = elemento[i];
        elemento[i] = elemento[j];
        elemento[j] = temp;
    }

    public void mostrar()
    {
        for(int i = 0; i < tam; i++)
        {
            System.out.println(elemento[i]);
        }
    }

}


public class Main
{
    public static void main(String[] args) 
    {
        Lista lista = new Lista(5);

        lista.inserirInicio(2);
        lista.inserirFim(3);
        lista.inserir(1, 1);
        lista.inserirInicio(4);
        lista.inserirInicio(5);
        //lista.Seleção();
        lista.mostrar();
    }
}