import java.io.*;
import java.util.*;

// -------------------- CLASSE NO1 (Primeira Árvore) --------------------
class No1 {
    public int inteiro;
    public No1 esq, dir;
    public Arvore2 game;

    public No1() {
        inteiro = 0;
        esq = dir = null;
        game = new Arvore2();
    }
    
    public No1(int x) {
        inteiro = x;
        esq = dir = null;
        game = new Arvore2();
    }
}

// -------------------- CLASSE NO2 (Segunda Árvore) --------------------
class No2 {
    public Games elemento;
    public No2 esq, dir;

    public No2() {
        elemento = new Games();
        esq = dir = null;
    }
    
    public No2(Games game) {
        elemento = game;
        esq = dir = null;
    }
}

// -------------------- CLASSE ARVORE1 (Primeira Árvore) --------------------
class Arvore1 {
    public No1 raiz;
    public static int comparacoes = 0;

    public Arvore1() {
        raiz = null;
    }

    public void inserir(int x) {
        raiz = inserir(raiz, x);
    }
    
    private No1 inserir(No1 i, int x) {
        if (i == null) {
            i = new No1(x);
        } else if (x > i.inteiro) {
            i.dir = inserir(i.dir, x);
        } else if (x < i.inteiro) {
            i.esq = inserir(i.esq, x);
        }
        return i;
    }

    public No1 pesquisarJogadores(int x) {
        return pesquisarJogadores(raiz, x);
    }
    
    private No1 pesquisarJogadores(No1 i, int x) {
        No1 resp = null;
        while (i != null) {
            if (x > i.inteiro) {
                i = i.dir;
            } else if (x < i.inteiro) {
                i = i.esq;
            } else {
                resp = i;
                i = null;
            }
        }
        return resp;
    }

    public void pesquisar(String str) {
        System.out.print(str + " => raiz ");
        if (pesquisar(raiz, str)) {
            System.out.println(" SIM");
        } else {
            System.out.println(" NAO");
        }
    }
    
    private boolean pesquisar(No1 i, String str) {
        boolean resp = false;
        if (i != null) {
            comparacoes++;
            resp = i.game.pesquisar(str);
            if (!resp) {
                System.out.print(" ESQ ");
                resp = pesquisar(i.esq, str);
            }
            if (!resp) {
                System.out.print(" DIR ");
                resp = pesquisar(i.dir, str);
            }
        }
        return resp;
    }
}

// -------------------- CLASSE ARVORE2 (Segunda Árvore) --------------------
class Arvore2 {
    public No2 raiz;

    public Arvore2() {
        raiz = null;
    }

    public void inserir(Games game) {
        raiz = inserir(raiz, game);
    }
    
    private No2 inserir(No2 i, Games game) {
        if (i == null) {
            i = new No2(game);
        } else if (game.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(i.dir, game);
        } else if (game.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(i.esq, game);
        }
        return i;
    }

    public boolean pesquisar(String str) {
        return pesquisar(raiz, str);
    }
    
    private boolean pesquisar(No2 i, String str) {
        boolean resp = false;
        while (i != null) {
            Arvore1.comparacoes++;
            if (str.compareTo(i.elemento.getNome()) > 0) {
                System.out.print("dir ");
                i = i.dir;
            } else if (str.compareTo(i.elemento.getNome()) < 0) {
                System.out.print("esq ");
                i = i.esq;
            } else {
                resp = true;
                i = null;
            }
        }
        return resp;
    }
}

// -------------------- CLASSE GAME --------------------
class Games {
    // Atributos correspondentes às colunas do CSV
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

