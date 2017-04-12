#include <stdio.h>
#include <string.h>

// tellhimaboutme cafe
// veqpjiredozxoe

void cifra(char texto[], char chave[], char cifra[]) {
    int tam_txt = strlen(texto),
        tam_chave = strlen(chave),
        i = 0;
        
    for (; i < tam_txt; i++) {
        cifra[i] = texto[i] ^ chave[i % tam_chave];
    }
}

void decifra(char cifra[], char chave[], char texto[]) {
    int tam_cifra = strlen(cifra),
        tam_chave = strlen(chave),
        i = 0;
        
    for (; i < tam_cifra; i++) {
        texto[i] = cifra[i] ^ chave[i % tam_chave];
    }
}

int main(int argc, int *argv[]) {
    char texto[100], chave[100], txt_cifrado[100], txt_decifrado[100];
    int i = 0;
    
    scanf("%s %s", texto, chave);
    
    cifra(texto, chave, txt_cifrado);
    //printf("Cifrado: %s\n", txt_cifrado);
    
    printf("Cifrado: ");
    for (i = 0; i < strlen(txt_cifrado); i++) {
        printf("%x", txt_cifrado[i]);
    }
    printf("\n");
    
    decifra(txt_cifrado, chave, txt_decifrado);
    printf("Decifrado: %s\n", txt_decifrado);
    
    return 0;
}
