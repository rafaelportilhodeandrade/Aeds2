#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_STR 1000
#define MAX_LISTA 50

typedef struct {
    char conteudo[MAX_STR];
} Texto;

typedef struct {
    int id;
    Texto nome;
    Texto lancamento;
    int players;
    float preco;
    Texto idiomas[MAX_LISTA];
    int qtdIdiomas;
    int notaCritica;
    float notaUsuarios;
    int conquistas;
    Texto empresasPub[MAX_LISTA];
    int qtdEmpresasPub;
    Texto empresasDev[MAX_LISTA];
    int qtdEmpresasDev;
    Texto categorias[MAX_LISTA];
    int qtdCategorias;
    Texto generos[MAX_LISTA];
    int qtdGeneros;
    Texto tags[MAX_LISTA];
    int qtdTags;
} Games;

typedef struct No {
    Games elemento;
    struct No *esq;
    struct No *dir;
    int nivel;
} No;

typedef struct {
    No *raiz;
    int comparacoes;
} ArvoreAVL;

// Funções auxiliares
int my_strcmp(char *a, char *b) {
    return strcmp(a, b);
}

long now() {
    struct timespec ts;
    clock_gettime(CLOCK_MONOTONIC, &ts);
    return ts.tv_sec * 1000000000L + ts.tv_nsec;
}

// Funções para formatação
int formatarLista(Texto entrada, Texto saida[], bool removerAspas) {
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

    if(strcmp(mes, "Jan") == 0) strcpy(numMes, "01");
    else if(strcmp(mes, "Feb") == 0) strcpy(numMes, "02");
    else if(strcmp(mes, "Mar") == 0) strcpy(numMes, "03");
    else if(strcmp(mes, "Apr") == 0) strcpy(numMes, "04");
    else if(strcmp(mes, "May") == 0) strcpy(numMes, "05");
    else if(strcmp(mes, "Jun") == 0) strcpy(numMes, "06");
    else if(strcmp(mes, "Jul") == 0) strcpy(numMes, "07");
    else if(strcmp(mes, "Aug") == 0) strcpy(numMes, "08");
    else if(strcmp(mes, "Sep") == 0) strcpy(numMes, "09");
    else if(strcmp(mes, "Oct") == 0) strcpy(numMes, "10");
    else if(strcmp(mes, "Nov") == 0) strcpy(numMes, "11");
    else if(strcmp(mes, "Dec") == 0) strcpy(numMes, "12");
    else strcpy(numMes, "01");

    strcpy(saida->conteudo, dia);
    strcat(saida->conteudo, "/");
    strcat(saida->conteudo, numMes);
    strcat(saida->conteudo, "/");
    strcat(saida->conteudo, ano);
}

// Setters
void setId(Games *g, Texto valor) {
    g->id = atoi(valor.conteudo);
}

void setNome(Games *g, Texto valor) {
    strcpy(g->nome.conteudo, valor.conteudo);
}

void setLancamento(Games *g, Texto valor) {
    converterData(valor, &g->lancamento);
}

void setPlayers(Games *g, Texto valor) {
    Texto aux;
    aux.conteudo[0] = '\0';
    int p = 0;
    for(int i = 0; i < strlen(valor.conteudo); i++) {
        if(valor.conteudo[i] >= '0' && valor.conteudo[i] <= '9') {
            aux.conteudo[p++] = valor.conteudo[i];
        }
    }
    aux.conteudo[p] = '\0';
    g->players = atoi(aux.conteudo);
}

void setPreco(Games *g, Texto valor) {
    if(strcmp(valor.conteudo, "Free to Play") == 0) {
        g->preco = 0.0f;
    } else {
        g->preco = atof(valor.conteudo);
    }
}

void setIdiomas(Games *g, Texto valor) {
    g->qtdIdiomas = formatarLista(valor, g->idiomas, true);
}

void setNotaCritica(Games *g, Texto valor) {
    g->notaCritica = strlen(valor.conteudo) == 0 ? -1 : atoi(valor.conteudo);
}

void setNotaUsuarios(Games *g, Texto valor) {
    if(strlen(valor.conteudo) == 0 || strcmp(valor.conteudo, "tbd") == 0) {
        g->notaUsuarios = -1.0f;
    } else {
        g->notaUsuarios = atof(valor.conteudo);
    }
}

void setConquistas(Games *g, Texto valor) {
    g->conquistas = strlen(valor.conteudo) == 0 ? 0 : atoi(valor.conteudo);
}

void setEmpresasPub(Games *g, Texto valor) {
    g->qtdEmpresasPub = formatarLista(valor, g->empresasPub, false);
}

