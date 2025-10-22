import java.util.Scanner;

public class SomaDigitos
{
    // Verifica se a string é "FIM"
    public static boolean isEnd(String frase) 
    {
        if(frase.length() == 3 &&
           frase.charAt(0) == 'F' &&
           frase.charAt(1) == 'I' &&
           frase.charAt(2) == 'M') {
            return false; 
        } else {
            return true;  
        }
    }
    
    // soma os digitos de forma iterativa
    public static int somaDigitos(String numeros)
    {
        int s = 0;
        char c;

        for(int i = 0; i < numeros.length(); i++)
        {
            c = numeros.charAt(i);
            s += (c - '0');
        }

        return s;
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        String numeros = sc.next();
        int s;

        while (isEnd(numeros)) 
        {
            s = somaDigitos(numeros);
            System.out.println(s);
            numeros = sc.next();
        }

        sc.close();
    }    
}