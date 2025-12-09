#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_JOGOS 4000
#define MAX_STR 1000
#define MAX_LISTA 50

typedef struct {
    char conteudo[MAX_STR];
} Texto;

typedef struct {
    int id;
    Texto titulo;
    Texto lancamento;
    int numJogadores;
    float valor;
    Texto linguas[MAX_LISTA];
    int qtdLinguas;
    int notaCritica;
    float notaUsuario;
    int trofeus;
    Texto publisher[MAX_LISTA];
    int qtdPublisher;
    Texto studio[MAX_LISTA];
    int qtdStudio;
    Texto categorias[MAX_LISTA];
    int qtdCategorias;
    Texto generos[MAX_LISTA];
    int qtdGeneros;
    Texto tags[MAX_LISTA];
    int qtdTags;
} Jogo;

// ---- Funções de parsing/formatting ----

int dividirLista(Texto entrada, Texto saida[], bool removerAspas) {
    Texto buffer;
    int idx = 0, bufPos = 0;
    for(int i = 0; i < strlen(entrada.conteudo); i++) {
        char c = entrada.conteudo[i];
        if(c == ',') {
            buffer.conteudo[bufPos] = '\0';
            int start = 0;
            while(buffer.conteudo[start] == ' ') start++;
            if(strlen(buffer.conteudo + start) > 0) {
                strcpy(saida[idx++].conteudo, buffer.conteudo + start);
            }
            bufPos = 0;
        } else {
            if(!(c == '[' || c == ']' || (removerAspas && c == '\''))) {
                buffer.conteudo[bufPos++] = c;
            }
        }
    }
    buffer.conteudo[bufPos] = '\0';
    if(strlen(buffer.conteudo) > 0) {
        int start = 0;
        while(buffer.conteudo[start] == ' ') start++;
        strcpy(saida[idx++].conteudo, buffer.conteudo + start);
    }
    return idx;
}

void converterData(Texto entrada, Texto *saida) {
    if(strlen(entrada.conteudo) < 8) {
        strcpy(saida->conteudo, "01/01/0000");
        return;
    }
    char mes[4], dia[3], ano[5], numMes[3];
    strncpy(mes, entrada.conteudo, 3);
    mes[3] = '\0';

    if(entrada.conteudo[5] == ',') {
        dia[0] = '0';
        dia[1] = entrada.conteudo[4];
        dia[2] = '\0';
        strcpy(ano, entrada.conteudo + 7);
    } else {
        dia[0] = entrada.conteudo[4];
        dia[1] = entrada.conteudo[5];
        dia[2] = '\0';
        strcpy(ano, entrada.conteudo + 8);
    }

    if(strcmp(mes,"Jan")==0) strcpy(numMes,"01");
    else if(strcmp(mes,"Feb")==0) strcpy(numMes,"02");
    else if(strcmp(mes,"Mar")==0) strcpy(numMes,"03");
    else if(strcmp(mes,"Apr")==0) strcpy(numMes,"04");
    else if(strcmp(mes,"May")==0) strcpy(numMes,"05");
    else if(strcmp(mes,"Jun")==0) strcpy(numMes,"06");
    else if(strcmp(mes,"Jul")==0) strcpy(numMes,"07");
    else if(strcmp(mes,"Aug")==0) strcpy(numMes,"08");
    else if(strcmp(mes,"Sep")==0) strcpy(numMes,"09");
    else if(strcmp(mes,"Oct")==0) strcpy(numMes,"10");
    else if(strcmp(mes,"Nov")==0) strcpy(numMes,"11");
    else if(strcmp(mes,"Dec")==0) strcpy(numMes,"12");
    else strcpy(numMes,"01");

    strcpy(saida->conteudo,dia);
    strcat(saida->conteudo,"/");
    strcat(saida->conteudo,numMes);
    strcat(saida->conteudo,"/");
    strcat(saida->conteudo,ano);
}

