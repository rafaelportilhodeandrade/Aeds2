#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

typedef struct Celula
{
    int elemento;
    struct Celula *prox;
} Celula;

typedef struct Pilha
{
    Celula *topo;
} Pilha;

Celula *newCelula(int elemento)
{
    Celula *celula = (Celula *)malloc(sizeof(Celula));
    celula->elemento = elemento;
    celula->prox = NULL;

    return celula;
}

Pilha *newPilha()
{
    Pilha *pilha = (Pilha *)malloc(sizeof(Pilha));
    pilha->topo = NULL;

    return pilha;
}

void inserir(int elemento, Pilha* pilha)
{
    Celula *temp = newCelula(elemento);
    temp->prox = pilha->topo;
    pilha->topo = temp;
}

void mostrarMenor(Pilha* pilha)
{
    if (pilha->topo == NULL)
    {
        printf("EMPTY\n");
    }
    else
    {
        Celula *aux = pilha->topo->prox;
        int menor = pilha->topo->elemento;
        for (; aux != NULL; aux = aux->prox)
        {
            if (aux->elemento < menor)
            {
                menor = aux->elemento;
            }
        }
        printf("%d\n", menor);
    }
}

void remover(Pilha* pilha)
{
    if (pilha->topo == NULL)
    {
        printf("EMPTY\n");
    }
    else
    {
        Celula *temp = pilha->topo;
        pilha->topo = pilha->topo->prox;
        temp->prox = NULL;
        free(temp);
    }
}

int main()
{
    Pilha *pilha = newPilha();
    int N;
    char entrada[50];
    int elemento;

    scanf("%d", &N);

    for (int i = 0; i < N; i++)
    {
        scanf(" %s", entrada);

        if (strcmp(entrada, "PUSH") == 0)
        {
            scanf("%d", &elemento);
            inserir(elemento, pilha);
        }
        else if (strcmp(entrada, "POP") == 0)
        {
            remover(pilha);
        }
        else if (strcmp(entrada, "MIN") == 0)
        {
            mostrarMenor(pilha);
        }
    }

    return 0;
}