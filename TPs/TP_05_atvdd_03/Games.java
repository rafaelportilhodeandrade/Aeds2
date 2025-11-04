import java.io.*;
import java.util.*;

public class Games {
    private int id;
    private String nome;
    private String lancamento;
    private int players;
    private float preco;
    private String[] idiomas;
    private int notaCritica;
    private float notaUsuarios;
    private int conquistas;
    private String[] empresasPub;
    private String[] empresasDev;
    private String[] categorias;
    private String[] generos;
    private String[] tags;

    // ---------------------- CONSTRUTOR ----------------------
    public Games(int id, String nome, String lancamento, int players, float preco,
            String[] idiomas, int notaCritica, float notaUsuarios, int conquistas,
            String[] empresasPub, String[] empresasDev, String[] categorias,
            String[] generos, String[] tags) {
        this.id = id;
        this.nome = nome;
        this.lancamento = lancamento;
        this.players = players;
        this.preco = preco;
        this.idiomas = idiomas;
        this.notaCritica = notaCritica;
        this.notaUsuarios = notaUsuarios;
        this.conquistas = conquistas;
        this.empresasPub = empresasPub;
        this.empresasDev = empresasDev;
        this.categorias = categorias;
        this.generos = generos;
        this.tags = tags;
    }

    // ---------------------- GETTERS ----------------------
    public int getId() {
        return id;
    }

    // ---------------------- MOSTRAR ----------------------
    public void mostrar() {
        System.out.print("=> " + id + " ## " + nome + " ## " + lancamento + " ## " + players +
                " ## " + preco + " ## " + Arrays.toString(idiomas) + " ## " +
                notaCritica + " ## " + notaUsuarios + " ## " + conquistas + " ## " +
                Arrays.toString(empresasPub) + " ## " + Arrays.toString(empresasDev) +
                " ## " + Arrays.toString(categorias) + " ## " + Arrays.toString(generos) +
                " ## " + Arrays.toString(tags) + " ##\n");
    }

    // ---------------------- ORDENAR ----------------------
    private static long comparacoes = 0;
    private static long movimentacoes = 0;

    public static void HeapSort(Games[] games, int count) {
        // Construir o heap (máximo)
        for (int i = count / 2 - 1; i >= 0; i--) {
            heapify(games, count, i);
        }

        // Extrair elementos do heap um a um
        for (int i = count - 1; i > 0; i--) {
            Games temp = games[0];
            games[0] = games[i];
            games[i] = temp;
            movimentacoes += 3; // cada troca conta como 3 movimentações

            heapify(games, i, 0);
        }
    }

    private static void heapify(Games[] games, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n)
            comparacoes++;
        if (esq < n && comparar(games[esq], games[maior]) > 0)
            maior = esq;

        if (dir < n)
            comparacoes++;
        if (dir < n && comparar(games[dir], games[maior]) > 0)
            maior = dir;

