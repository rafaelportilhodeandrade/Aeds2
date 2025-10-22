import java.util.Scanner;

public class Cesar {
    // retorna false se a string for exatamente "FIM"
    static boolean continuar(String c) {
        if (c.length() == 3 &&
            c.charAt(0) == 'F' &&
            c.charAt(1) == 'I' &&
            c.charAt(2) == 'M') {
            return false;
        }
        return true;
    }

    // cifra todos os caracteres
    static String cifraTudo(String c, int chave) {
        String resp = "";
        for (int i = 0; i < c.length(); i++) {
            char ch = c.charAt(i);

            if (ch >= 0 && ch <= 127) {
                char enc = (char) (ch + chave);
                resp += enc;
            } else {
                resp += ch; // mantém o mesmo caractere
            }
        }
        System.out.println(resp);
        return resp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int chave = 3;
        String c = sc.nextLine();

        while (continuar(c)) {
            String resp = cifraTudo(c, chave);
            c = sc.nextLine();
        }

        sc.close();
    }
}