// Setters
void definirId(Jogo *j, Texto valor) { j->id = atoi(valor.conteudo); }
void definirTitulo(Jogo *j, Texto valor) { strcpy(j->titulo.conteudo, valor.conteudo); }
void definirLancamento(Jogo *j, Texto valor) { converterData(valor, &j->lancamento); }
void definirJogadores(Jogo *j, Texto valor) {
    Texto aux; aux.conteudo[0]='\0'; int p=0;
    for(int i=0;i<strlen(valor.conteudo);i++)
        if(valor.conteudo[i]>='0' && valor.conteudo[i]<='9') aux.conteudo[p++] = valor.conteudo[i];
    aux.conteudo[p]='\0';
    j->numJogadores = atoi(aux.conteudo);
}
void definirValor(Jogo *j, Texto valor) {
    if(strcmp(valor.conteudo,"Free to Play")==0 || strcmp(valor.conteudo,"0.0")==0) j->valor=0.0f;
    else j->valor=atof(valor.conteudo);
}
void definirLinguas(Jogo *j, Texto valor) { j->qtdLinguas = dividirLista(valor, j->linguas, true); }
void definirNotaCritica(Jogo *j, Texto valor) { j->notaCritica = strlen(valor.conteudo)==0?0:atoi(valor.conteudo); }
void definirNotaUsuario(Jogo *j, Texto valor) { 
    if(strlen(valor.conteudo)==0 || strcmp(valor.conteudo,"tbd")==0) j->notaUsuario=0.0f;
    else j->notaUsuario=atof(valor.conteudo); 
}
void definirTrofeus(Jogo *j, Texto valor) { j->trofeus = strlen(valor.conteudo)==0?0:atoi(valor.conteudo); }
void definirPublisher(Jogo *j, Texto valor) { j->qtdPublisher = dividirLista(valor,j->publisher,false); }
void definirStudio(Jogo *j, Texto valor) { j->qtdStudio = dividirLista(valor,j->studio,false); }
void definirCategorias(Jogo *j, Texto valor) { j->qtdCategorias = dividirLista(valor,j->categorias,false); }
void definirGeneros(Jogo *j, Texto valor) { j->qtdGeneros = dividirLista(valor,j->generos,false); }
void definirTags(Jogo *j, Texto valor) { j->qtdTags = dividirLista(valor,j->tags,false); }

void preencherJogo(Jogo *j, Texto campos[]) {
    definirId(j, campos[0]);
    definirTitulo(j, campos[1]);
    definirLancamento(j, campos[2]);
    definirJogadores(j, campos[3]);
    definirValor(j, campos[4]);
    definirLinguas(j, campos[5]);
    definirNotaCritica(j, campos[6]);
    definirNotaUsuario(j, campos[7]);
    definirTrofeus(j, campos[8]);
    definirPublisher(j, campos[9]);
    definirStudio(j, campos[10]);
    definirCategorias(j, campos[11]);
    definirGeneros(j, campos[12]);
    definirTags(j, campos[13]);
}

// Impressão
void imprimirLista(Texto array[], int n) {
    printf("[");
    for(int i=0;i<n;i++){
        int start=0;
        while(array[i].conteudo[start]==' ') start++;
        printf("%s", array[i].conteudo+start);
        if(i<n-1) printf(", ");
    }
    printf("]");
}

void mostrarJogo(Jogo *j) {
    printf("=> %d ## %s ## %s ## %d ## ", j->id, j->titulo.conteudo, j->lancamento.conteudo, j->numJogadores);
    if(j->valor==0.0) printf("0.0 ## "); else printf("%g ## ", j->valor);
    imprimirLista(j->linguas,j->qtdLinguas);
    printf(" ## %d ## %.1f ## %d ## ", j->notaCritica, j->notaUsuario, j->trofeus);
    imprimirLista(j->publisher,j->qtdPublisher); printf(" ## ");
    imprimirLista(j->studio,j->qtdStudio); printf(" ## ");
    imprimirLista(j->categorias,j->qtdCategorias); printf(" ## ");
    imprimirLista(j->generos,j->qtdGeneros); printf(" ## ");
    imprimirLista(j->tags,j->qtdTags); printf(" ##\n");
}

// ---- Implementação da LISTA flexível ----

typedef struct Celula {
    Jogo elemento;
    struct Celula *prox;
} Celula;

typedef struct {
    Celula *primeiro;
    Celula *ultimo;
} Lista;