    // Construtor padrão inicializa tudo com valores neutros
    public Games() {
        id = 0;
        nome = "";
        lancamento = "01/01/0000";
        players = 0;
        preco = 0;
        idiomas = new String[0];
        notaCritica = -1;
        notaUsuarios = -1;
        conquistas = 0;
        empresasPub = new String[0];
        empresasDev = new String[0];
        categorias = new String[0];
        generos = new String[0];
        tags = new String[0];
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getPlayers() { return players; }

    // Converte o ID lido (string do CSV) para int
    public void setId(String id) {
        this.id = id.equals("") ? 0 : Integer.parseInt(id);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Converte formato textual de data (ex: "Dec 10, 2020") para dd/mm/yyyy
    public void setLancamento(String lancamento) {
        if (lancamento == null || lancamento.equals("")) {
            this.lancamento = "01/01/0000";
            return;
        }

        String[] partes = lancamento.split(" ");
        String mes = "01";
        String dia = "01";
        String ano = "0000";

        // Converte o mês abreviado para número
        if (partes.length >= 3) {
            String mesTxt = partes[0];
            dia = partes[1].replace(",", "");
            ano = partes[2];

            switch (mesTxt) {
                case "Jan": mes = "01"; break;
                case "Feb": mes = "02"; break;
                case "Mar": mes = "03"; break;
                case "Apr": mes = "04"; break;
                case "May": mes = "05"; break;
                case "Jun": mes = "06"; break;
                case "Jul": mes = "07"; break;
                case "Aug": mes = "08"; break;
                case "Sep": mes = "09"; break;
                case "Oct": mes = "10"; break;
                case "Nov": mes = "11"; break;
                case "Dec": mes = "12"; break;
            }
        }

        // Garante que o dia tenha 2 dígitos
        if (dia.length() == 1) dia = "0" + dia;

        this.lancamento = dia + "/" + mes + "/" + ano;
    }

    // Extrai apenas números da string de players
    public void setPlayers(String players) {
        String aux = "";
        for (int i = 0; i < players.length(); i++) {
            if (Character.isDigit(players.charAt(i))) aux += players.charAt(i);
        }
        this.players = aux.equals("") ? 0 : Integer.parseInt(aux);
    }

    // Converte preço; "Free to Play" = 0.0
    public void setPreco(String preco) {
        if (preco.equals("Free to Play")) this.preco = 0.0f;
        else this.preco = preco.equals("") ? 0 : Float.parseFloat(preco);
    }

    // Demais setters apenas delegam para o método formatar
    public void setIdiomas(String idiomas) { this.idiomas = formatar(idiomas); }
    public void setNotaCritica(String nota) { this.notaCritica = nota.equals("") ? -1 : Integer.parseInt(nota); }
    public void setNotaUsuarios(String nota) { this.notaUsuarios = (nota.equals("") || nota.equals("tbd")) ? -1 : Float.parseFloat(nota); }
    public void setConquistas(String conquistas) { this.conquistas = conquistas.equals("") ? 0 : Integer.parseInt(conquistas); }
    public void setEmpresasPub(String pub) { this.empresasPub = formatar(pub); }
    public void setEmpresasDev(String dev) { this.empresasDev = formatar(dev); }
    public void setCategorias(String cat) { this.categorias = formatar(cat); }
    public void setGeneros(String gen) { this.generos = formatar(gen); }
    public void setTags(String tags) { this.tags = formatar(tags); }

    private String[] formatar(String entrada) {
        if (entrada == null || entrada.equals("[]") || entrada.equals("")) return new String[0];

        entrada = entrada.replace("[", "").replace("]", "");
        String[] partes = entrada.split(",");

        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i].replace("'", "");

            String str = partes[i];
            int start = 0;
            int end = str.length();

            // Remove espaços do início
            while (start < end && str.charAt(start) == ' ') start++;

            // Remove espaços do final
            while (end > start && str.charAt(end - 1) == ' ') end--;

            partes[i] = str.substring(start, end);
        }
        return partes;
    }

