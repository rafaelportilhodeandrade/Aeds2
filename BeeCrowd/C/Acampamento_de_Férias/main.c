#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

typedef struct Aluno{

    int nmr;
    char nome[200];

}Aluno;

void getNome(char entrada[], char nome[])
{

    int i;
    for( i = 0; entrada[i] != ' '; i++)
    {
        nome[i] = entrada[i];
    }

    nome[i] = '\0';

    

}

int getNumero(char entrada[])
{
    int nmr = 0;

    for(int i = 0; entrada[i] != '\0'; i++)
    {
        if(entrada[i] == ' ')
        {
            nmr = entrada[i + 1] - '0';
        }
    }

    

    return nmr;
}

void removerAluno(Aluno* alunos, int pos, int* tam)
{
    for(int i = pos; i < (*tam) - 1; i++)
    {
        alunos[i] = alunos[i+1];
    }

    (*tam)--;
}


void getCampeao(Aluno* alunos, int tam)
{
    int pos = 0;
    int nmr = alunos[0].nmr;

    while (tam > 1)
    {
        int passos = (nmr - 1) % tam;

        if (nmr % 2 == 1)  
        {
            pos = (pos + passos) % tam;
        }
        else              
        {
            pos = (pos - passos + tam) % tam;
        }
        nmr = alunos[pos].nmr;
        removerAluno(alunos, pos, &tam);
        if (pos == tam)
            pos = 0;
    }

    printf("Vencedor(a): %s\n", alunos[0].nome);
}

int main()
{
    int nmrCriancas;
    scanf("%d", &nmrCriancas);

    char entrada[200];
    char nome[200];
    int nmr;

    while(nmrCriancas > 0)
    {

        Aluno* alunos = (Aluno*) malloc (nmrCriancas * sizeof (Aluno));

        for(int i = 0; i < nmrCriancas; i++)
        {
            scanf(" %[^\n]", entrada);
            //printf("%s\n", entrada);
            getNome(entrada, nome);
            nmr = getNumero(entrada);

            //printf("%s", nome);
            //printf(" %d\n", nmr);

            strcpy((alunos + i)->nome, nome);
            (alunos + i) -> nmr = nmr;
        }

        getCampeao(alunos, nmrCriancas);

        scanf("%d", &nmrCriancas);
    }

    

    return 0;
}