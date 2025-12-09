#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#define TAM 255

typedef struct No
{
    char elemento;
    struct No* prox[TAM];
    bool folha;
}No;

typedef struct Arvore
{
    No* raiz;
}Arvore;

No* newNo(char elemento)
{
    No* no = (No*) malloc (sizeof (No));

    no->elemento = elemento;
    no->folha = false;
    
    for(int i = 0; i < TAM; i++)
        no->prox[i] = NULL;

    return no;
}

Arvore* newArvore()
{
    Arvore* arvore = (Arvore*) malloc (sizeof (Arvore));

    arvore->raiz = newNo('*');

    return arvore;
}

int getHash(char letra)
{
    return (int) letra % TAM;
}

void inserir(char palavra[], Arvore* arvore)
{
    inserirPalavra(palavra, arvore->raiz, 0);
}

void inserirPalavra(char palavra[], No* no, int i)
{
    int pos = getHash(palavra[i]);

    if(no->prox[pos] == NULL)
    {
        no->prox[pos] = newNo(palavra[i]);

        if(strlen(palavra) - 1 == i)
        {
            no->prox[pos]->folha = true;
        }
        else inserirPalavra(palavra, no->prox[pos], i + 1);
    }
    else if(strlen(palavra) - 1 > i) inserirPalavra(palavra, no->prox[pos], i + 1);
    else return;
}

bool pesquiar(char palavra[], Arvore* arvore)
{
    return pesquisarPalavra(palavra, arvore->raiz, 0);
}

bool pesquisarArvore(char palavra[], No* no, int i)
{
    int pos = getHash(palavra[i]);

    if(no->prox[pos] == NULL)
    {
        return false;
    }
    else if(i == strlen(palavra) - 1)
    {
        return no->prox[pos]->folha = true;
    }
    else if(i < strlen(palavra) - 1)
    {
        return pesquisarPalavra(palavra, no->prox[pos], i + 1);
    }
}

void mostrar(Arvore* arvore)
{
    char buffer[1000];
    buffer[0] = '\0';  
    mostrarPalavras(buffer, arvore->raiz);
}

void mostrarPalavras(char s[], No* no)
{
    if(no->elemento != '*')   
    {
        int tam = strlen(s);
        s[tam] = no->elemento;
        s[tam+1] = '\0';
    }

    if(no->folha == true)
    {
        printf("%s\n", s);
    }

    for(int i = 0; i < TAM; i++)
    {
        if(no->prox[i] != NULL)
        {
            mostrarPalavras(s, no->prox[i]);
        }
    }

    
    s[strlen(s)-1] = '\0';
}


int main()
{
    Arvore* arvore = newArvore();

    return 0;
}