    // Converte array para string formatada
    private String mostrarArray(String[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Formato de impressão do game
    @Override
    public String toString() {
        return "=> " + id + " ## " + nome + " ## " + lancamento + " ## " + players +
                " ## " + preco + " ## " + mostrarArray(idiomas) + " ## " + notaCritica +
                " ## " + notaUsuarios + " ## " + conquistas + " ## " + mostrarArray(empresasPub) +
                " ## " + mostrarArray(empresasDev) + " ## " + mostrarArray(categorias) +
                " ## " + mostrarArray(generos) + " ## " + mostrarArray(tags) + " ##";
    }
}

// -------------------- MAIN --------------------
public class Main {
    // Preenche o objeto Games com todos os campos
    public static void settar(Games g, String[] arr) {
        g.setId(arr[0]);
        g.setNome(arr[1]);
        g.setLancamento(arr[2]);
        g.setPlayers(arr[3]);
        g.setPreco(arr[4]);
        g.setIdiomas(arr[5]);
        g.setNotaCritica(arr[6]);
        g.setNotaUsuarios(arr[7]);
        g.setConquistas(arr[8]);
        g.setEmpresasPub(arr[9]);
        g.setEmpresasDev(arr[10]);
        g.setCategorias(arr[11]);
        g.setGeneros(arr[12]);
        g.setTags(arr[13]);
    }

    // Simples comparação de strings
    public static boolean my_strcmp(String a, String b) {
        return a.equals(b);
    }

    public static long now() {
        return System.nanoTime();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Lê o arquivo CSV
        BufferedReader br = new BufferedReader(new FileReader("/tmp/games.csv"));
        
        // Cria a primeira árvore com os valores especificados
        Arvore1 arvorePrincipal = new Arvore1();
        int[] numeros = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int i = 0; i < numeros.length; i++) {
            arvorePrincipal.inserir(numeros[i]);
        }

        br.readLine(); // Pula cabeçalho
        String linha;

        // Array para armazenar todos os jogos
        Games[] games = new Games[5000];
        int totalGames = 0;

        // --------- PARSE MANUAL DO CSV (SUPORTA CAMPOS COM ASPAS) ---------
        while ((linha = br.readLine()) != null) {
            String[] campos = new String[14];
            String aux = "";
            boolean aspas = false;
            int c = 0;

            // Parsing manual controlando vírgulas dentro de aspas
            for (int i = 0; i < linha.length(); i++) {
                char ch = linha.charAt(i);

                if (ch == '"') aspas = !aspas;
                else if (ch == ',' && !aspas) {
                    campos[c++] = aux;
                    aux = "";
                }
                else aux += ch;
            }
            campos[c] = aux; // último campo

            Games g = new Games();
            settar(g, campos);
            games[totalGames++] = g;
        }

        // Entrada dos IDs até "FIM"
        String entradaId = sc.nextLine();
        while (!my_strcmp(entradaId, "FIM")) {
            int idBusca = Integer.parseInt(entradaId);
            
            // Busca linear pelo ID
            for (int i = 0; i < totalGames; i++) {
                if (games[i].getId() == idBusca) {
                    // Calcula players % 15 e insere na árvore correspondente
                    int mod = games[i].getPlayers() % 15;
                    No1 no = arvorePrincipal.pesquisarJogadores(mod);
                    if (no != null) {
                        no.game.inserir(games[i]);
                    }
                    i = totalGames;
                }
            }
            
            entradaId = sc.nextLine();
        }

        // Pesquisa por nomes
        long inicio = now();
        String entradaNome = sc.nextLine();
        while (!my_strcmp(entradaNome, "FIM")) {
            System.out.print("=> ");
            arvorePrincipal.pesquisar(entradaNome);
            entradaNome = sc.nextLine();
        }
        long fim = now();
        
        double tempoExecucao = (fim - inicio) / 1_000_000.0;
        
        // Gera arquivo de log
        try {
            PrintWriter log = new PrintWriter("890258_arvoredeArvores.txt");
            log.printf("890258\t%.2fms\t%dcomparacoes\n", tempoExecucao, Arvore1.comparacoes);
            log.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
        }

        sc.close();
        br.close();
    }
}