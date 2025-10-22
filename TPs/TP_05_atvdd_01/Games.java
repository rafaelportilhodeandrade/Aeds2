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

    // ---------------------- Pesquisa Binaria ----------------------
    public static boolean pesquisaBinaria(Games[] games, int count, String nomeBuscado) {
        int esq = 0;
        int dir = count - 1;

        while (esq <= dir) {
            int meio = (esq + dir) / 2;
            int comparacao = games[meio].nome.compareTo(nomeBuscado);

            if (comparacao == 0) {
                
                return true;
            } else if (comparacao < 0) {
                
                esq = meio + 1;
            } else {
                
                dir = meio - 1;
            }
        }

        return false;
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
                    tempArray[j++] = temp.trim();
                    temp = "";
                }
            } else {
                temp += array.charAt(i);
            }
        }
        if (!temp.equals(""))
            tempArray[j++] = temp.trim();

        String[] resultado = new String[j];
        for (int k = 0; k < j; k++)
            resultado[k] = tempArray[k];

        return resultado;
    }

    // ------------------- FORMATAR DATA --------------------
    public static String formatarData(String data) {
        if (data == null || data.trim().equals("")) {
            return "01/01/0001";
        }

        String[] partes = data.split(" ");

        String mesTexto = (partes.length > 0 && !partes[0].trim().equals("")) ? partes[0].trim() : "Jan";

        String dia = (partes.length > 1 && !partes[1].trim().equals(""))
                ? partes[1].replace(",", "").trim()
                : "01";

        String ano = (partes.length > 2 && !partes[2].trim().equals(""))
                ? partes[2].trim()
                : "0001";

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

        if (dia.equals("")) {
            dia = "01";
        }

        if (dia.length() == 1) {
            dia = "0" + dia;
        }

        return dia + "/" + mesNumero + "/" + ano;
    }

    // ---------------------- FORMATAR LINHA ----------------------
    public static Games formatar(String linha) {
        int pos = 0;
        String[] temp;

        // id
        temp = separar(linha, pos);
        int id = Integer.parseInt(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // nome
        temp = separar(linha, pos);
        String nome = temp[0].trim();
        pos = Integer.parseInt(temp[1]);

        // lançamento
        temp = separar(linha, pos);
        String lancamento = temp[0].trim();
        pos = Integer.parseInt(temp[1]);
        lancamento = formatarData(lancamento);

        // players
        temp = separar(linha, pos);
        int players = temp[0].trim().equals("") ? 0 : Integer.parseInt(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // preço
        temp = separar(linha, pos);
        float preco = temp[0].trim().equals("") ? 0.0f : Float.parseFloat(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // idiomas (array)
        temp = separar(linha, pos);
        String[] idiomas = formatarArray(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // nota crítica
        temp = separar(linha, pos);
        int notaCritica = temp[0].trim().equals("") ? -1 : Integer.parseInt(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // nota usuários
        temp = separar(linha, pos);
        float notaUsuarios = temp[0].trim().equals("") ? -1.0f : Float.parseFloat(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // conquistas
        temp = separar(linha, pos);
        int conquistas = temp[0].trim().equals("") ? 0 : Integer.parseInt(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // empresas publicadoras
        temp = separar(linha, pos);
        String[] empresasPub = formatarArray(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // empresas desenvolvedoras
        temp = separar(linha, pos);
        String[] empresasDev = formatarArray(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // categorias
        temp = separar(linha, pos);
        String[] categorias = formatarArray(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // gêneros
        temp = separar(linha, pos);
        String[] generos = formatarArray(temp[0].trim());
        pos = Integer.parseInt(temp[1]);

        // tags
        temp = separar(linha, pos);
        String[] tags = formatarArray(temp[0].trim());

        return new Games(
                id, nome, lancamento, players, preco,
                idiomas, notaCritica, notaUsuarios, conquistas,
                empresasPub, empresasDev, categorias, generos, tags);
    }

    // ---------------------- MAIN ----------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Games[] games = new Games[1850];
        int count = 0;

        //String caminhoArquivo = "C:\\Users\\rafae\\Desktop\\java\\games.csv";
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
        if (games[i].getId() == idBusca)
        {
        //games[i].mostrar();
        achou = true;
        i = count;
        }
        }
        if (!achou)
        System.out.println("Jogo não encontrado.");
        }

        ordenar(games, count);
        // for(int i = 0; i < count; i++)
        // {
        // games[i].mostrar();
        // }

        while (!(entrada = sc.nextLine()).equals("FIM")) {
            if(pesquisaBinaria(games, count, entrada))
            {
                System.out.println(" SIM");
            }
            else System.out.println(" NAO");
        }

        sc.close();
    }
}
