#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void ordenar(int arrayAux[], int tam)
{
    for(int i = 0; i < tam - 1; i++)
    {
        for(int j = 0; j < tam - 1 - i; j++)
        {
            if(arrayAux[j] < arrayAux[j + 1])
            {
                int temp = arrayAux[j];
                arrayAux[j] = arrayAux[j + 1];
                arrayAux[j + 1] = temp;
            }
        }
    }
}


void compararArrays(int array[], int arrayOrdenado[], int tam)
{
    int naoMoveram = 0;

    for(int i = 0; i < tam; i++)
    {
        if(array[i] == arrayOrdenado[i])
        {
            naoMoveram++;
        }
    }

    printf("%d\n", naoMoveram);
}

int main()
{
    int casos;
    int numeroDeAlunos;

    scanf("%d", &casos);

    for(int i = 0; i < casos; i++)
    {
        // entrada do nmr de alunos
        scanf("%d", &numeroDeAlunos);
        // preencher com as notas dos alunos;
        int alunosNotas[numeroDeAlunos];
        int arrayAux[numeroDeAlunos];
        for(int j = 0; j < numeroDeAlunos; j++)
        {
            scanf("%d", &alunosNotas[j]);
            arrayAux[j] = alunosNotas[j];
        }

        ordenar(arrayAux, numeroDeAlunos);
        compararArrays(alunosNotas, arrayAux, numeroDeAlunos);
    }

    return 0;
}