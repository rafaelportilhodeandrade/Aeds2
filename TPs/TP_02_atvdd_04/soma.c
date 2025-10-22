#include <stdio.h>
#include <stdlib.h>

// Calcula a soma dos dígitos de um número de forma recursiva
int somaDigitos(int n) 
{
    if (n < 10) {
        return n; // caso base: número de um dígito
    }
    return (n % 10) + somaDigitos(n / 10); // soma último dígito com a recursão
}

// Verifica se a entrada é a string "FIM"
int isFIM(char entrada[]) 
{
    return (entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M' && entrada[3] == '\0');
}

int main() 
{
    char entrada[100];
    int continuar = 1; 

    while (continuar && scanf("%s", entrada) == 1) 
    {
        if (isFIM(entrada)) 
        {
            continuar = 0; 
        } 
        else 
        {
            int num = atoi(entrada);           // converte string para inteiro
            printf("%d\n", somaDigitos(num));  
        }
    }

    return 0;
}
