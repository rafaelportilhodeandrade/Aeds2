import java.util.Scanner;

public class ExpressaoBooleana {

    // Avalia uma expressão booleana representada como string
    public static boolean avaliar(String expr) {
        expr = removerEspacos(expr);

        // Caso base: expressão de um único dígito 
        if (expr.length() == 1 && expr.charAt(0) >= '0' && expr.charAt(0) <= '1') {
            return expr.charAt(0) == '1';
        }

        // Operação NOT
        if (expr.startsWith("not(")) {
            String dentro = extrair(expr, 4, expr.length()-1);
            return !avaliar(dentro);
        }

        // Operação AND
        if (expr.startsWith("and(")) {
            String dentro = extrair(expr, 4, expr.length()-1);
            return avaliarLista(dentro, "and");
        }

        // Operação OR
        if (expr.startsWith("or(")) {
            String dentro = extrair(expr, 3, expr.length()-1);
            return avaliarLista(dentro, "or");
        }

        return false;
    }

    // Avalia uma lista de expressões separadas por vírgula dentro de um and ou or
    public static boolean avaliarLista(String s, String op) {
        int nivel = 0;
        String atual = "";
        boolean resultado = (op.charAt(0) == 'a' ? true : false);

        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') nivel++;
            if (c == ')') nivel--;
            if (c == ',' && nivel == 0) {
                boolean valor = avaliar(removerEspacos(atual));
                if (op.charAt(0) == 'a') resultado = resultado && valor;
                else resultado = resultado || valor;
                atual = "";
            } else {
                if (!(c == ',' && nivel==0)) {
                    atual += c;
                }
            }
        }
        if (atual.length()>0) {
            boolean valor = avaliar(removerEspacos(atual));
            if (op.charAt(0) == 'a') resultado = resultado && valor;
            else resultado = resultado || valor;
        }
        return resultado;
    }

    // Extrai substring entre posições específicas
    public static String extrair(String s, int ini, int fim) {
        String r = "";
        for (int i=ini; i<fim; i++) {
            r += s.charAt(i);
        }
        return r;
    }

    // Remove espaços e tabulações da string
    public static String removerEspacos(String s) {
        String r = "";
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) != ' ' && s.charAt(i) != '\t') {
                r += s.charAt(i);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            String linha = sc.nextLine();

            // '0' no início encerra a execução
            if (linha.charAt(0) == '0') {
                continuar = false;
            } else {
                // Número de variáveis
                int n = linha.charAt(0) - '0';
                int pos = 2;

                // Leitura dos valores das variáveis
                char[] vars = new char[n];
                for (int i=0; i<n; i++) {
                    vars[i] = linha.charAt(pos);
                    pos += 2;
                }

                // Extrai a expressão após os valores
                String expr = "";
                for (int i=pos; i<linha.length(); i++) {
                    expr += linha.charAt(i);
                }

                // Substitui variáveis por 0/1
                String novaExpr = "";
                for (int i=0; i<expr.length(); i++) {
                    char c = expr.charAt(i);
                    if (c >= 'A' && c <= 'Z') {
                        int idx = c - 'A';
                        if (idx < n) novaExpr += vars[idx];
                        else novaExpr += c;
                    } else {
                        novaExpr += c;
                    }
                }

                // Avalia expressão e imprime resultado
                boolean valor = avaliar(novaExpr);
                if (valor) System.out.println("1");
                else System.out.println("0");
            }
        }

        sc.close();
    }
}
