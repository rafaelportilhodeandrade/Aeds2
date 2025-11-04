#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Celula
{
    int elemento;
    struct Celula* prox;
}Celula;

typedef struct Fila
{
    Celula* primeiro;
    Celula* ultimo;
}Fila;

Celula* newCelula(int elemento)
{
    Celula* celula = (Celula*) malloc (sizeof(Celula));
    celula->elemento = elemento;
    celula->prox = NULL;
    return celula;
}

Fila* newFila()
{
    Fila* fila = (Fila*) malloc (sizeof(Fila));
    fila->primeiro = newCelula(0);
    fila->ultimo = fila->primeiro;
    return fila;
}

void freeCelula(Celula* i)
{
    while(i != NULL)
    {
        Celula* j = i;
        i = i->prox;
        free(j);
    }
}

// liberar fila
void freeFila(Fila* fila)
{
    freeCelula(fila->primeiro);
    free(fila);
}

//inserir fim
void enfileirar(Fila* fila, int elemento)
{
    Celula* i = newCelula(elemento);
    fila->ultimo->prox = i;
    fila->ultimo = i;
}

bool isvazia(Fila* fila)
{
    if(fila->primeiro->prox == NULL) return true;
    else return false;
}

//inserir remover inicio
void desenfileirar(Fila* fila)
{
    if(!isvazia(fila))
    {
        Celula* i = fila->primeiro->prox;
        fila->primeiro->prox = i->prox;

        if (fila->primeiro->prox == NULL)
        {
            fila->ultimo = fila->primeiro;
        }
        
        i->prox = NULL;
        free(i); 
    }
}

void mostrar(Fila* fila)
{
    Celula* i = fila->primeiro->prox;
    while(i != NULL)
    {
        printf("%d\n", (i->elemento));
        i = i->prox;
    }
}

int main()
{
    Fila* fila = newFila();

    enfileirar(fila,1);
    enfileirar(fila,2);
    enfileirar(fila,3);
    enfileirar(fila,4);
    enfileirar(fila,5);
    desenfileirar(fila);

    mostrar(fila);

    freeFila(fila);
    return 0;
}