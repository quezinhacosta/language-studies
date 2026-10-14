#include <stdio.h>
#include <stdlib.h>

void troca(float *x, float *y){
	float *aux;
	aux = (float *)malloc(sizeof(float));
	
	*aux = *x; 
	*x = *y; 
	*y = *aux;
}

float *alocar_vetor_int(float tamanho){
	float *aux = (float *)malloc(sizeof(float)*tamanho);
}

float calcularnotas(float *notas){
    int i;
    float soma = 0;
    for(i = 0; i < 10; i++){
        soma += notas[i];
    }

    return soma / 10;
}


void main () {
    float notas[10];
    int cont = 10;

    while (cont > 0){
        printf("Digite a nota %d: ", cont);
        scanf("%f", &notas[cont - 1]);
        cont--;
    }
    for (int i = 0; i < 10; i++) {
        printf("Nota %d: %.2f\n", i + 1, notas[i]);
    }

    float media = calcularnotas(notas);
    printf("A média das notas é: %f", media);


    return 0;
}