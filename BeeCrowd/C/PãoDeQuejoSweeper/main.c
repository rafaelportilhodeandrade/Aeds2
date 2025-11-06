#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>



int main()
{
    int lin, col;

    while(scanf("%d %d", &lin, &col) != EOF)
    {
        int matriz[lin][col];
        int matrizAux[lin][col];

        // preencher matriz
        for(int i = 0; i < lin; i++)
        {
            for(int j = 0; j < col; j++)
            {
                scanf("%d", &matriz[i][j]);
            }
        }


        // substituir valores
        for(int i = 0; i < lin; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(matriz[i][j] == 1)
                {
                    matrizAux[i][j] = 9;
                }
                else
                {
                    int cont = 0;
                    // elemento acima (se existir)
                    if(i > 0)
                    {
                        if(matriz[i - 1][j] == 1)
                        {
                            cont++;
                        }
                    }

                    // elemento abaixo (se existir)
                    if(i < lin - 1)
                    {
                        if(matriz[i + 1][j] == 1)
                        {
                            cont++;
                        }
                    }

                    // elemento à direita (se existir)
                    if(j < col - 1)
                    {
                        if(matriz[i][j + 1] == 1)
                        {
                            cont++;
                        }
                    }

                    // elemento à esquerda (se existir)
                    if(j > 0)
                    {
                        if(matriz[i][j - 1] == 1)
                        {
                            cont++;
                        }
                    }

                    matrizAux[i][j] = cont;

                    
                }
                printf("%d", matrizAux[i][j]);
            }
            printf("\n");
        }
    }
 
    

    

    return 0;
}