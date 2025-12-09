#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

typedef struct No
{
    int elemento;
    int nivel;
    struct No *esq;
    struct No *dir;
} No;

typedef struct Arvore
{
    No *raiz;
} Arvore;

No *newNo(int elemento)
{
    No *no = (No *)malloc(sizeof(No));
    no->elemento = elemento;
    no->nivel = 0;
    no->esq = NULL;
    no->dir = NULL;

    return no;
}

Arvore *newArvore()
{
    Arvore *arvore = (Arvore *)malloc(sizeof(Arvore));
    arvore->raiz = NULL;
    return arvore;
}

void setNivel(No *i)
{
    int nivelEsq = (i->esq == NULL ? 0 : i->esq->nivel);
    int nivelDir = (i->dir == NULL ? 0 : i->dir->nivel);
    i->nivel = 1 + (nivelEsq > nivelDir ? nivelEsq : nivelDir);
}

int getFatorBalanceamento(No *i)
{
    int fatorBalanceamento = 0;
    int nivelEsq = (i->esq == NULL ? 0 : i->esq->nivel);
    int nivelDir = (i->dir == NULL ? 0 : i->dir->nivel);

    fatorBalanceamento = nivelDir - nivelEsq;

    return fatorBalanceamento;
}

No *rotacionarDir(No *no)
{
    No *noDir = no->dir;
    No *dirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = dirEsq;

    setNivel(no);
    setNivel(noDir);

    return noDir;
}

No *rotacionarEsq(No *no)
{
    No *noEsq = no->esq;
    No *esqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = esqDir;

    setNivel(no);
    setNivel(noEsq);

    return noEsq;
}

No *rotacaoDirEsq(No *no)
{
    no->dir = rotacionarDir(no->dir);
    return rotacionarEsq(no);
}

No *rotacaoEsqDir(No *no)
{
    no->esq = rotacionarEsq(no->esq);
    return rotacionarDir(no);
}

No *balancear(No *i)
{
    int fb = getFatorBalanceamento(i);

    if (fb >= 2)
    {
        if (getFatorBalanceamento(i->dir) < 0)
            i = rotacaoDirEsq(i);
        else
            i = rotacionarEsq(i);
    }
    else if (fb <= -2)
    {
        if (getFatorBalanceamento(i->esq) > 0)
            i = rotacaoEsqDir(i);
        else
            i = rotacionarDir(i);
    }else{
        setNivel(i);
    }

    return i;
}

void freeNo(No *i)
{
    if (i != NULL)
    {
        freeNo(i->esq);
        freeNo(i->dir);
        free(i);
    }
}

void freeArvore(Arvore *arvore)
{
    freeNo(arvore->raiz);
    free(arvore);
}

No *inserirNo(int elemento, No *i)
{
    if (i == NULL)
        i = newNo(elemento);
    else if (i->elemento > elemento)
        i->esq = inserirNo(elemento, i->esq);
    else if (i->elemento < elemento)
        i->dir = inserirNo(elemento, i->dir);
    i = balancear(i);

    return i;
}

void inserir(Arvore *arvore, int elemento)
{
    arvore->raiz = inserirNo(elemento, arvore->raiz);
}

bool pesquisarNo(int elemento, No *i)
{
    bool resp;

    if (i == NULL)
        resp = false;
    else if (i->elemento == elemento)
        resp = true;
    else if (i->elemento > elemento)
        resp = pesquisarNo(elemento, i->esq);
    else if (i->elemento < elemento)
        resp = pesquisarNo(elemento, i->dir);

    return resp;
}

bool pesquisar(Arvore *arvore, int elemento)
{
    return pesquisarNo(elemento, arvore->raiz);
}

No *maiorEsq(No *i, No *j)
{
    if (j->dir == NULL)
    {
        i->elemento = j->elemento;
        j = j->esq;
    }
    else
        j->dir = maiorEsq(i, j->dir);

    return j;
}

No *removerNo(int elemento, No *i)
{
    if (i == NULL)
        return 0;
    else if (i->elemento > elemento)
        i->esq = removerNo(elemento, i->esq);
    else if (i->elemento < elemento)
        i->dir = removerNo(elemento, i->dir);
    else if (i->dir == NULL)
        i = i->esq;
    else if (i->esq == NULL)
        i = i->dir;
    else
        i->esq = maiorEsq(i, i->esq);

    if (i != NULL)
    {
        i = balancear(i);
    }

    return i;
}

void remover(Arvore *arvore, int elemento)
{
    arvore->raiz = removerNo(elemento, arvore->raiz);
}

void caminarCentral(No *i)
{
    if (i != NULL)
    {
        caminarCentral(i->esq);
        int fb = getFatorBalanceamento(i);
        printf(" %d ", i->elemento);
        printf(" ( %d ) ", i->nivel);
        printf(" ( %d )\n ", fb);
        caminarCentral(i->dir);
    }
}

void caminhar(Arvore *arvore)
{
    caminarCentral(arvore->raiz);
}

int main()
{
    Arvore *arvore = newArvore();

    inserir(arvore, 7);
    printf("AQUI");
    inserir(arvore, 3);
    printf("AQUI");
    inserir(arvore, 2);
    printf("AQUI");
    inserir(arvore, 6);
    printf("AQUI");
    inserir(arvore, 1);
    printf("AQUI");
    caminhar(arvore);


    freeArvore(arvore);
    return 0;
}