import java.util.*;

public class Main {

    public static void ordenar(String[] comandos, int tam)
    {
        for(int i = 0; i < tam - 1; i++)
        {
            int menor = i;
            for(int j = i + 1; j < tam; j++)
            {
                if(comandos[menor].compareTo(comandos[j]) > 0)
                {
                    menor = j;
                }
            }
            if(menor != i)
            {
                String temp = comandos[menor];
                comandos[menor] = comandos[i];
                comandos[i] = temp;
            }
        }

        mostrar(comandos, tam);
    }

    public static void mostrar(String[] comandos, int tam)
    {
        for(int i = 0; i < tam; i++)
        {
            System.out.println(comandos[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String comando;

        while (sc.hasNext()) {
            comando = sc.nextLine();

            if (comando.equals("1")) {
                int i = 0;
                String[] comandos = new String[1000000];

                while (!comando.equals("0")) {
                    comando = sc.nextLine();

                    if (!comando.equals("0")) {
                        //System.out.println(comando);
                        comandos[i] = comando;
                        i++; 
                    }
                }
                ordenar(comandos, i);
                //System.out.println();
            }
        }

        sc.close();
    }
}
