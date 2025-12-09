import java.io.*;
import java.util.*;

// -------------------- CLASSE CÉLULA --------------------
class Celula {
    public Games elemento;  // Armazena um objeto Games
    public Celula prox;     // Ponteiro para a próxima célula (lista encadeada)

    public Celula() {
        elemento = new Games();  // Cria um Game vazio por padrão
        prox = null;             // Próximo inicialmente nulo
    }

    public Celula(Games game) {
        elemento = game;
        prox = null;
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

// -------------------- CLASSE LISTA FLEXÍVEL --------------------
class Lista {
    public Celula primeiro; // Célula cabeça (não armazena Game)
    public Celula ultimo;   // Referência para o último nó

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    // Insere no início (após a cabeça)
    public void inserirInicio(Games game) {
        Celula nova = new Celula(game);
        nova.prox = primeiro.prox;
        primeiro.prox = nova;

        if (ultimo == primeiro) ultimo = nova;
    }

    // Insere no final
    public void inserirFim(Games game) {
        ultimo.prox = new Celula(game);
        ultimo = ultimo.prox;
    }

    // Insere em qualquer posição
    public void inserir(Games game, int pos) {
        int tam = tamanho();
        if (pos < 0 || pos > tam) throw new IllegalArgumentException("Posição inválida");
        else if (pos == 0) inserirInicio(game);
        else if (pos == tam) inserirFim(game);
        else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula nova = new Celula(game);
            nova.prox = i.prox;
            i.prox = nova;
        }
    }

    // Remove do início
    public Games removerInicio() {
        if (primeiro == ultimo) throw new IllegalArgumentException("Lista vazia");
        Celula tmp = primeiro.prox;
        primeiro.prox = tmp.prox;

        if (tmp == ultimo) ultimo = primeiro;

        tmp.prox = null;
        return tmp.elemento;
    }

    // Remove do final
    public Games removerFim() {
        if (primeiro == ultimo) throw new IllegalArgumentException("Lista vazia");
        Celula i;

        // Encontra a penúltima célula
        for (i = primeiro; i.prox != ultimo; i = i.prox);

        Games resp = ultimo.elemento;
        ultimo = i;
        ultimo.prox = null;
        return resp;
    }

    // Remove em posição específica
    public Games remover(int pos) {
        int tam = tamanho();
        if (pos < 0 || pos >= tam) throw new IllegalArgumentException("Posição inválida");
        else if (pos == 0) return removerInicio();
        else if (pos == tam - 1) return removerFim();

        Celula i = primeiro;

        for (int j = 0; j < pos; j++, i = i.prox);

        Celula tmp = i.prox;
        i.prox = tmp.prox;
        tmp.prox = null;
        return tmp.elemento;
    }

    // Conta o tamanho da lista
    public int tamanho() {
        int count = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) count++;
        return count;
    }

    // Imprime a lista
    public void mostrar() {
        int idx = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, idx++)
            System.out.printf("[%d] %s\n", idx, i.elemento.toString());
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

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Lê o arquivo CSV
        BufferedReader br = new BufferedReader(new FileReader("/tmp/games.csv"));
        Lista lista = new Lista();

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

            lista.inserirFim(g); // adiciona game lido
        }

        // Lista de resultados das buscas
        Lista pesquisa = new Lista();

        // Entrada dos IDs até "FIM"
        String entrada = sc.nextLine();

        while (!my_strcmp(entrada, "FIM")) {
            int idBusca = Integer.parseInt(entrada);

            // Busca linear
            for (Celula i = lista.primeiro.prox; i != null; i = i.prox) {
                if (i.elemento.getId() == idBusca) {
                    pesquisa.inserirFim(i.elemento);
                }
            }
            entrada = sc.nextLine();
        }

        // Número de operações na lista flexível
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String acao = sc.next();

            // Inserção no início
            if (acao.equals("II")) {
                int id1 = sc.nextInt();

                for (Celula j = lista.primeiro.prox; j != null; j = j.prox)
                    if (j.elemento.getId() == id1)
                        pesquisa.inserirInicio(j.elemento);

            }
            // Inserção no fim
            else if (acao.equals("IF")) {
                int id2 = sc.nextInt();

                for (Celula j = lista.primeiro.prox; j != null; j = j.prox)
                    if (j.elemento.getId() == id2)
                        pesquisa.inserirFim(j.elemento);

            }
            // Inserção em posição específica
            else if (acao.equals("I*")) {
                int pos = sc.nextInt();
                int id3 = sc.nextInt();

                for (Celula j = lista.primeiro.prox; j != null; j = j.prox)
                    if (j.elemento.getId() == id3)
                        pesquisa.inserir(j.elemento, pos);

            }
            // Remoção início
            else if (acao.equals("RI")) {
                Games g1 = pesquisa.removerInicio();
                System.out.println("(R) " + g1.getNome());
            }
            // Remoção fim
            else if (acao.equals("RF")) {
                Games g2 = pesquisa.removerFim();
                System.out.println("(R) " + g2.getNome());
            }
            // Remoção em posição
            else if (acao.equals("R*")) {
                int posR = sc.nextInt();
                Games g3 = pesquisa.remover(posR);
                System.out.println("(R) " + g3.getNome());
            }
        }

        // Imprime a lista final
        pesquisa.mostrar();

        sc.close();
        br.close();
    }
}
