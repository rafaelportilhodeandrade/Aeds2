import java.util.Scanner;
import java.util.Random;

public class Substituir {

    // Verifica se a entrada é "FIM"
    public static boolean isFim(String frase) 
    {
        if (frase.length() == 3 &&
            frase.charAt(0) == 'F' &&
            frase.charAt(1) == 'I' &&
            frase.charAt(2) == 'M') 
        {
            return false;
        }
        return true;
    }

    // Sorteia uma letra entre 'A' e 'D'
    public static char sortear(Random rand) 
    {
        return (char) ('A' + rand.nextInt(4));
    }

    // Substitui todas as ocorrências de char1 por char2 na frase
    public static String substituir(String frase, char char1, char char2) 
    {
        String novaFrase = "";
        for (int i = 0; i < frase.length(); i++) 
        {
            if (frase.charAt(i) == char1) 
            {
                novaFrase += char2; 
            } else 
            {
                novaFrase += frase.charAt(i); 
            }
        }
        return novaFrase;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String frase = sc.nextLine();

        while (isFim(frase))
        {
            char char1 = sortear(rand); // sorteia caractere a ser substituído
            char char2  = sortear(rand); // sorteia caractere substituto
           
            String frase2 = substituir(frase, char1, char2); // aplica substituição
            System.out.println(frase2);

            frase = sc.nextLine();
        }

        sc.close();
    }
}
