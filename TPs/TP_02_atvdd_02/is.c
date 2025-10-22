#include <stdio.h>
#include <stdbool.h>

// Função para verificar se todos os caracteres são vogais (a/A, e/E, i/I, o/O, u/U)
bool vogal(char frase[])
{
    for (int i = 0; frase[i] != '\0'; i++)
    {
        if (frase[i] != 'a' && frase[i] != 'e' && frase[i] != 'i' && frase[i] != 'o' && frase[i] != 'u' &&
            frase[i] != 'A' && frase[i] != 'E' && frase[i] != 'I' && frase[i] != 'O' && frase[i] != 'U')
        {
            return false;
        }
    }
    return true;
}

// Função para verificar se todos os caracteres são consoantes (letras que não são vogais)
bool consoante(char frase[])
{
    for (int i = 0; frase[i] != '\0'; i++)
    {
        char c = frase[i];

        // Se não for letra, retorna false
        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')))
            return false;

        // Se for vogal, retorna false
        if (c == 'a'||c=='e'||c=='i'||c=='o'||c=='u'||
            c=='A'||c=='E'||c=='I'||c=='O'||c=='U')
            return false;
    }
    return true; // só letras e não-vogais
}

// Função para verificar se é um número inteiro (com sinal opcional)
bool numeroInteiro(char frase[])
{
    for (int i = 0; frase[i] != '\0'; i++)
    {
        if (i == 0 && (frase[i] == '+' || frase[i] == '-'))
            continue;
        if (frase[i] < '0' || frase[i] > '9')
            return false;
    }
    return true;
}

// Função para verificar se é um número real (com ponto e sinal opcional)
bool numeroReal(char frase[])
{
    bool temDigito = false;
    bool temPonto = false;

    for (int i = 0; frase[i] != '\0'; i++)
    {
        char c = frase[i];

        if (i == 0 && (c == '+' || c == '-'))
            continue;

        if (c >= '0' && c <= '9')
            temDigito = true;
        else if (c == '.')
        {
            if (temPonto) return false; 
            temPonto = true;
        }
        else
            return false; 
    }

    return temDigito && temPonto;
}


// Função para calcular o tamanho da string
int tamanho(char frase[])
{
    int position = 0;
    while (frase[position] != '\0')
    {
        position++;
    }
    return position;
}

// Função para verificar se a string é "FIM"
bool igual(char frase[], int tam)
{
    if (tam == 3 && (frase[0] == 'F') && (frase[1] == 'I') && (frase[2] == 'M'))
        return false;
    return true;
}

int main()
{
    char frase[100];
    int tam;

    scanf(" %99[^\n]", frase);

    getchar();
    tam = tamanho(frase);

    while (igual(frase, tam))
    {
        printf("%s ", vogal(frase) ? "SIM" : "NAO");
        printf("%s ", consoante(frase) ? "SIM" : "NAO");
        printf("%s ", numeroInteiro(frase) ? "SIM" : "NAO");
        printf("%s\n", numeroReal(frase) ? "SIM" : "NAO");

        scanf(" %99[^\n]", frase);

        getchar();
        tam = tamanho(frase);
    }

    return 0;
}
