import java.util.*;

public class Main
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        int nmr = sc.nextInt();
        sc.nextLine();

        String[] colecao = new String[151];
        int usados = 0;

        for(int i = 0; i < nmr; i++)
        {
            String pokemon = sc.nextLine();
            boolean existe = false;

            for(int j = 0; j < usados; j++)  
            {
                if(colecao[j].equals(pokemon))
                {
                    existe = true;
                    j = usados;
                }
            }

            if(!existe)
            {
                colecao[usados] = pokemon;  
                usados++;
            }
        }

        int falta = 151 - usados;
        System.out.println("Falta(m) " + falta + " pomekon(s).");

        sc.close();
    }
}
