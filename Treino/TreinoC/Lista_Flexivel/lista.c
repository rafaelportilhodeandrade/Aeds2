#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Celula
{
    int elemento;
    struct Celula* prox;
}Celula;

typedef struct Lista
{
    Celula* primeiro;
    Celula* ultimo;
}Lista;

Celula* newCelula(int elemento)
{
    Celula* celula = (Celula*) malloc(sizeof(Celula));
    celula->elemento = elemento;
    celula->prox = NULL;

    return celula;
}

Lista* newLista()
{
    Lista* lista = (Lista*) malloc (sizeof(Lista));
    lista->primeiro = newCelula(0);
    lista->ultimo = lista->primeiro;

    return lista;
}

void deletarCelula(Celula* celula)
{
    celula->prox = NULL;
    free(celula);
}

void deletarLista(Lista* lista)
{
    Celula* i = lista ->primeiro;
    Celula* temp;

    while(i != NULL)
    {
        temp = i;
        i = i->prox;
        deletarCelula(temp);
    }
    free(lista);
}

// |--------- Inserções --------|

void inserirInicio(Lista* lista, int elemento)
{
    Celula* i = newCelula(elemento);
    i->prox = lista->primeiro->prox;
    lista->primeiro->prox = i;
    if(lista->primeiro == lista->ultimo)
    {
        lista->ultimo = i;
    }
}

void inserirFim(Lista* lista, int elemento)
{
    Celula* i = newCelula(elemento);
    lista->ultimo->prox = i;
    lista->ultimo = i;
}

//Funação auxiliar
int getTam(Lista* lista)
{
    Celula* i = lista->primeiro->prox;
    int cont = 0;

    for(; i != NULL; i = i->prox)
    {
        cont++;
    }

    return cont;
}

void inserir(Lista* lista, int elemento, int pos)
{
    int tam = getTam(lista);
    if(pos == 0) inserirInicio(lista, elemento);
    else if(pos == tam) inserirFim(lista,elemento);
    else if(pos < 0 ||pos > tam) return;
    else
    {
        Celula* i = lista->primeiro;
        for(int j = 0; j < pos; j++) i = i->prox;
        Celula* temp = newCelula(elemento);
        temp->prox = i->prox;
        i->prox = temp;
    }
}

// |--------- Remoções --------|

bool isVazio(Lista* lista)
{
    if(lista->primeiro->prox) return true;
    else return false;
}

void removerInicio(Lista* lista)
{
    if(!isVazio(lista)) 
    {
        Celula* i = lista->primeiro->prox;
        lista->primeiro->prox = i->prox;
        i->prox = NULL;

        if(lista->primeiro->prox == NULL)
        {
            lista->ultimo = lista->primeiro;
        }

        free(i);
    }
}

void removerFim(Lista* lista)
{
    if(!isVazio(lista))
    {
        Celula* i = lista->primeiro;
        for(; i->prox != lista->ultimo; i = i->prox);
        Celula* j = lista->ultimo;
        lista->ultimo = i;
        lista->ultimo->prox = NULL;

        if(lista->primeiro->prox == NULL)
        {
            lista->ultimo = lista->primeiro;
        }
        free(j);
    }
}

void remover(Lista* lista, int pos)
{
    if(!isVazio(lista))
    {
        int tam = getTam(lista);
        if (pos < 0 || pos >= tam) return;

        Celula* i = lista->primeiro;
        for(int j = 0; j < pos; j++) i = i->prox;
        Celula* j = i->prox;
        i->prox = j->prox;

        if(j == lista->ultimo)
        {
            lista->ultimo = i;
        }

        j->prox = NULL;
        free(j);

        if(lista->primeiro->prox == NULL)
        {
            lista->ultimo = lista->primeiro;
        }
    }
}

void mostrar(Lista* lista)
{
    Celula* i = lista->primeiro->prox;
    for(; i != NULL; i = i->prox) printf("%d\n", i->elemento);
}

int main()
{
    Lista* lista = newLista();

    inserirInicio(lista, 1);
    inserirInicio(lista, 2);
    inserirInicio(lista, 3);
    inserirInicio(lista, 4);
    inserirInicio(lista, 5);
    inserir(lista, 0, 1);

    mostrar(lista);

    deletarLista(lista);
    return 0;
}