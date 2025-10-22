#include <stdio.h>
#include <stdbool.h>

// Função substituindo lerLinha usando fgets
void lerLinha(char *frase, int tamanho) {
    if (fgets(frase, tamanho, stdin) != NULL) {
        // Remove o '\n' manualmente
        int i = 0;
        while (i < tamanho && frase[i] != '\0') {
            if (frase[i] == '\n') {
                frase[i] = '\0';
                break;
            }
            i++;
        }
    }
}


//Calcula e retorna o tamanho da string
int tamanho(char frase[])
{
    int position = 0;
    char caractere = frase[position];
    while (caractere != '\0') {  
        position++;
        caractere = frase[position];
    }
    return position;
}

//Verifica se está escrito FIM dentro da string para terminar a execução
bool isFIM(char frase[], int tam)
{
    if(tam == 3 && frase[0] == 'F' && frase[1] == 'I' && frase[2] == 'M')
    {
        return false;
    }
    else
    {
        return true;
    }
}    


//Função recursiva que retorna true se for palindromo e false caso não seja
bool palindromo (char frase[], int point_0, int point_1)
{
    if (point_0 >= point_1) {
        return true;
    }
    
    if (frase[point_0] != frase[point_1]) {
        return false;
    }

    return palindromo(frase, point_0 + 1, point_1 - 1);
}

int main() 
{
    int tam;
    char frase[1000];

    lerLinha(frase, 1000);
    tam = tamanho(frase);

    while(isFIM(frase, tam))
    {
        if(palindromo(frase, 0, tam - 1))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }

        lerLinha(frase, 1000);
        tam = tamanho(frase);
    }

    return 0;
}
