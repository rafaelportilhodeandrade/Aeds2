import java.util.*;

public class Main {

    public static int getInteiro(String entrada) {
        String parteInteira = "";
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c == '-' || (c >= '0' && c <= '9')) {
                parteInteira += c;
            }
        }

        if (parteInteira.equals("")) return 0;
        return Integer.parseInt(parteInteira);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] oeste = new int[100];
        int[] norte = new int[100];
        int[] sul = new int[100];
        int[] leste = new int[100];

        int contOeste = 0;
        int contNorte = 0;
        int contSul = 0;
        int contLeste = 0;

        String entrada = sc.nextLine();
        int entradaInteiro = getInteiro(entrada);

        while (entradaInteiro != 0) {
            if (entradaInteiro == -1) {
                entrada = sc.nextLine();
                entradaInteiro = getInteiro(entrada);
                while (entradaInteiro > 0) {
                    oeste[contOeste] = entradaInteiro;
                    contOeste++;
                    entrada = sc.nextLine();
                    entradaInteiro = getInteiro(entrada);
                }
            } else if (entradaInteiro == -2) {
                entrada = sc.nextLine();
                entradaInteiro = getInteiro(entrada);
                while (entradaInteiro > 0) {
                    sul[contSul] = entradaInteiro;
                    contSul++;
                    entrada = sc.nextLine();
                    entradaInteiro = getInteiro(entrada);
                }
            } else if (entradaInteiro == -3) {
                entrada = sc.nextLine();
                entradaInteiro = getInteiro(entrada);
                while (entradaInteiro > 0) {
                    norte[contNorte] = entradaInteiro;
                    contNorte++;
                    entrada = sc.nextLine();
                    entradaInteiro = getInteiro(entrada);
                }
            } else if (entradaInteiro == -4) {
                entrada = sc.nextLine();
                entradaInteiro = getInteiro(entrada);
                while (entradaInteiro > 0) {
                    leste[contLeste] = entradaInteiro;
                    contLeste++;
                    entrada = sc.nextLine();
                    entradaInteiro = getInteiro(entrada);
                }
            } else {
                entrada = sc.nextLine();
                entradaInteiro = getInteiro(entrada);
            }
        }

        // Intercalar segundo a prioridade -1 / -3 / -2 / -4
        int i = 0;
        while (i < contOeste || i < contNorte || i < contSul || i < contLeste) {
            if(i + 1 >= contOeste &&  i + 1 >= contNorte && i + 1 >= contSul && i + 1 >= contLeste)
            {
                if (i < contOeste) System.out.print("A" + oeste[i]);
                if (i < contNorte) System.out.print("A" + norte[i]);
                if (i < contSul) System.out.print("A" + sul[i]);
                if (i < contLeste) System.out.print("A" + leste[i]);
                i++;
            }
            else
            {
                if (i < contOeste) System.out.print("A" + oeste[i] + " ");
                if (i < contNorte) System.out.print("A" + norte[i] + " ");
                if (i < contSul) System.out.print("A" + sul[i] + " ");
                if (i < contLeste) System.out.print("A" + leste[i] + " ");
                i++;
            }
            
        }

        sc.close();
    }
}