void setEmpresasDev(Games *g, Texto valor) {
    g->qtdEmpresasDev = formatarLista(valor, g->empresasDev, false);
}

void setCategorias(Games *g, Texto valor) {
    g->qtdCategorias = formatarLista(valor, g->categorias, false);
}

void setGeneros(Games *g, Texto valor) {
    g->qtdGeneros = formatarLista(valor, g->generos, false);
}

void setTags(Games *g, Texto valor) {
    g->qtdTags = formatarLista(valor, g->tags, false);
}

void settar(Games *g, Texto campos[]) {
    setId(g, campos[0]);
    setNome(g, campos[1]);
    setLancamento(g, campos[2]);
    setPlayers(g, campos[3]);
    setPreco(g, campos[4]);
    setIdiomas(g, campos[5]);
    setNotaCritica(g, campos[6]);
    setNotaUsuarios(g, campos[7]);
    setConquistas(g, campos[8]);
    setEmpresasPub(g, campos[9]);
    setEmpresasDev(g, campos[10]);
    setCategorias(g, campos[11]);
    setGeneros(g, campos[12]);
    setTags(g, campos[13]);
}

// Funções de impressão
void imprimirArray(Texto array[], int n) {
    printf("[");
    for(int i = 0; i < n; i++) {
        int start = 0;
        while(array[i].conteudo[start] == ' ') start++;
        printf("%s", array[i].conteudo + start);
        if(i < n - 1) printf(", ");
    }
    printf("]");
}

void mostrarGames(Games *g) {
    printf("=> %d ## %s ## %s ## %d ## ", g->id, g->nome.conteudo, g->lancamento.conteudo, g->players);
    
    if(g->preco == 0.0) printf("0.0 ## ");
    else printf("%g ## ", g->preco);
    
    imprimirArray(g->idiomas, g->qtdIdiomas);
    printf(" ## %d ## %.1f ## %d ## ", g->notaCritica, g->notaUsuarios, g->conquistas);
    imprimirArray(g->empresasPub, g->qtdEmpresasPub); printf(" ## ");
    imprimirArray(g->empresasDev, g->qtdEmpresasDev); printf(" ## ");
    imprimirArray(g->categorias, g->qtdCategorias); printf(" ## ");
    imprimirArray(g->generos, g->qtdGeneros); printf(" ## ");
    imprimirArray(g->tags, g->qtdTags); printf(" ##\n");
}

// Funções da Árvore AVL
No* criarNo(Games game) {
    No* novo = (No*)malloc(sizeof(No));
    novo->elemento = game;
    novo->esq = NULL;
    novo->dir = NULL;
    novo->nivel = 1;
    return novo;
}

ArvoreAVL* criarArvore() {
    ArvoreAVL* arvore = (ArvoreAVL*)malloc(sizeof(ArvoreAVL));
    arvore->raiz = NULL;
    arvore->comparacoes = 0;
    return arvore;
}

int getNivel(No *no) {
    return (no == NULL) ? 0 : no->nivel;
}

void setNivel(No *no) {
    int nivelEsq = getNivel(no->esq);
    int nivelDir = getNivel(no->dir);
    no->nivel = 1 + (nivelEsq > nivelDir ? nivelEsq : nivelDir);
}

int getFator(No *no) {
    return getNivel(no->dir) - getNivel(no->esq);
}

No* rotacionarDir(No *no) {
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;
    
    noEsq->dir = no;
    no->esq = noEsqDir;
    
    setNivel(no);
    setNivel(noEsq);
    
    return noEsq;
}

No* rotacionarEsq(No *no) {
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;
    
    noDir->esq = no;
    no->dir = noDirEsq;
    
    setNivel(no);
    setNivel(noDir);
    
    return noDir;
}

No* balancear(No *no) {
    if(no != NULL) {
        setNivel(no);
        int fator = getFator(no);
        
        // Caso de desbalanceamento à direita
        if(fator == 2) {
            int fatorDir = getFator(no->dir);
            if(fatorDir == -1) {
                no->dir = rotacionarDir(no->dir);
            }
            return rotacionarEsq(no);
        }
        // Caso de desbalanceamento à esquerda
        else if(fator == -2) {
            int fatorEsq = getFator(no->esq);
            if(fatorEsq == 1) {
                no->esq = rotacionarEsq(no->esq);
            }
            return rotacionarDir(no);
        }
    }
    return no;
}

