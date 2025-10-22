#include <stdio.h>
#include <stdbool.h>

// Verifica se só tem vogais
bool vogal(char frase[], int i) {
    if (i < 0) return true;

    char c = frase[i];

    if (!(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||
          c=='A'||c=='E'||c=='I'||c=='O'||c=='U'))
        return false;

    return vogal(frase, i - 1);
}

// Verifica se só tem consoantes
bool consoante(char frase[], int i) {
    if (i < 0) return true;

    char c = frase[i];

    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')))
        return false;

    if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||
        c=='A'||c=='E'||c=='I'||c=='O'||c=='U')
        return false;

    return consoante(frase, i - 1);
}

// Verifica se é número inteiro
bool numeroInteiro(char frase[], int i) {
    if (i < 0) return true;

    if (frase[i] < '0' || frase[i] > '9') return false;

    return numeroInteiro(frase, i-1);
}

// Verifica se é número real (um ponto permitido)
bool numeroReal(char frase[], int i) {
    int pontos = 0; 
    if (i == -2) { pontos = 0; return true; } 

    if (i < 0) {
        return (pontos == 1); 
    }

    char c = frase[i];
    if (c == '.' || c == ',') {
        pontos++;
        if (pontos > 1) return false;
    }
    else if (c < '0' || c > '9') return false;

    return numeroReal(frase, i-1);
}

// Calcula tamanho da string
int tamanho(char frase[]) {
    int pos = 0;
    while (frase[pos] != '\0') pos++;
    return pos;
}

// Condição de parada
bool isEnd(char frase[], int tam) {
    return !(tam == 3 && frase[0]=='F' && frase[1]=='I' && frase[2]=='M');
}

int main() {
    char frase[99];

    scanf(" %99[^\n]", frase);

    int tam = tamanho(frase);

    while (isEnd(frase, tam)) {

        printf("%s ", vogal(frase, tam-1) ? "SIM" : "NAO");
        printf("%s ", consoante(frase, tam-1) ? "SIM" : "NAO");
        printf("%s ", numeroInteiro(frase, tam-1) ? "SIM" : "NAO");

        numeroReal(frase, -2);
        printf("%s\n", numeroReal(frase, tam-1) ? "SIM" : "NAO");

        scanf(" %99[^\n]", frase);
        tam = tamanho(frase);
    }

    return 0;
}
