import java.util.*;

public class Inversão 
{
    // Verifica se a string é diferente de "FIM"
    public static boolean isEnd(String frase) 
    {
        return !(frase.length() == 3 &&
                 frase.charAt(0) == 'F' &&
                 frase.charAt(1) == 'I' &&
                 frase.charAt(2) == 'M');
    }

    // Inverte a string recursivamente
    public static String inverter(String frase, int N)
    {
        if(N == 0)
            return "";
            
        return frase.charAt(N - 1) + inverter(frase, N - 1);
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String frase = sc.next();

        while (isEnd(frase)) 
        {
            System.out.println(inverter(frase, frase.length()));
            frase = sc.next();
        }

        sc.close();
    }    
}
