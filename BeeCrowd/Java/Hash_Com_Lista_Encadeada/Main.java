import java.util.*;

class Carro {
    String placa, modelo, tipo, chassi;

    public Carro(String placa, String modelo, String tipo, String chassi) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.chassi = chassi;
    }
}

class Celula {
    Carro carro;
    Celula prox;

    public Celula(Carro carro) {
        this.carro = carro;
        this.prox = null;
    }
}

class Hash {
    Celula primeiro;
    Celula ultimo;

    public Hash() {
        primeiro = ultimo = new Celula(null); 
    }

    private static int getPlacaAsc(String placa) {
        int aux = 0;
        for(int i = 0; i < placa.length(); i++) {
            aux += placa.charAt(i);
        }
        return aux;
    }

    private static int getHash(String placa, int tam) {
        return getPlacaAsc(placa) % tam;
    }

    public static void inserir(Carro carro, Hash[] tabela, int tam) {
        int pos = getHash(carro.placa, tam);

        Celula nova = new Celula(carro);
        tabela[pos].ultimo.prox = nova;
        tabela[pos].ultimo = nova;
    }

    public static void pesquisar(Carro carro, Hash[] hash, int tam)
    {
        int pos = getHash(carro.placa, tam);

        boolean existe = false;

        for(Celula i = hash[pos].primeiro.prox; i != null; i = i.prox)
        {
            if(i != null && carro.placa.equals(i.carro.placa))
            {
                existe = true;
            }
        }

        if(existe)
        {
            System.out.println(carro.placa + " " +  carro.modelo +  " " + carro.tipo +  " " + carro.chassi);
        }
        else{
            System.out.println("VEICULO_NAO_CADASTRADO");
        }
    }
}

public class Main {

    public static Carro ler(String linha) {
        String[] partes = linha.split(",");
        //System.out.println(partes[0] + " " +  partes[1] +  " " + partes[2] +  " " + partes[3]);
        return new Carro(partes[0], partes[1], partes[2], partes[3]);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int tamanhoTabela = sc.nextInt();
        sc.nextLine(); 

        Hash[] tabela = new Hash[tamanhoTabela];

        for(int i = 0; i < tamanhoTabela; i++) {
            tabela[i] = new Hash();
        }

        String linha = sc.nextLine();

        while(!linha.equals("FIM")) {
            Carro carro = ler(linha);
            Hash.inserir(carro, tabela, tamanhoTabela);
            linha = sc.nextLine();
        }

        linha = sc.nextLine();

        while(!linha.equals("FIM_CONSULTA"))
        {
            Carro carro = ler(linha);
            Hash.pesquisar(carro, tabela, tamanhoTabela);

            linha = sc.nextLine();
        }

        sc.close();
    }
}
