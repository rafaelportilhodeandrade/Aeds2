import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int execucoes;
            do{
                execucoes = sc.nextInt();
            }while(execucoes < 0 || execucoes > 103);
            int aux = 0;

            for (int i = 0; i < execucoes; i++) {
                int tempo;
                int ciclos;
                do{
                    tempo = sc.nextInt();
                    ciclos = sc.nextInt();
                }while(tempo <= 0 || tempo >= 103 || ciclos <= 0 || ciclos >= 103);

                if (i < execucoes - 1) {
                    aux += ciclos;
                }
            }

            System.out.println(aux);
        }

        sc.close();
    }
}