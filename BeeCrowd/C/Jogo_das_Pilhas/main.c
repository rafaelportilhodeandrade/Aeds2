#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main()
{
    int entrada;
    int valorCartas;
    int soma;

    scanf("%d", &entrada);

    while(entrada != 0)
    {
        valorCartas = 0;
        soma = 0;
        for(int i = 0; i < entrada; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                scanf("%d", &valorCartas);
                soma += valorCartas;
            }
        }

        if(soma % 3 == 0)
        {
            printf("1\n");
        }
        else{
            printf("0\n");
        }

        scanf("%d", &entrada);
    }

    return 0;
}