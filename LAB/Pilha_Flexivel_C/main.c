#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Celula {
    int elemento;
    struct Celula* prox;
} Celula;

typedef struct Pilha {
    struct Celula* topo;
} Pilha;

Celula* newCelula(int elemento) {
    Celula* celula = (Celula*) malloc(sizeof(Celula));
    celula->elemento = elemento;
    celula->prox = NULL;
    return celula;
}

Pilha* newPilha() {
    Pilha* pilha = (Pilha*) malloc(sizeof(Pilha));
    pilha->topo = NULL;
    return pilha;
}

void liberarCelula(Celula* celula) {
    free(celula);
}

void liberarPilha(Pilha* pilha) {
    Celula* i = pilha->topo;
    Celula* j;

    while (i != NULL) {
        j = i;
        i = i->prox;
        liberarCelula(j);
    }
    free(pilha);
}

void empilhar(Pilha* pilha, int elemento) {
    Celula* temp = newCelula(elemento);
    temp->prox = pilha->topo;
    pilha->topo = temp;
}

void desempilhar(Pilha* pilha) {
    if (pilha->topo == NULL) {
        printf("-1\n");
    } else {
        Celula* temp = pilha->topo;
        pilha->topo = pilha->topo->prox;
        printf("%d\n", temp->elemento);
        free(temp);
    }
}

void mostrar(Pilha* pilha) {
    if (pilha->topo == NULL) {
        printf("V\n");
    } else {
        for (Celula* i = pilha->topo; i != NULL; i = i->prox) {
            printf("%d ", i->elemento);
        }
        printf("\n");
    }
}

bool pesquisar(Pilha* pilha, int elemento) {
    for (Celula* i = pilha->topo; i != NULL; i = i->prox) {
        if (i->elemento == elemento)
            return true;
    }
    return false;
}

int main() {
    Pilha* pilha = newPilha();
    char comando;
    int elemento;

    while (scanf(" %c", &comando) == 1) {
        if (comando == 'E') {
            scanf("%d", &elemento);
            empilhar(pilha, elemento);
        } else if (comando == 'D') {
            desempilhar(pilha);
        } else if (comando == 'M') {
            mostrar(pilha);
        } else if (comando == 'P') {
            scanf("%d", &elemento);
            printf(pesquisar(pilha, elemento) ? "S\n" : "N\n");
        }
    }

    liberarPilha(pilha);
    return 0;
}