        if (maior != i) {
            Games temp = games[i];
            games[i] = games[maior];
            games[maior] = temp;
            movimentacoes += 3;

            heapify(games, n, maior);
        }
    }

    // Função de comparação: ordena por players, e em caso de empate, por id
    private static int comparar(Games g1, Games g2) {
        if (g1 == null && g2 == null)
            return 0;
        if (g1 == null)
            return -1;
        if (g2 == null)
            return 1;

        // Primeiro critério: players
        if (g1.players > g2.players)
            return 1;
        else if (g1.players < g2.players)
            return -1;
        else {
            // Desempate: id
            if (g1.id > g2.id)
                return 1;
            else if (g1.id < g2.id)
                return -1;
            else
                return 0;
        }
    }

    // ---------------------- SEPARAR ----------------------
    public static String[] separar(String linha, int pos) {
        String[] temp = new String[2];
        temp[0] = "";

        if (pos < linha.length() && linha.charAt(pos) == '"') {
            pos++;
            while (pos < linha.length() && linha.charAt(pos) != '"') {
                char c = linha.charAt(pos);
                if (c != '[' && c != ']' && c != '\'')
                    temp[0] += c;
                pos++;
            }
            if (pos < linha.length())
                pos++;
            if (pos < linha.length() && linha.charAt(pos) == ',')
                pos++;
        } else {
            while (pos < linha.length() && linha.charAt(pos) != ',') {
                char c = linha.charAt(pos);
                if (c != '[' && c != ']' && c != '\'')
                    temp[0] += c;
                pos++;
            }
            if (pos < linha.length() && linha.charAt(pos) == ',')
                pos++;
        }

        temp[1] = String.valueOf(pos);
        return temp;
    }

    // ---------------------- FORMATAR ARRAY ----------------------
    public static String[] formatarArray(String array) {
        String[] tempArray = new String[100];
        int j = 0;
        String temp = "";
        int i = 0;

        if (i < array.length() && array.charAt(i) == ',')
            i++;

        for (; i < array.length(); i++) {
            if (array.charAt(i) == ',') {
                if (!temp.equals("")) {
                    tempArray[j++] = removerEspacos(temp);
                    temp = "";
                }
            } else {
                temp += array.charAt(i);
            }
        }
        if (!temp.equals(""))
            tempArray[j++] = removerEspacos(temp);

        String[] resultado = new String[j];
        for (int k = 0; k < j; k++)
            resultado[k] = tempArray[k];

        return resultado;
    }

    // ------------------- FORMATAR DATA --------------------
    public static String formatarData(String data) {
        if (data == null || removerEspacos(data).equals("")) {
            return "01/01/0001";
        }

        String[] partes = data.split(" ");

        String mesTexto = (partes.length > 0 && !removerEspacos(partes[0]).equals("")) ? removerEspacos(partes[0])
                : "Jan";
        String dia = (partes.length > 1 && !removerEspacos(partes[1]).equals("")) ? partes[1].replace(",", "") : "01";
        String ano = (partes.length > 2 && !removerEspacos(partes[2]).equals("")) ? partes[2] : "0001";

        String mesNumero;
        switch (mesTexto) {
            case "Jan":
                mesNumero = "01";
                break;
            case "Feb":
                mesNumero = "02";
                break;
            case "Mar":
                mesNumero = "03";
                break;
            case "Apr":
                mesNumero = "04";
                break;
            case "May":
                mesNumero = "05";
                break;
            case "Jun":
                mesNumero = "06";
                break;
            case "Jul":
                mesNumero = "07";
                break;
            case "Aug":
                mesNumero = "08";
                break;
            case "Sep":
                mesNumero = "09";
                break;
            case "Oct":
                mesNumero = "10";
                break;
            case "Nov":
                mesNumero = "11";
                break;
            case "Dec":
                mesNumero = "12";
                break;
            default:
                mesNumero = "01";
        }

        if (dia.equals(""))
            dia = "01";
        if (dia.length() == 1)
            dia = "0" + dia;

        return dia + "/" + mesNumero + "/" + ano;
    }

    // ---------------------- FORMATAR LINHA ----------------------
    public static Games formatar(String linha) {
        int pos = 0;
        String[] temp;

        temp = separar(linha, pos);
        int id = Integer.parseInt(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        String nome = removerEspacos(temp[0]);
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        String lancamento = removerEspacos(temp[0]);
        pos = Integer.parseInt(temp[1]);
        lancamento = formatarData(lancamento);

        temp = separar(linha, pos);
        int players = removerEspacos(temp[0]).equals("") ? 0 : Integer.parseInt(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        float preco = removerEspacos(temp[0]).equals("") ? 0.0f : Float.parseFloat(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        String[] idiomas = formatarArray(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        int notaCritica = removerEspacos(temp[0]).equals("") ? -1 : Integer.parseInt(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        float notaUsuarios = removerEspacos(temp[0]).equals("") ? -1.0f : Float.parseFloat(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        int conquistas = removerEspacos(temp[0]).equals("") ? 0 : Integer.parseInt(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        String[] empresasPub = formatarArray(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        String[] empresasDev = formatarArray(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        String[] categorias = formatarArray(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        String[] generos = formatarArray(removerEspacos(temp[0]));
        pos = Integer.parseInt(temp[1]);

        temp = separar(linha, pos);
        String[] tags = formatarArray(removerEspacos(temp[0]));

        return new Games(id, nome, lancamento, players, preco, idiomas, notaCritica, notaUsuarios,
                conquistas, empresasPub, empresasDev, categorias, generos, tags);
    }

    // ---------------------- FUNÇÃO AUXILIAR ----------------------
    public static String removerEspacos(String s) {
        if (s == null || s.equals(""))
            return "";
        int inicio = 0;
        int fim = s.length() - 1;

        while (inicio < s.length() && s.charAt(inicio) == ' ')
            inicio++;
        while (fim >= 0 && s.charAt(fim) == ' ')
            fim--;

        if (fim < inicio)
            return "";
        String resultado = "";
        for (int i = inicio; i <= fim; i++)
            resultado += s.charAt(i);

        return resultado;
    }

    // ---------------------- MAIN ----------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Games[] games = new Games[1850];
        int count = 0;
        int cont2 = 0;
        Games[] gamesPesquisa = new Games[100];

        // String caminhoArquivo = "C:\\Users\\rafae\\Desktop\\java\\games.csv";
        String caminhoArquivo = "/tmp/games.csv";

        try (BufferedReader bf = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = bf.readLine()) != null) {
                if (!(linha.charAt(0) == 'A')) {
                    games[count++] = formatar(linha);
                }
            }
        } catch (IOException e) {
            throw new Error("erro");
        }

        String entrada;

        while (!(entrada = sc.nextLine()).equals("FIM")) {
            int idBusca = Integer.parseInt(entrada);
            boolean achou = false;
            for (int i = 0; i < count; i++) {
                if (games[i].getId() == idBusca) {
                    gamesPesquisa[cont2] = games[i];
                    cont2++;
                    achou = true;
                    i = count;
                }
            }
            if (!achou)
                System.out.println("Jogo não encontrado.");
        }

        long inicio = System.nanoTime();
        HeapSort(gamesPesquisa, cont2); 
        long fim = System.nanoTime();

        double tempo = (fim - inicio) / 1e6; // tempo em ms

        // Imprimir resultados no arquivo
        try (PrintWriter pw = new PrintWriter("890258_heapsort.txt")) {
            pw.println("890258");
            pw.println("Tempo de execução: " + (long) tempo + " ms");
            pw.println("Comparacoes: " + comparacoes);
            pw.println("Movimentacoes: " + movimentacoes);
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo.");
        }

        for (int i = 0; i < 100; i++) {
            gamesPesquisa[i].mostrar();
        }

        sc.close();
    }
}
