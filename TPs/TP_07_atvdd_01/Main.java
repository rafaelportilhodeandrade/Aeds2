import java.io.*;
import java.util.*;

// -------------------- CLASSE NO --------------------
class No {
    public Games elemento;  
    public No dir;
    public No esq;

    public No() {
        elemento = new Games();  
        dir = null;             
        esq = null;
    }

    public No(Games game) {
        elemento = game;
        dir = null;             
        esq = null;
    }
}

// -------------------- CLASSE ARVORE --------------------
class Arvore {
    public No raiz;   
    public static int comparacoes = 0;

    public Arvore() {
        raiz = null;
    }

    public void inserir(Games game) {
        raiz = inserir(game, raiz);
    }
    
    private No inserir(Games game, No i) {
        if (i == null) {
            i = new No(game);
        } 
        else if (game.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(game, i.esq); 
        }
        else if (game.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(game, i.dir);
        }
        else {
            // Nomes iguais - pode inserir em qualquer lado ou tratar como quiser
            i.dir = inserir(game, i.dir);
        }
        return i;
    }

    public boolean pesquisar(String nome) {
        System.out.print(nome + ": =>raiz ");
        boolean resp = pesquisar(raiz, nome);
        if (resp) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }
        return resp;
    }

    private boolean pesquisar(No i, String nome) {
        boolean resp = false;
        while (i != null) {
            comparacoes++;
            if (nome.compareTo(i.elemento.getNome()) < 0) {
                System.out.print("esq ");
                i = i.esq;
            }
            else if (nome.compareTo(i.elemento.getNome()) > 0) {
                System.out.print("dir ");
                i = i.dir;
            }
            else {
                resp = true;
                i = null;
            }
        }
        return resp;
    }

    // Métodos de caminhamento (opcionais)
    public void caminharCentral() {
        caminharCentral(raiz);
    }
    
    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento.toString());
            caminharCentral(i.dir);
        }
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

    // Busca recursiva por ID na árvore
    public static boolean buscarPorId(No i, int idBusca, Arvore arvorePesquisa) {
        if (i == null) {
            return false;
        }
        
        // Busca na subárvore esquerda
        if (buscarPorId(i.esq, idBusca, arvorePesquisa)) {
            return true;
        }
        
        // Verifica o nó atual
        if (i.elemento.getId() == idBusca) {
            arvorePesquisa.inserir(i.elemento);
            return true;
        }
        
        // Busca na subárvore direita
        if (buscarPorId(i.dir, idBusca, arvorePesquisa)) {
            return true;
        }
        
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Lê o arquivo CSV
        BufferedReader br = new BufferedReader(new FileReader("/tmp/games.csv"));
        Arvore arvorePrincipal = new Arvore();

        br.readLine(); // Pula cabeçalho
        String linha;

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
            arvorePrincipal.inserir(g);
        }

        // Árvore para pesquisa por nome
        Arvore arvorePesquisa = new Arvore();
        
        // Entrada dos IDs até "FIM"
        String entradaId = sc.nextLine();
        while (!my_strcmp(entradaId, "FIM")) {
            int idBusca = Integer.parseInt(entradaId);
            
            // Busca recursiva na árvore principal pelo ID
            buscarPorId(arvorePrincipal.raiz, idBusca, arvorePesquisa);
            
            entradaId = sc.nextLine();
        }

        // Pesquisa por nomes
        long inicio = now();
        String entradaNome = sc.nextLine();
        while (!my_strcmp(entradaNome, "FIM")) {
            arvorePesquisa.pesquisar(entradaNome);
            entradaNome = sc.nextLine();
        }
        long fim = now();
        
        double tempoExecucao = (fim - inicio) / 1_000_000.0;
        
        // Gera arquivo de log
        try {
            PrintWriter log = new PrintWriter("890258_arvoreBinaria.txt");
            log.printf("890258\t%.2fms\t%dcomparacoes\n", tempoExecucao, Arvore.comparacoes);
            log.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
        }

        sc.close();
        br.close();
    }
}