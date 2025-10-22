import java.util.Scanner;

public class Anagrama {

    // Verifica se tem a frase FIM na string para finalizar a execução
    public static boolean isFim(String frase) 
    {
        return !(frase.length() == 3 &&
                 frase.charAt(0) == 'F' &&
                 frase.charAt(1) == 'I' &&
                 frase.charAt(2) == 'M');
    }

    // Monta uma noava string com tudo que tem antes do hifen
    public static String antesDoHifen(String frase) {
        int i = 0;
        while (i < frase.length() && frase.charAt(i) != '-') 
        {
            i++;
        }

        char[] x = new char[i];
        for (int j = 0; j < i; j++) 
        {
            x[j] = frase.charAt(j);
        }

        return new String(x);
    }


    // Monta uma noava string com tudo que tem depois do hifen
    public static String depoisDoHifen(String frase) 
    {
        int i = 0;
        while (i < frase.length() && frase.charAt(i) != '-') 
        {
            i++;
        }

        int tamanho = frase.length() - i - 1;
        char[] x = new char[tamanho];
        for (int j = 0; j < tamanho; j++) 
        {
            x[j] = frase.charAt(i + 1 + j);
        }

        return new String(x);
    }

    // Transforma letras maiusculas em minusculas comando 32
    public static String toLower(String frase) 
    {
        int n = frase.length();
        char[] x = new char[n];
        for (int i = 0; i < n; i++) 
        {
            char c = frase.charAt(i);
            if (c >= 'A' && c <= 'Z') x[i] = (char)(c + 32);
            else x[i] = c;
        }
        return new String(x);
    }

    // Ordena a string em ordem alfabetica
    public static String ordenar(String frase) 
    {
        int n = frase.length();
        char[] x = new char[n];
        for (int i = 0; i < n; i++) x[i] = frase.charAt(i);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) 
            {
                if (x[j] > x[j + 1]) 
                {
                    char temp = x[j];
                    x[j] = x[j + 1];
                    x[j + 1] = temp;
                }
            }
        }

        return new String(x);
    }

    // verifica se as letras são iguais
    public static boolean diferenciar(String frase1, String frase2) 
    {
        if (frase1.length() != frase2.length()) return false;

        for (int i = 0; i < frase1.length(); i++) {
            if (frase1.charAt(i) != frase2.charAt(i)) return false;
        }

        return true;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        String frase = sc.nextLine();
        while (isFim(frase)) 
        {
            String parte1 = antesDoHifen(frase);
            String parte2 = depoisDoHifen(frase);

            parte1 = toLower(parte1);
            parte2 = toLower(parte2);

            parte1 = ordenar(parte1);
            parte2 = ordenar(parte2);

            if (diferenciar(parte1, parte2)) System.out.println("SIM");
            else System.out.println("N\u00E3O");  
;

            frase = sc.nextLine();
        }

        sc.close();
    }
}