Celula *criarCelula(Jogo game) {
    Celula *nova = (Celula*)malloc(sizeof(Celula));
    nova->elemento = game;
    nova->prox = NULL;
    return nova;
}

Lista *criarLista() {
    Lista *lista = (Lista*)malloc(sizeof(Lista));
    // Cria célula cabeça
    lista->primeiro = criarCelula((Jogo){0});
    lista->ultimo = lista->primeiro;
    return lista;
}

void inserirInicio(Lista *lista, Jogo game) {
    Celula *nova = criarCelula(game);
    nova->prox = lista->primeiro->prox;
    lista->primeiro->prox = nova;
    if (lista->ultimo == lista->primeiro) {
        lista->ultimo = nova;
    }
}

void inserirFim(Lista *lista, Jogo game) {
    lista->ultimo->prox = criarCelula(game);
    lista->ultimo = lista->ultimo->prox;
}

void inserirPosicao(Lista *lista, Jogo game, int pos) {
    int tam = 0;
    for(Celula *i = lista->primeiro->prox; i != NULL; i = i->prox) tam++;
    
    if (pos < 0 || pos > tam) {
        printf("Erro: Posição inválida\n");
        return;
    } else if (pos == 0) {
        inserirInicio(lista, game);
    } else if (pos == tam) {
        inserirFim(lista, game);
    } else {
        Celula *i = lista->primeiro;
        for (int j = 0; j < pos; j++, i = i->prox);
        Celula *nova = criarCelula(game);
        nova->prox = i->prox;
        i->prox = nova;
    }
}

Jogo removerInicio(Lista *lista) {
    Jogo vazio;
    vazio.id = -1;
    vazio.titulo.conteudo[0] = '\0';

    if (lista->primeiro == lista->ultimo) {
        printf("Erro: Lista vazia\n");
        return vazio;
    }
    
    Celula *tmp = lista->primeiro->prox;
    Jogo resp = tmp->elemento;
    lista->primeiro->prox = tmp->prox;
    if (tmp == lista->ultimo) {
        lista->ultimo = lista->primeiro;
    }
    tmp->prox = NULL;
    free(tmp);
    return resp;
}

Jogo removerFim(Lista *lista) {
    Jogo vazio;
    vazio.id = -1;
    vazio.titulo.conteudo[0] = '\0';

    if (lista->primeiro == lista->ultimo) {
        printf("Erro: Lista vazia\n");
        return vazio;
    }
    
    Celula *i;
    for (i = lista->primeiro; i->prox != lista->ultimo; i = i->prox);
    Jogo resp = lista->ultimo->elemento;
    Celula *tmp = lista->ultimo;
    lista->ultimo = i;
    lista->ultimo->prox = NULL;
    free(tmp);
    return resp;
}

Jogo removerPosicao(Lista *lista, int pos) {
    int tam = 0;
    for(Celula *i = lista->primeiro->prox; i != NULL; i = i->prox) tam++;
    
    if (pos < 0 || pos >= tam) {
        printf("Erro: Posição inválida\n");
        Jogo vazio;
        vazio.id = -1;
        vazio.titulo.conteudo[0] = '\0';
        return vazio;
    } else if (pos == 0) {
        return removerInicio(lista);
    } else if (pos == tam - 1) {
        return removerFim(lista);
    }
    
    Celula *i = lista->primeiro;
    for (int j = 0; j < pos; j++, i = i->prox);
    Celula *tmp = i->prox;
    Jogo resp = tmp->elemento;
    i->prox = tmp->prox;
    tmp->prox = NULL;
    free(tmp);
    return resp;
}

int tamanhoLista(Lista *lista) {
    int count = 0;
    for(Celula *i = lista->primeiro->prox; i != NULL; i = i->prox) count++;
    return count;
}

void mostrarLista(Lista *lista) {
    int idx = 0;
    for(Celula *i = lista->primeiro->prox; i != NULL; i = i->prox, idx++) {
        printf("[%d] ", idx);
        mostrarJogo(&i->elemento);
    }
}

void freeLista(Lista *lista) {
    Celula *i = lista->primeiro;
    while(i != NULL) {
        Celula *tmp = i->prox;
        free(i);
        i = tmp;
    }
    free(lista);
}

