#include <stdio.h>
#include <string.h>
#define MOD(a, b) (a % b + b) % b

void cifra_vigenere(char texto[], char chave[], char cifra[]) {
    int tam_txt = strlen(texto), 
        tam_chave = strlen(chave),
        i = 0;

    for (; i < tam_txt; i++) {
        cifra[i] = MOD((texto[i]-'a') + (chave[i % tam_chave]-'a'), 26) + 'a';
    }
}

void decifra_vigenere(char cifra[], char chave[], char texto[]) {
    int tam_cifra = strlen(cifra), 
        tam_chave = strlen(chave),
        i = 0;

    for (; i < tam_cifra; i++) {
        texto[i] = MOD((cifra[i]-'a') - (chave[i % tam_chave]-'a'), 26) + 'a';
    }
}

int main(int argc, char *argv[]) {
    char texto[100], chave[100], txt_cifrado[100], txt_decifrado[100];
    int i;
    
    scanf("%s %s", texto, chave);
    
    cifra_vigenere(texto, chave, txt_cifrado);
    printf("Cifrado: %s\n", txt_cifrado);
    
    printf("Cifrado hex: ");
    for (i = 0; i < strlen(txt_cifrado); i++) {
        printf("%x", txt_cifrado[i]);
    }
    printf("\n");
    
    decifra_vigenere(txt_cifrado, chave, txt_decifrado);
    printf("Decifrado: %s\n", txt_decifrado);
    
    return 0;
}