No* inserirAVL(Games game, No *i, ArvoreAVL *arvore) {
    if(i == NULL) {
        return criarNo(game);
    }
    
    int cmp = strcmp(game.nome.conteudo, i->elemento.nome.conteudo);
    
    if(cmp < 0) {
        i->esq = inserirAVL(game, i->esq, arvore);
    } else if(cmp > 0) {
        i->dir = inserirAVL(game, i->dir, arvore);
    } else {
        // Nomes iguais - insere à direita
        i->dir = inserirAVL(game, i->dir, arvore);
    }
    
    return balancear(i);
}

void inserir(Games game, ArvoreAVL *arvore) {
    arvore->raiz = inserirAVL(game, arvore->raiz, arvore);
}

bool pesquisarAVL(char *nome, No *i, ArvoreAVL *arvore) {
    bool resp = false;
    while(i != NULL) {
        arvore->comparacoes++;
        int cmp = strcmp(nome, i->elemento.nome.conteudo);
        
        if(cmp < 0) {
            printf(" esq");
            i = i->esq;
        } else if(cmp > 0) {
            printf(" dir");
            i = i->dir;
        } else {
            resp = true;
            i = NULL;
        }
    }
    return resp;
}

void pesquisar(char *nome, ArvoreAVL *arvore) {
    printf("%s: raiz", nome);
    if(pesquisarAVL(nome, arvore->raiz, arvore)) {
        printf(" SIM\n");
    } else {
        printf(" NAO\n");
    }
}

// Busca por ID (recursiva)
bool buscarPorId(No *i, int idBusca, ArvoreAVL *arvorePesquisa) {
    if(i == NULL) return false;
    
    if(buscarPorId(i->esq, idBusca, arvorePesquisa)) return true;
    
    if(i->elemento.id == idBusca) {
        inserir(i->elemento, arvorePesquisa);
        return true;
    }
    
    if(buscarPorId(i->dir, idBusca, arvorePesquisa)) return true;
    
    return false;
}

int main() {
    FILE *arquivo = fopen("/tmp/games.csv", "r");
    if(!arquivo) {
        printf("Erro ao abrir arquivo\n");
        return 1;
    }

    Games *listaGames = malloc(5000 * sizeof(Games));
    Texto linha, cabecalho;
    int totalGames = 0;

    fscanf(arquivo, " %[^\n]", cabecalho.conteudo);

    // Parse do CSV
    while(fscanf(arquivo, " %[^\n]", linha.conteudo) != EOF) {
        linha.conteudo[strcspn(linha.conteudo, "\r\n")] = '\0';
        Texto campos[14];
        Texto buffer;
        buffer.conteudo[0] = '\0';
        int campoAtual = 0, bufPos = 0;
        bool aspas = false;

        for(int i = 0; i < strlen(linha.conteudo); i++) {
            char c = linha.conteudo[i];
            if(c == '"') aspas = !aspas;
            else if(c == ',' && !aspas) {
                buffer.conteudo[bufPos] = '\0';
                strcpy(campos[campoAtual++].conteudo, buffer.conteudo);
                bufPos = 0;
            } else {
                buffer.conteudo[bufPos++] = c;
            }
        }
        buffer.conteudo[bufPos] = '\0';
        strcpy(campos[campoAtual].conteudo, buffer.conteudo);

        settar(&listaGames[totalGames], campos);
        totalGames++;
    }
    fclose(arquivo);

    // Árvore principal
    ArvoreAVL *arvorePrincipal = criarArvore();
    for(int i = 0; i < totalGames; i++) {
        inserir(listaGames[i], arvorePrincipal);
    }

    // Árvore para pesquisa
    ArvoreAVL *arvorePesquisa = criarArvore();
    Texto entradaId;
    
    scanf("%s", entradaId.conteudo);
    while(strcmp(entradaId.conteudo, "FIM") != 0) {
        int idBusca = atoi(entradaId.conteudo);
        buscarPorId(arvorePrincipal->raiz, idBusca, arvorePesquisa);
        scanf("%s", entradaId.conteudo);
    }

    // Pesquisa por nomes
    long inicio = now();
    Texto entradaNome;
    
    scanf(" %[^\n]", entradaNome.conteudo);
    while(strcmp(entradaNome.conteudo, "FIM") != 0) {
        pesquisar(entradaNome.conteudo, arvorePesquisa);
        scanf(" %[^\n]", entradaNome.conteudo);
    }
    
    long fim = now();
    double tempoExecucao = (fim - inicio) / 1000000.0;

    // Gera arquivo de log
    FILE *log = fopen("890258_avl.txt", "w");
    if(log) {
        fprintf(log, "890258\t%.2fms\t%dcomparacoes\n", tempoExecucao, arvorePesquisa->comparacoes);
        fclose(log);
    }

    free(listaGames);
    return 0;
}