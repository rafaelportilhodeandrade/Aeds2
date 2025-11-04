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
    // Cria um objeto Games com todos os atributos fornecidos
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

    // ---------------------- GETTER ----------------------
    // Retorna o ID do jogo
    public int getId() {
        return id;
    }

    // ---------------------- MOSTRAR ----------------------
    // Exibe todos os atributos do jogo formatados
    public void mostrar() {
        System.out.print("=> " + id + " ## " + nome + " ## " + lancamento + " ## " + players +
                " ## " + preco + " ## " + Arrays.toString(idiomas) + " ## " +
                notaCritica + " ## " + notaUsuarios + " ## " + conquistas + " ## " +
                Arrays.toString(empresasPub) + " ## " + Arrays.toString(empresasDev) +
                " ## " + Arrays.toString(categorias) + " ## " + Arrays.toString(generos) +
                " ## " + Arrays.toString(tags) + " ##\n");
    }

    // ---------------------- ORDENAR ----------------------
    // Ordena o array de Games pelo nome usando Bubble Sort
    public static void ordenar(Games[] games, int count) {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (games[j].nome.compareTo(games[j + 1].nome) > 0) {
                    Games temp = games[j];
                    games[j] = games[j + 1];
                    games[j + 1] = temp;
                }
            }
        }
    }

    // ---------------------- PESQUISA BINÁRIA ----------------------
    // Retorna o número de comparações até encontrar o jogo (positivo se encontrado, negativo se não encontrado)
    public static int pesquisaBinaria(Games[] games, int count, String nomeBuscado) {
        int esq = 0, dir = count - 1;
        int comparacoes = 0;

        while (esq <= dir) {
            int meio = (esq + dir) / 2;
            comparacoes++;
            int cmp = games[meio].nome.compareTo(nomeBuscado);

            if (cmp == 0) return comparacoes;  //  encontrado
            else if (cmp < 0) esq = meio + 1;  // busca na metade direita
            else dir = meio - 1;               // busca na metade esquerda
        }
        return -comparacoes;  // não encontrado
    }

    // ---------------------- SEPARAR ----------------------
    // Separa um campo da linha CSV considerando aspas e vírgulas
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
            if (pos < linha.length()) pos++;
            if (pos < linha.length() && linha.charAt(pos) == ',') pos++;
        } else {
            while (pos < linha.length() && linha.charAt(pos) != ',') {
                char c = linha.charAt(pos);
                if (c != '[' && c != ']' && c != '\'')
                    temp[0] += c;
                pos++;
            }
            if (pos < linha.length() && linha.charAt(pos) == ',') pos++;
        }

        temp[1] = String.valueOf(pos);  // retorna a próxima posição para leitura
        return temp;
    }

    // ---------------------- FORMATAR ARRAY ----------------------
    // Converte uma string separada por vírgulas em um array de strings
    public static String[] formatarArray(String array) {
        String[] tempArray = new String[100];
        int j = 0;
        String temp = "";
        int i = 0;

        if (i < array.length() && array.charAt(i) == ',') i++;

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
        if (!temp.equals("")) tempArray[j++] = removerEspacos(temp);

        String[] resultado = new String[j];
        for (int k = 0; k < j; k++) resultado[k] = tempArray[k];

        return resultado;
    }

    // ---------------------- FORMATAR DATA ----------------------
    // Converte datas no formato "Mês Dia, Ano" para "DD/MM/AAAA"
    public static String formatarData(String data) {
        if (data == null || removerEspacos(data).equals("")) return "01/01/0001";

        String[] partes = data.split(" ");
        String mesTexto = (partes.length > 0 && !removerEspacos(partes[0]).equals("")) ? removerEspacos(partes[0]) : "Jan";
        String dia = (partes.length > 1 && !removerEspacos(partes[1]).equals("")) ? partes[1].replace(",", "") : "01";
        String ano = (partes.length > 2 && !removerEspacos(partes[2]).equals("")) ? partes[2] : "0001";

        String mesNumero;
        switch (mesTexto) {
            case "Jan": mesNumero = "01"; break;
            case "Feb": mesNumero = "02"; break;
            case "Mar": mesNumero = "03"; break;
            case "Apr": mesNumero = "04"; break;
            case "May": mesNumero = "05"; break;
            case "Jun": mesNumero = "06"; break;
            case "Jul": mesNumero = "07"; break;
            case "Aug": mesNumero = "08"; break;
            case "Sep": mesNumero = "09"; break;
            case "Oct": mesNumero = "10"; break;
            case "Nov": mesNumero = "11"; break;
            case "Dec": mesNumero = "12"; break;
            default: mesNumero = "01";
        }

        if (dia.length() == 1) dia = "0" + dia;
        return dia + "/" + mesNumero + "/" + ano;
    }

    // ---------------------- FORMATAR LINHA ----------------------
    // Lê uma linha do CSV e cria um objeto Games preenchido
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

    // ---------------------- REMOVER ESPAÇOS ----------------------
    // Remove espaços no início e no fim da string
    public static String removerEspacos(String s) {
        if (s == null || s.equals("")) return "";
        int inicio = 0;
        int fim = s.length() - 1;
        while (inicio < s.length() && s.charAt(inicio) == ' ') inicio++;
        while (fim >= 0 && s.charAt(fim) == ' ') fim--;
        if (fim < inicio) return "";
        return s.substring(inicio, fim + 1);
    }

    // ---------------------- MAIN ----------------------
    // Função principal que lê o CSV, faz buscas por ID e nome, ordena e realiza pesquisa binária
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Games[] games = new Games[1850];
        int count = 0;
        Games[] gamesPesquisa = new Games[100];

        String caminhoArquivo = "/tmp/games.csv";

        // Leitura do arquivo CSV
        try (BufferedReader bf = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = bf.readLine()) != null) {
                if (!(linha.charAt(0) == 'A')) games[count++] = formatar(linha);
            }
        }

        int cont2 = 0;
        String entrada;

        // Busca por IDs informados pelo usuário e armazena em gamesPesquisa
        while (!(entrada = sc.nextLine()).equals("FIM")) {
            int idBusca = Integer.parseInt(entrada);
            boolean achou = false;
            for (int i = 0; i < count; i++) {
                if (games[i].getId() == idBusca) {
                    gamesPesquisa[cont2++] = games[i];
                    achou = true;
                    break;
                }
            }
            if (!achou) System.out.println("Jogo não encontrado.");
        }

        // Ordena os jogos para pesquisa binária
        ordenar(gamesPesquisa, cont2);

        long inicio = System.currentTimeMillis();
        int totalComparacoes = 0;

        // Pesquisa binária pelos nomes informados pelo usuário
        while (!(entrada = sc.nextLine()).equals("FIM")) {
            int res = pesquisaBinaria(gamesPesquisa, cont2, entrada);
            totalComparacoes += Math.abs(res);
            System.out.println(res > 0 ? " SIM" : " NAO");
        }

        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;

        // Grava resultados em arquivo
        try (PrintWriter writer = new PrintWriter(new File("suamatricula_binaria.txt"))) {
            writer.println("890258");
            writer.println("Tempo de execução: " + tempo + " ms");
            writer.println("Número de comparações: " + totalComparacoes);
        }

        sc.close();
    }
}
