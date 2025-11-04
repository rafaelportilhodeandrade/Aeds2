#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

typedef struct CelulaPilha
{
    int elemento;
    struct CelulaPilha* prox;
}CelulaPilha;

typedef struct CelulaLista
{
    CelulaPilha* topo;
    struct CelulaLista* prox;
}CelulaLista;

typedef struct Lista
{
    struct CelulaLista* pimeiro;
    struct CelulaLista* ulitmo;
}Lista;

CelulaPilha* newCelulaPilha(int elemento)
{
    CelulaPilha* celula = (CelulaPilha*) malloc (sizeof(CelulaPilha));
    celula->elemento = elemento;
    celula->prox = NULL;

    return celula;
}

CelulaLista* newCelulaPilha(int elemento)
{
    CelulaLista* celula = (CelulaLista*) malloc (sizeof(CelulaLista));
    celula->topo = NULL;
    celula->prox = NULL;

    return celula;
}

Lista* newLista()
{
    Lista* lista = (Lista*) malloc(sizeof(Lista));
    lista->primeiro = lista->ultimo = NULL;

}

int main()
{

    return 0;
}

