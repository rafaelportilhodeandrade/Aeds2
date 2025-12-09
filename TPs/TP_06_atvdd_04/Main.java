import java.io.*;
import java.util.*;

// -------------------- CLASSE CÉLULA --------------------
class Celula {
    public Games elemento;
    public Celula prox;

    public Celula() {
        elemento = new Games();
        prox = null;
    }

    public Celula(Games game) {
        elemento = game;
        prox = null;
    }
}

// -------------------- CLASSE GAME --------------------
class Games {
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

    public void setId(String id) {
        this.id = id.equals("") ? 0 : Integer.parseInt(id);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLancamento(String lancamento) {
        if (lancamento == null || lancamento.equals("")) {
            this.lancamento = "01/01/0000";
            return;
        }

        String[] partes = lancamento.split(" ");
        String mes = "01";
        String dia = "01";
        String ano = "0000";

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

        if (dia.length() == 1) dia = "0" + dia;
        this.lancamento = dia + "/" + mes + "/" + ano;
    }

    public void setPlayers(String players) {
        String aux = "";
        for (int i = 0; i < players.length(); i++) {
            if (Character.isDigit(players.charAt(i))) aux += players.charAt(i);
        }
        this.players = aux.equals("") ? 0 : Integer.parseInt(aux);
    }

    public void setPreco(String preco) {
        if (preco.equals("Free to Play")) this.preco = 0.0f;
        else this.preco = preco.equals("") ? 0 : Float.parseFloat(preco);
    }

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
            
            // Remover espaços em branco no início e fim sem usar trim()
            String str = partes[i];
            int start = 0;
            int end = str.length();
            
            // Encontrar primeiro caractere não espaço
            while (start < end && str.charAt(start) == ' ') {
                start++;
            }
            
            // Encontrar último caractere não espaço
            while (end > start && str.charAt(end - 1) == ' ') {
                end--;
            }
            
            partes[i] = str.substring(start, end);
        }
        return partes;
    }

   

    private String mostrarArray(String[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "=> " + id + " ## " + nome + " ## " + lancamento + " ## " + players +
                " ## " + preco + " ## " + mostrarArray(idiomas) + " ## " + notaCritica +
                " ## " + notaUsuarios + " ## " + conquistas + " ## " + mostrarArray(empresasPub) +
                " ## " + mostrarArray(empresasDev) + " ## " + mostrarArray(categorias) +
                " ## " + mostrarArray(generos) + " ## " + mostrarArray(tags) + " ##";
    }
}

// -------------------- CLASSE FILA DINÂMICA --------------------
class Fila {
    private Celula primeiro;
    private Celula ultimo;

    public Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void enfileirar(Games game) { // inserir no fim
        ultimo.prox = new Celula(game);
        ultimo = ultimo.prox;
    }

    public Games desenfileirar() { // remover do início
        if (primeiro == ultimo) throw new IllegalArgumentException("Fila vazia");
        Celula tmp = primeiro.prox;
        primeiro.prox = tmp.prox;
        if (tmp == ultimo) ultimo = primeiro;
        tmp.prox = null;
        return tmp.elemento;
    }

    public void mostrar() {
        int idx = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, idx++)
            System.out.printf("[%d] %s\n", idx, i.elemento.toString());
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }
}

// -------------------- MAIN --------------------
public class Main {
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

    public static boolean my_strcmp(String a, String b) {
        return a.equals(b);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("/tmp/games.csv"));
        Fila fila = new Fila();

        br.readLine();
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] campos = new String[14];
            String aux = "";
            boolean aspas = false;
            int c = 0;
            for (int i = 0; i < linha.length(); i++) {
                char ch = linha.charAt(i);
                if (ch == '"') aspas = !aspas;
                else if (ch == ',' && !aspas) {
                    campos[c++] = aux;
                    aux = "";
                } else aux += ch;
            }
            campos[c] = aux;

            Games g = new Games();
            settar(g, campos);
            fila.enfileirar(g); // insere todos na fila principal
        }

        Fila pesquisa = new Fila();
        String entrada = sc.nextLine();
        while (!my_strcmp(entrada, "FIM")) {
            int idBusca = Integer.parseInt(entrada);
            // percorre a fila original (ineficiente, mas respeita seu estilo)
            BufferedReader br2 = new BufferedReader(new FileReader("/tmp/games.csv"));
            br2.readLine();
            String l;
            while ((l = br2.readLine()) != null) {
                String[] campos = new String[14];
                String aux = "";
                boolean aspas = false;
                int c = 0;
                for (int i = 0; i < l.length(); i++) {
                    char ch = l.charAt(i);
                    if (ch == '"') aspas = !aspas;
                    else if (ch == ',' && !aspas) {
                        campos[c++] = aux;
                        aux = "";
                    } else aux += ch;
                }
                campos[c] = aux;
                Games g = new Games();
                settar(g, campos);
                if (g.getId() == idBusca)
                    pesquisa.enfileirar(g);
            }
            entrada = sc.nextLine();
        }

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String acao = sc.next();

            if (acao.equals("I")) {
                int id = sc.nextInt();
                BufferedReader br3 = new BufferedReader(new FileReader("/tmp/games.csv"));
                br3.readLine();
                String l;
                while ((l = br3.readLine()) != null) {
                    String[] campos = new String[14];
                    String aux = "";
                    boolean aspas = false;
                    int c = 0;
                    for (int j = 0; j < l.length(); j++) {
                        char ch = l.charAt(j);
                        if (ch == '"') aspas = !aspas;
                        else if (ch == ',' && !aspas) {
                            campos[c++] = aux;
                            aux = "";
                        } else aux += ch;
                    }
                    campos[c] = aux;
                    Games g = new Games();
                    settar(g, campos);
                    if (g.getId() == id) {
                        pesquisa.enfileirar(g);
                    }
                }
            } else if (acao.equals("R")) {
                Games g = pesquisa.desenfileirar();
                System.out.println("(R) " + g.getNome());
            }
        }

        pesquisa.mostrar();
        sc.close();
        br.close();
    }
}
