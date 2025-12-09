#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

void ordenar(int array[], int tam)
{
    int i = 0;
    bool movimento = true;

    // acaba quando n der para fzr mais movimentos
    for(; movimento; i++)
    {
        // faz apenas um movimento e para, para o prox jogador poder fzr o prox movimento
        for(int j = 0; j < tam - 1; j++)
        {
            movimento = false;
            
            if(array[j] > array[j + 1])
            {
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;

                j = tam;
                movimento = true;
            }
        }
    }

    // if(i % 2 == 0)
    // {
    //     printf("Marcelo\n");
    // }
    // else{
    //     printf("Carlos\n");
    // }
}

void separarNumeros(char entrada[])
{
    int array[50];
    int tam = 0;

    char *token = strtok(entrada, " ");

    while (token != NULL)
    {
        array[tam] = atoi(token);
        printf("%d ", array[tam]);
        tam++;
        token = strtok(NULL, " ");
    }
    printf("\n");

    ordenar(array, tam);
}


int main()
{
    char entrada[50];

    scanf(" %[^\n]", entrada);
    

    while(strcmp(entrada, "0") != 0)
    {
        separarNumeros(entrada);
        
        scanf(" %[^\n]", entrada);
    }

    return 0;
}