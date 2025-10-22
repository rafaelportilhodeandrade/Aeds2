import java.util.Scanner;

public class Inverter 
{
    // Inverte os caracteres de uma string
    public static String inverter(String frase) {
        int n = frase.length();
        char[] invertido = new char[n]; 
        int x = n - 1; 

        for (int i = 0; i < n; i++) {
            invertido[i] = frase.charAt(x); 
            x--;
        }

        return new String(invertido);
    }

    // Verifica se a string é "FIM"
    public static boolean isFim(String frase) {
        if(frase.length() == 3 &&
           frase.charAt(0) == 'F' &&
           frase.charAt(1) == 'I' &&
           frase.charAt(2) == 'M') {
            return false; // encerra quando for "FIM"
        } else {
            return true;  // continua caso contrário
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String frase = sc.next();
        String fraseInvertida;

        while(isFim(frase)) {
            fraseInvertida = inverter(frase);
            System.out.println(fraseInvertida);
            frase = sc.next();
        }

        sc.close();
    }    
}
