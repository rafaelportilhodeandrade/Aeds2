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
    int dia;
    int mes;
    int ano;
} Data;

typedef struct {
    int id;
    Texto titulo;
    Data lancamento;
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

// ------------------- Funções auxiliares -------------------

int dividirLista(Texto entrada, Texto saida[], bool removerAspas) {
    Texto buffer;
    int idx = 0, bufPos = 0;
    for (int i = 0; i < strlen(entrada.conteudo); i++) {
        char c = entrada.conteudo[i];
        if (c == ',') {
            buffer.conteudo[bufPos] = '\0';
            int start = 0;
            while (buffer.conteudo[start] == ' ') start++;
            if (strlen(buffer.conteudo + start) > 0) {
                strcpy(saida[idx++].conteudo, buffer.conteudo + start);
            }
            bufPos = 0;
        } else {
            if (!(c == '[' || c == ']' || (removerAspas && c == '\''))) {
                buffer.conteudo[bufPos++] = c;
            }
        }
    }
    buffer.conteudo[bufPos] = '\0';
    if (strlen(buffer.conteudo) > 0) {
        int start = 0;
        while (buffer.conteudo[start] == ' ') start++;
        strcpy(saida[idx++].conteudo, buffer.conteudo + start);
    }
    return idx;
}

// Converte "Oct 18, 2018" -> struct Data
Data converterData(Texto entrada) {
    Data d = {1, 1, 0};
    if (strlen(entrada.conteudo) < 8) return d;

    char mes[4], dia[3], ano[5];
    strncpy(mes, entrada.conteudo, 3);
    mes[3] = '\0';

    if (entrada.conteudo[5] == ',') {
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

    int m = 1;
    if (strcmp(mes, "Jan") == 0) m = 1;
    else if (strcmp(mes, "Feb") == 0) m = 2;
    else if (strcmp(mes, "Mar") == 0) m = 3;
    else if (strcmp(mes, "Apr") == 0) m = 4;
    else if (strcmp(mes, "May") == 0) m = 5;
    else if (strcmp(mes, "Jun") == 0) m = 6;
    else if (strcmp(mes, "Jul") == 0) m = 7;
    else if (strcmp(mes, "Aug") == 0) m = 8;
    else if (strcmp(mes, "Sep") == 0) m = 9;
    else if (strcmp(mes, "Oct") == 0) m = 10;
    else if (strcmp(mes, "Nov") == 0) m = 11;
    else if (strcmp(mes, "Dec") == 0) m = 12;

    d.dia = atoi(dia);
    d.mes = m;
    d.ano = atoi(ano);
    return d;
}

// ------------------- Setters -------------------

void definirId(Jogo *j, Texto valor) { j->id = atoi(valor.conteudo); }
void definirTitulo(Jogo *j, Texto valor) { strcpy(j->titulo.conteudo, valor.conteudo); }
void definirLancamento(Jogo *j, Texto valor) { j->lancamento = converterData(valor); }
void definirJogadores(Jogo *j, Texto valor) {
    Texto aux; aux.conteudo[0]='\0'; int p=0;
    for(int i=0;i<strlen(valor.conteudo);i++)
        if(valor.conteudo[i]>='0' && valor.conteudo[i]<='9')
            aux.conteudo[p++] = valor.conteudo[i];
    aux.conteudo[p]='\0';
    j->numJogadores = atoi(aux.conteudo);
}
void definirValor(Jogo *j, Texto valor) {
    if(strcmp(valor.conteudo,"Free to Play")==0 || strcmp(valor.conteudo,"0.0")==0)
        j->valor=0.0f;
    else j->valor=atof(valor.conteudo);
}
void definirLinguas(Jogo *j, Texto valor) { j->qtdLinguas = dividirLista(valor, j->linguas, true); }
void definirNotaCritica(Jogo *j, Texto valor) { j->notaCritica = strlen(valor.conteudo)==0?0:atoi(valor.conteudo); }
void definirNotaUsuario(Jogo *j, Texto valor) {
    if(strlen(valor.conteudo)==0 || strcmp(valor.conteudo,"tbd")==0)
        j->notaUsuario=0.0f;
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

// ------------------- Impressão -------------------

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

void imprimirData(Data d) {
    printf("%02d/%02d/%04d", d.dia, d.mes, d.ano);
}

void mostrarJogo(Jogo *j) {
    printf("=> %d ## %s ## ", j->id, j->titulo.conteudo);
    imprimirData(j->lancamento);
    printf(" ## %d ## ", j->numJogadores);
    if(j->valor==0.0) printf("0.0 ## "); else printf("%.2f ## ", j->valor);
    imprimirLista(j->linguas, j->qtdLinguas);
    printf(" ## %d ## %.1f ## %d ## ", j->notaCritica, j->notaUsuario, j->trofeus);
    imprimirLista(j->publisher, j->qtdPublisher); printf(" ## ");
    imprimirLista(j->studio, j->qtdStudio); printf(" ## ");
    imprimirLista(j->categorias, j->qtdCategorias); printf(" ## ");
    imprimirLista(j->generos, j->qtdGeneros); printf(" ## ");
    imprimirLista(j->tags, j->qtdTags);
    printf(" ##\n");
}

// ------------------- Ordenação -------------------

int compararData(Data a, Data b){ if(a.ano!=b.ano) return a.ano-b.ano; if(a.mes!=b.mes) return a.mes-b.mes; return a.dia-b.dia;}
int compararJogos(Jogo a, Jogo b){ int cmp=compararData(a.lancamento,b.lancamento); if(cmp!=0) return cmp; return a.id-b.id;}
void swapCont(Jogo *a,Jogo *b,long *mov){ Jogo t=*a; *a=*b; *b=t; (*mov)++;}
int particao(Jogo arr[],int low,int high,long *comp,long *mov){
    Jogo pivot=arr[high]; int i=low-1;
    for(int j=low;j<high;j++){
        (*comp)++;
        if(compararJogos(arr[j],pivot)<=0){ i++; swapCont(&arr[i],&arr[j],mov);}
    }
    swapCont(&arr[i+1],&arr[high],mov);
    return i+1;
}
void quickSortCont(Jogo arr[],int low,int high,long *comp,long *mov){
    if(low<high){
        int pi=particao(arr,low,high,comp,mov);
        quickSortCont(arr,low,pi-1,comp,mov);
        quickSortCont(arr,pi+1,high,comp,mov);
    }
}

// ---------- Main ----------
int main(){
    FILE *arquivo=fopen("/tmp/games.csv","r");
    if(!arquivo){ printf("Erro ao abrir arquivo\n"); return 1; }

    Jogo *listaJogos=malloc(MAX_JOGOS*sizeof(Jogo));
    Jogo *listaTemp=malloc(100*sizeof(Jogo));
    Texto linha,cabecalho; int total=0,cont=0;

    fscanf(arquivo," %[^\n]",cabecalho.conteudo);
    while(fscanf(arquivo," %[^\n]",linha.conteudo)!=EOF){
        linha.conteudo[strcspn(linha.conteudo,"\r\n")]='\0';
        Texto campos[14]; Texto buffer; buffer.conteudo[0]='\0'; int campoAtual=0,bufPos=0; bool aspas=false;
        for(int i=0;i<strlen(linha.conteudo);i++){
            char c=linha.conteudo[i];
            if(c=='"') aspas=!aspas;
            else if(c==',' && !aspas){ buffer.conteudo[bufPos]='\0'; strcpy(campos[campoAtual++].conteudo,buffer.conteudo); bufPos=0;}
            else buffer.conteudo[bufPos++]=c;
        }
        buffer.conteudo[bufPos]='\0'; strcpy(campos[campoAtual].conteudo,buffer.conteudo);
        preencherJogo(&listaJogos[total],campos); total++;
    }
    fclose(arquivo);

    Texto busca; scanf("%s",busca.conteudo);
    while(strcmp(busca.conteudo,"FIM")!=0){
        int id=atoi(busca.conteudo);
        for(int i=0;i<total;i++){ if(listaJogos[i].id==id){ listaTemp[cont++]=listaJogos[i]; break; } }
        scanf("%s",busca.conteudo);
    }

    clock_t inicio=clock();
    long comparacoes=0,movimentacoes=0;
    quickSortCont(listaTemp,0,cont-1,&comparacoes,&movimentacoes);
    clock_t fim=clock();
    double tempo=((double)(fim-inicio))/CLOCKS_PER_SEC*1000.0;

    for(int i=0;i<cont;i++) mostrarJogo(&listaTemp[i]);

    FILE *out=fopen("890258_binaria.txt","w");
    if(out){
        fprintf(out,"890258\n");
        fprintf(out,"Tempo de execução: %.0f ms\n",tempo);
        fprintf(out,"Comparacoes: %ld\n",comparacoes);
        fprintf(out,"Movimentacoes: %ld\n",movimentacoes);
        fclose(out);
    }

    free(listaJogos); free(listaTemp);
    return 0;
}