// ---- Main adaptado para usar lista flexível ----

int main() {
    FILE *arquivo = fopen("/tmp/games.csv","r");
    if(!arquivo){ printf("Erro ao abrir arquivo\n"); return 1; }

    // le arquivo e guarda todos os jogos em lista dinâmica (array alocado)
    Jogo *listaJogos = malloc(MAX_JOGOS * sizeof(Jogo));
    Texto linha, cabecalho;
    int total = 0;

    fscanf(arquivo," %[^\n]", cabecalho.conteudo);

    while(fscanf(arquivo," %[^\n]", linha.conteudo) != EOF) {
        linha.conteudo[strcspn(linha.conteudo,"\r\n")] = '\0';
        Texto campos[14];
        Texto buffer; buffer.conteudo[0]='\0';
        int campoAtual=0, bufPos=0;
        bool aspas=false;

        for(int i=0;i<strlen(linha.conteudo);i++){
            char c=linha.conteudo[i];
            if(c=='"') aspas = !aspas;
            else if(c==',' && !aspas) {
                buffer.conteudo[bufPos]='\0';
                strcpy(campos[campoAtual++].conteudo, buffer.conteudo);
                bufPos=0;
            } else buffer.conteudo[bufPos++] = c;
        }
        buffer.conteudo[bufPos]='\0';
        strcpy(campos[campoAtual].conteudo, buffer.conteudo);

        preencherJogo(&listaJogos[total], campos);
        total++;
    }

    fclose(arquivo);

    // Lista que armazenará os jogos buscados (flexível)
    Lista *pesquisa = criarLista();

    // Leitura dos IDs iniciais (até "FIM"), insere no fim o jogo correspondente
    Texto busca;
    scanf(" %s", busca.conteudo);
    while(strcmp(busca.conteudo,"FIM")!=0){
        int id = atoi(busca.conteudo);
        for(int i=0;i<total;i++){
            if(listaJogos[i].id==id){
                inserirFim(pesquisa, listaJogos[i]);
            }
        }
        scanf(" %s", busca.conteudo);
    }

    int n;
    if(scanf("%d", &n) != 1) n = 0;

    for(int i=0;i<n;i++){
        char acao[10];
        if(scanf(" %s", acao) != 1) i = n;

        if(strcmp(acao, "II") == 0) {
            int id;
            scanf("%d", &id);
            // procura jogo na lista e insere no início
            for(int j=0;j<total;j++){
                if(listaJogos[j].id == id){
                    inserirInicio(pesquisa, listaJogos[j]);
                }
            }
        }
        else if(strcmp(acao, "IF") == 0) {
            int id;
            scanf("%d", &id);
            // procura jogo na lista e insere no fim
            for(int j=0;j<total;j++){
                if(listaJogos[j].id == id){
                    inserirFim(pesquisa, listaJogos[j]);
                }
            }
        }
        else if(strcmp(acao, "I*") == 0) {
            int pos, id;
            scanf("%d %d", &pos, &id);
            // procura jogo na lista e insere na posição
            for(int j=0;j<total;j++){
                if(listaJogos[j].id == id){
                    inserirPosicao(pesquisa, listaJogos[j], pos);
                }
            }
        }
        else if(strcmp(acao, "RI") == 0) {
            Jogo rem = removerInicio(pesquisa);
            if(rem.id != -1) {
                printf("(R) %s\n", rem.titulo.conteudo);
            }
        }
        else if(strcmp(acao, "RF") == 0) {
            Jogo rem = removerFim(pesquisa);
            if(rem.id != -1) {
                printf("(R) %s\n", rem.titulo.conteudo);
            }
        }
        else if(strcmp(acao, "R*") == 0) {
            int pos;
            scanf("%d", &pos);
            Jogo rem = removerPosicao(pesquisa, pos);
            if(rem.id != -1) {
                printf("(R) %s\n", rem.titulo.conteudo);
            }
        }
        else {
            // ação desconhecida: ignora linha
        }
    }

    // Mostrar lista
    mostrarLista(pesquisa);

    // libera memórias
    free(listaJogos);
    freeLista(pesquisa);
    return 0;
}