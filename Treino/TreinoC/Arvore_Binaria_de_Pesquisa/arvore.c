#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

typedef struct No
{
    int elemento;
    struct No* esq;
    struct No* dir;
}No;

typedef struct Arvore
{
    No* raiz;
}Arvore;

No* newNo(int elemento)
{
    No* no = (No*) malloc (sizeof (No));
    no->elemento = elemento;
    no->esq = NULL;
    no->dir = NULL;

    return no;
}

Arvore* newArvore()
{
    Arvore* arvore = (Arvore*) malloc (sizeof(Arvore));
    arvore->raiz = NULL;
    return arvore;
}

void freeNo(No* i)
{
    if(i != NULL)
    {
        freeNo(i->esq);
        freeNo(i->dir);
        free(i);
    }
}

void freeArvore(Arvore* arvore)
{
    freeNo(arvore->raiz);
    free(arvore);
}

No* inserirNo(int elemento, No* i)
{
    if(i == NULL) i = newNo(elemento);
    else if(i->elemento > elemento) i->esq = inserirNo(elemento, i->esq);
    else if(i->elemento <  elemento) i->dir = inserirNo(elemento, i->dir);

    return i;
}

void inserir(Arvore* arvore, int elemento)
{
    arvore->raiz = inserirNo(elemento, arvore->raiz);
}

bool pesquisarNo(int elemento, No* i)
{
    bool resp;

    if(i == NULL) resp = false;
    else if(i->elemento == elemento) resp = true;
    else if(i->elemento > elemento) resp = pesquisarNo(elemento, i->esq);
    else if(i->elemento < elemento) resp = pesquisarNo(elemento, i->dir);

    return resp;
}

bool pesquisar(Arvore* arvore, int elemento)
{
    return pesquisarNo(elemento, arvore->raiz);
}

No* maiorEsq(No* i, No* j)
{
    if(j->dir == NULL) 
    {
        i->elemento = j->elemento; 
        j = j->esq;
    }
    else j->dir = maiorEsq(i, j->dir);

    return j;
} 

No* removerNo(int elemento, No* i)
{
    if(i == NULL) return 0;
    else if(i->elemento > elemento) i->esq = removerNo(elemento, i->esq);
    else if(i->elemento <  elemento) i->dir = removerNo(elemento, i->dir);
    else if(i->dir == NULL) i = i->esq;
    else if(i->esq == NULL) i = i->dir;
    else i->esq = maiorEsq(i, i->esq);

    return i;
}

void remover(Arvore* arvore, int elemento)
{
    removerNo(elemento, arvore->raiz);
}

void caminarCentral(No* i)
{
    if(i != NULL)
    {
        caminarCentral(i->esq);
        printf("%d\n", i->elemento);
        caminarCentral(i->dir);
    }
}

void caminhar(Arvore* arvore)
{
    caminarCentral(arvore->raiz);
}

int main()
{
    Arvore* arvore = newArvore();

    inserir(arvore,7);
    inserir(arvore,3);
    inserir(arvore,2);
    inserir(arvore,6);
    inserir(arvore,1);
    remover(arvore, 6);
    caminhar(arvore);

    if(pesquisar(arvore, 7)) printf("EXISTE\n");
    else printf("NÃO EXISTE\n");


    freeArvore(arvore);
    return 0;
}