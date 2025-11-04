#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Celula
{
    int elemento;
    struct Celula* prox;

}Celula;

typedef struct
{
    Celula* topo;
}Pilha;

Celula* newCelula(int x)
{
    Celula* celula = (Celula*) malloc (sizeof (Celula));
    celula->elemento = x;
    celula->prox = NULL;
    return celula;
}

Pilha* newPilha()
{
    Pilha* pilha = (Pilha*) malloc (sizeof (Pilha));
    pilha->topo = newCelula(0);
    return pilha;
}

void empilhar(Pilha* pilha,int elemento)
{
    Celula* temp = newCelula(elemento);
    temp->prox = pilha->topo->prox;
    pilha->topo->prox = temp;
}

bool isvazia(Pilha* pilha)
{
    if(pilha->topo->prox == NULL) return true;
    else return false;   
}

void desempilhar(Pilha* pilha)
{
    if(!isvazia(pilha))
    {
        Celula* temp = pilha->topo->prox;
        pilha->topo->prox = temp->prox;
        temp->prox = NULL;
        free(temp);
    }
}

void liberarCelula(Celula* celula)
{
    celula->prox=NULL;
    free(celula);
}

void liberarPilha(Pilha* pilha)
{
    Celula *temp, *i = pilha->topo;
    while(i != NULL)
    {
        temp = i;
        i = i->prox;
        liberarCelula(temp);
    }
    pilha->topo = NULL;
    free(pilha);
}

void mostrar(Pilha* pilha)
{
    Celula* i = pilha->topo->prox;
    for(; i != NULL; i = i->prox)
    {
        printf("%d\n", i->elemento);
    }
}

int main()
{
    Pilha* pilha = newPilha();

    empilhar(pilha, 1);
    empilhar(pilha, 2);
    empilhar(pilha, 3);
    empilhar(pilha, 4);
    empilhar(pilha, 5);
    desempilhar(pilha);

    mostrar(pilha);
    
    liberarPilha(pilha);
    return 0;
}