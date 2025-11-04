import java.util.*;

class Contato {
    private String nome;
    private int telefone;
    private String email;
    private int cpf;

    public Contato(String nome, int telefone, String email, int cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getCpf() {
        return cpf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}

class Celula {
    Contato contato;
    Celula prox;

    public Celula() {
        contato = null;
        prox = null;
    }

    public Celula(Contato contato) {
        this.contato = contato;
        prox = null;
    }
}

class No {
    char letra;
    No esq;
    No dir;
    Celula primeiro;
    Celula ultimo;

    public No(char letra) {
        this.letra = letra;
        esq = null;
        dir = null;
        primeiro = ultimo = new Celula();
    }
}

class Agenda {
    private No raiz;

    public Agenda() {
        raiz = null;
    }

    private No pesquisarNo(No i, char letra) {
        No no = null;

        if (i == null)
            no = null;
        else if (i.letra == letra)
            no = i;
        else if (i.letra > letra)
            no = pesquisarNo(i.esq, letra);
        else if (i.letra < letra)
            no = pesquisarNo(i.dir, letra);

        return no;
    }

    private No inserirNo(No i, char letra) {
        if (i == null)
            i = new No(letra);
        else if (i.letra > letra)
            i.esq = inserirNo(i.esq, letra);
        else if (i.letra < letra)
            i.dir = inserirNo(i.dir, letra);

        return i;
    }

    private void inserirContato(No no, Contato contato) {
        Celula temp = new Celula(contato);
        no.ultimo.prox = temp;
        no.ultimo = temp;
        temp = null;
    }

    public void inserirContato(Contato contato) {
        No no = pesquisarNo(raiz, contato.getNome().charAt(0));

        if (no == null) {
            raiz = inserirNo(raiz, contato.getNome().charAt(0));
            no = pesquisarNo(raiz, contato.getNome().charAt(0));
        }

        inserirContato(no, contato);

    }

    private Celula pesquisarContato(String nome, No no) {
        Celula i = no.primeiro.prox;
        boolean existe = false;

        while (i != null && !existe) {
            if (i.contato.getNome().equals(nome))
                existe = true;
            else
                i = i.prox;
        }

        return i;
    }

    private void removerContato(Celula temp, String nome, No no) {
        if (temp == null)
            return;

        if (no.primeiro.prox == temp) {
            no.primeiro.prox = temp.prox;
            if (temp == no.ultimo)
                no.ultimo = no.primeiro;
        } else {
            Celula i = no.primeiro;
            while (i.prox != temp)
                i = i.prox;
            i.prox = temp.prox;

            if (temp == no.ultimo)
                no.ultimo = i;
        }

        temp.prox = null;
        temp = null;
    }

    public void removerPeloNome(String nome) {
        No no = pesquisarNo(raiz, nome.charAt(0));

        if (no != null) {
            Celula temp = pesquisarContato(nome, no);
            if (temp != null) {
                removerContato(temp, nome, no);
            }
        }
    }

    private void printarContatos(No i) {
        Celula j = i.primeiro.prox;
        for (; j != null; j = j.prox) {
            if (j != null) {
                System.out.println(" NOME: " + j.contato.getNome() + " ||  Email: " + j.contato.getEmail()
                        + " ||  CPF: " + j.contato.getCpf() + " || TELEFONE " + j.contato.getTelefone());
                System.out.println();
            }
        }
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            printarContatos(i);
            caminharCentral(i.dir);
        }
    }

    public void mostrar() {
        caminharCentral(raiz);
    }

    public boolean pesquisarContato(String nome) {
        boolean resp = false;
        No no = pesquisarNo(raiz, nome.charAt(0));

        if (no != null) {
            Celula celula = pesquisarContato(nome, no);
            if (celula != null)
                resp = true;
        }

        return resp;
    }

    private boolean pesquisarCpfNo(int cpf, No i) {
        boolean resp = false;

        Celula temp = i.primeiro.prox;

        while (temp != null) {
            if (temp.contato.getCpf() == cpf) {
                resp = true;
            }

            temp = temp.prox;
        }

        return resp;
    }

    private boolean pesquisarCpf(No i, int cpf) {
        if (i == null)
            return false;

        if (pesquisarCpfNo(cpf, i))
            return true;

        return pesquisarCpf(i.esq, cpf) || pesquisarCpf(i.dir, cpf);
    }

    public boolean pesquisarCpf(int cpf) {
        return pesquisarCpf(raiz, cpf);
    }
}

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        Contato contato = new Contato("Jorge", 987654321, "emailfalso123@gmail.com", 123456789);

        agenda.inserirContato(contato);

        contato = new Contato("Mateus", 987654321, "emailfalso123@gmail.com", 123456789);

        agenda.inserirContato(contato);

        contato = new Contato("Pedro", 987654321, "emailfalso123@gmail.com", 123456789);

        agenda.inserirContato(contato);

        contato = new Contato("Caio", 987654321, "emailfalso123@gmail.com", 123456789);

        agenda.inserirContato(contato);

        contato = new Contato("Joao", 987654321, "emailfalso123@gmail.com", 123456789);

        agenda.inserirContato(contato);

        agenda.removerPeloNome("Jorge");

        agenda.mostrar();

        int cpf = 123456789;

        System.out.println(agenda.pesquisarContato("Joao") ? " EXISTE" : " NAO EXISTE");
        System.out.println(agenda.pesquisarCpf(cpf) ? " EXISTE" : " NAO EXISTE");

    }
}
