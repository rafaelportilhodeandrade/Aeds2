import java.util.*;

// N Cartelas
// cada uma contem K numeros
// numeros de 1 até U para k < U

class Cartelas
{
    int[] elementos;

    public Cartelas(int max)
    {
        this.elementos = new int[max];
    }
}


public class Bingo
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        int numeroDeCartelas = sc.nextInt();
        int tamanhoDasCartelas = sc.nextInt();
        int numerosSorteados = sc.nextInt();

        Cartelas[] cartelas = new Cartelas[numeroDeCartelas];

        for(int i = 0; i < numeroDeCartelas; i++)
        {
            cartelas[i] = new Cartelas(tamanhoDasCartelas);
        }

        for(int i = 0; i < numeroDeCartelas; i++)
        {
            for(int j = 0; j < tamanhoDasCartelas; j++)
            {
                cartelas[i].elementos[j] = sc.nextInt();
            }
        }

        int[] arrayNumerosSorteados = new int[numerosSorteados];

        for(int i = 0; i < numerosSorteados; i++)
        {
            arrayNumerosSorteados[i] = sc.nextInt();

            if(i >= tamanhoDasCartelas)
            {
                int vencedora = compararCartelas(arrayNumerosSorteados, cartelas, numeroDeCartelas, tamanhoDasCartelas);

                if(vencedora > 0)
                {
                    System.out.println(vencedora);
                    i = numerosSorteados;
                }
            }
        }

        sc.close();
    }

    public static int compararCartelas(int[] arrayNumerosSorteados, Cartelas[] cartelas, int numeroDeCartelas, int tamanhoDasCartelas)
    {
        int vencedora = 0;

        for(int i = 0; i < numeroDeCartelas; i++)
        {
            boolean venceu = true;
            for(int j = 0; j < tamanhoDasCartelas; j++)
            {
                int temp = cartelas[i].elementos[j];
                boolean encontrou = false;
                for(int k = 0; k < arrayNumerosSorteados.length; k++)
                {
                    if(temp == arrayNumerosSorteados[k])
                    {
                        encontrou = true;
                        k = arrayNumerosSorteados.length;
                    }
                }
                if(!encontrou)
                {
                    venceu = false;
                    j = tamanhoDasCartelas;
                }
            }
            if(venceu)
            {
                vencedora = i + 1;
            }
        }

        return vencedora;
    }
}