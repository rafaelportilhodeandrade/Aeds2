import java.util.Scanner;

public class App
{
    // Função que substitui o equals, returnando false quando a string p tiver dentro dela a frase "FIM", caso contrario retorna true.
    static boolean igual(String p)
    {
        if(p.length() == 3 && 
        p.charAt(0) == 'F' &&
        p.charAt(1)== 'I' &&
        p.charAt(2) == 'M')
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // Função que vai comparando a primeira metado com a segunda metade da string para ver se ela é um palindromo, 
    // retornando true caso seja e false caso não seja.
    static boolean palindromo(String p)
    {
        int inicio = 0;
        int fim = p.length() - 1;

        while (inicio < fim) 
        {
            if (p.charAt(inicio) != p.charAt(fim))
            {
                return false;
            }    
            inicio++;
            fim--;
        }
        return true;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        String p = sc.nextLine();

        // Repetição que roda enquanto a função igual for true e printa "SIM" se a função palindromo retornar true e "NAO" caso contrário. 
        while (igual(p)) 
        {
                if (palindromo(p)) 
                {
                    System.out.println("SIM");
                } else 
                {
                    System.out.println("NAO");
                }
            p = sc.nextLine();
        }
        
        sc.close();
        
    }
}
